package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConferenceUnblockCommand extends AbstractCommand {

    private final static String CONFERENCE_ID_PARAMETER_NAME = "conference_id";
    private final static String CONTROLLER_COMMAND_CONFERENCE_ADMIN = "controller?command=conferenceAdmin";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final ConferenceService conferenceService;

    public ConferenceUnblockCommand(RequestParameterExtractor parameterExtractor,
                                    RequestParameterValidator parameterValidator, ConferenceService conferenceService) {
        super(parameterExtractor, parameterValidator);
        this.conferenceService = conferenceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        Long unblockConferenceId = extractor.extractLong(request, CONFERENCE_ID_PARAMETER_NAME);
        if (!validator.isValid(CONFERENCE_ID_PARAMETER_NAME, unblockConferenceId)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", CONFERENCE_ID_PARAMETER_NAME));
        }
        conferenceService.unblockById(unblockConferenceId);

        return CommandResult.redirect(CONTROLLER_COMMAND_CONFERENCE_ADMIN);
    }

}