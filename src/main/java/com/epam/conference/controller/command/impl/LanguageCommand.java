package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LanguageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        String language = extractor.extractParameter(request, "l");
        HttpSession session = request.getSession();
        session.setAttribute("language", language);
        String back = extractor.extractParameter(request, "back");
        back = back.replace("__","&");

        return CommandResult.redirect("controller?" + back);
    }

}