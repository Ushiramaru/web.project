package com.epam.conference.controller;

import com.epam.conference.connection.ConnectionPool;
import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandFactory;
import com.epam.conference.controller.command.CommandResult;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(Controller.class);

    private final static String COMMAND_PARAMETER_NAME = "command";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandResult commandResult;

        try {
            String commandName = request.getParameter(COMMAND_PARAMETER_NAME);

            Command command = CommandFactory.create(commandName);
            commandResult = command.execute(request, response);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            if (e instanceof RuntimeException || e.getCause() != null) {
                response.sendError(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            } else {
                response.sendError(HttpStatus.SC_BAD_REQUEST);
            }

            return;
        }

        if (commandResult.isRedirect()) {
            response.sendRedirect(commandResult.getPage());
        } else {
            forward(request, response, commandResult.getPage());
        }
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void destroy() {
        ConnectionPool.getInstance().shutDown();
    }

}