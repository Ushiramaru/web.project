package com.epam.conference.controller.command.impl.user;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.entity.Conference;
import com.epam.conference.entity.Section;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.SectionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ConferenceInfoCommand implements Command {

    private final static String CONFERENCE_ID_PARAMETER_NAME = "conference_id";
    private final static String CONFERENCE_ATTRIBUTE_NAME = "conference";
    private final static String SECTIONS_ATTRIBUTE_NAME = "sections";
    private final static String CONFERENCE_NOT_FOUND_MESSAGE = "specified conference doesn't exist";
    private final static String CONFERENCE_IS_NOT_RELEVANT_MESSAGE = "specified conference doesn't relevant";
    private final static String CONFERENCE_INFO_JSP = "/WEB-INF/conferenceInfo.jsp";

    private SectionService sectionService;
    private ConferenceService conferenceService;

    public ConferenceInfoCommand(SectionService sectionService, ConferenceService conferenceService) {
        this.sectionService = sectionService;
        this.conferenceService = conferenceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        Long conferenceId = Long.valueOf(extractor.extractParameter(request, CONFERENCE_ID_PARAMETER_NAME));

        Optional<Conference> optionalConference = conferenceService.getById(conferenceId);
        if (!optionalConference.isPresent()) {
            throw new ServiceException(CONFERENCE_NOT_FOUND_MESSAGE);
        }

        Conference conference = optionalConference.get();
        if (!conference.isRelevant()) {
            throw new ServiceException(CONFERENCE_IS_NOT_RELEVANT_MESSAGE);
        }
        request.setAttribute(CONFERENCE_ATTRIBUTE_NAME, conference);

        List<Section> sections = sectionService.getAllByConferenceId(conferenceId);
        request.setAttribute(SECTIONS_ATTRIBUTE_NAME, sections);

        return CommandResult.forward(CONFERENCE_INFO_JSP);
    }

}