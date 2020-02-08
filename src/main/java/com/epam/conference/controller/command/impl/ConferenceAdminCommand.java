package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Conference;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ConferenceAdminCommand implements Command {

    private ConferenceService service;

    public ConferenceAdminCommand(ConferenceService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Conference> conferences = service.getAll();
        request.setAttribute("conferences", conferences);

        return CommandResult.forward("/WEB-INF/conferenceAdmin.jsp");
    }

}