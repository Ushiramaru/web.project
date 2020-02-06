package com.epam.conference.controller;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandFactory;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(Controller.class);

    public void destroy() {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandResult commandResult;

        try {
            String commandName = request.getParameter("command");
            LOGGER.info(commandName);

            Command command = CommandFactory.create(commandName);
            commandResult = command.execute(request, response);
        } catch (ServiceException e) {
            LOGGER.error(e.getMessage(), e);
            commandResult = CommandResult.redirect("/error.jsp");
        }

        LOGGER.info(commandResult);

        if (commandResult.isRedirect()) {
            response.sendRedirect(commandResult.getPage());
        } else {
            forward(request, response, commandResult.getPage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

}