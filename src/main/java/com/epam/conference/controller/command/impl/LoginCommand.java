package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.User;
import com.epam.conference.service.UserService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<User> optionalUser = service.login(login, password);

        CommandResult result;
        String attributeName;
        Object attributeValue;
        HttpSession session = request.getSession();
        String locale = (String) session.getAttribute("language");
        ResourceBundle language = ResourceBundle.getBundle("locale_" + locale);
        boolean isLogin = false;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            isLogin = user.isActive();
            if (isLogin) {
                attributeName = "user";
                attributeValue = user;
            } else {
                attributeName = "wrong";
                attributeValue = language.getString("warn.userBlock");
            }
        } else {
            attributeName = "wrong";
            attributeValue = language.getString("warn.wrongUserData");
        }

        session.setAttribute(attributeName, attributeValue);
        if (isLogin) {
            session.removeAttribute("wrong");
            result = CommandResult.redirect("controller?command=mainPage");
        } else {
            result = CommandResult.redirect("controller?command=loginPage");
        }

        return result;
    }

}