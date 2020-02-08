package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConferenceUnblockCommand implements Command {

    private final ConferenceService conferenceService;

    public ConferenceUnblockCommand(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long unblockUserId = Long.valueOf(request.getParameter("conference_id"));
        conferenceService.unblockById(unblockUserId);

        return CommandResult.redirect("controller?command=conferenceAdmin");
    }

}