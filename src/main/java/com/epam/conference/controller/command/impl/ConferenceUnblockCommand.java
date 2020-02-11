package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
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
        ParameterExtractor extractor = new ParameterExtractor();
        Long unblockUserId = Long.valueOf(extractor.extractParameter(request,"conference_id"));
        conferenceService.unblockById(unblockUserId);

        return CommandResult.redirect("controller?command=conferenceAdmin");
    }

}