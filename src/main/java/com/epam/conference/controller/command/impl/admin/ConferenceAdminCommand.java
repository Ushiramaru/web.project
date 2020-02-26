package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Conference;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ConferenceAdminCommand implements Command {

    private final static String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private final static String CONFERENCE_ADMIN_JSP = "/WEB-INF/conferenceAdmin.jsp";

    private final ConferenceService service;

    public ConferenceAdminCommand(ConferenceService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Conference> conferences = service.getAll();
        request.setAttribute(CONFERENCES_ATTRIBUTE_NAME, conferences);

        return CommandResult.forward(CONFERENCE_ADMIN_JSP);
    }

}