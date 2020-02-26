package com.epam.conference.controller.command.impl.user;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Conference;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ConferenceCommand implements Command {

    private final static String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private final static String CONFERENCE_JSP = "/WEB-INF/conference.jsp";

    private final ConferenceService service;

    public ConferenceCommand(ConferenceService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Conference> conferences = service.getAllRelevant();
        request.setAttribute(CONFERENCES_ATTRIBUTE_NAME, conferences);

        return CommandResult.forward(CONFERENCE_JSP);
    }

}