package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConferenceBlockCommand implements Command {

    private final static String CONFERENCE_ID_PARAMETER_NAME = "conference_id";
    private final static String CONTROLLER_COMMAND_CONFERENCE_ADMIN = "controller?command=conferenceAdmin";

    private final ConferenceService conferenceService;

    public ConferenceBlockCommand(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        Long blockUserId = Long.valueOf(extractor.extractParameter(request, CONFERENCE_ID_PARAMETER_NAME));
        conferenceService.blockById(blockUserId);

        return CommandResult.redirect(CONTROLLER_COMMAND_CONFERENCE_ADMIN);
    }

}