package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.entity.Conference;
import com.epam.conference.entity.Section;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.SectionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ConferenceInfoAdminCommand extends AbstractCommand {

    private final static String CONFERENCE_ATTRIBUTE_NAME = "conference";
    private final static String SECTIONS_ATTRIBUTE_NAME = "sections";
    private final static String CONFERENCE_ID_PARAMETER_NAME = "conference_id";
    private final static String CONFERENCE_NOT_FOUND_MESSAGE = "specified conference doesn't exist";
    private final static String CONFERENCE_INFO_ADMIN_JSP = "/WEB-INF/conferenceInfoAdmin.jsp";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final ConferenceService conferenceService;
    private final SectionService sectionService;

    public ConferenceInfoAdminCommand(RequestParameterExtractor parameterExtractor,
                                      RequestParameterValidator parameterValidator, ConferenceService conferenceService, SectionService sectionService) {
        super(parameterExtractor, parameterValidator);
        this.conferenceService = conferenceService;
        this.sectionService = sectionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        Long conferenceId = extractor.extractLong(request, CONFERENCE_ID_PARAMETER_NAME);
        if (!validator.isValid(CONFERENCE_ID_PARAMETER_NAME, conferenceId)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", CONFERENCE_ID_PARAMETER_NAME));
        }

        Optional<Conference> optionalConference = conferenceService.getById(conferenceId);
        if (!optionalConference.isPresent()) {
            throw new ServiceException(CONFERENCE_NOT_FOUND_MESSAGE);
        }
        Conference conference = optionalConference.get();
        List<Section> sections = sectionService.getAllByConferenceId(conference.getId());

        request.setAttribute(CONFERENCE_ATTRIBUTE_NAME, conference);
        request.setAttribute(SECTIONS_ATTRIBUTE_NAME, sections);

        return CommandResult.forward(CONFERENCE_INFO_ADMIN_JSP);
    }

}