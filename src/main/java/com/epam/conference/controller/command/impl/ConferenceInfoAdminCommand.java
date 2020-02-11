package com.epam.conference.controller.command.impl;

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

public class ConferenceInfoAdminCommand implements Command {

    private final ConferenceService conferenceService;
    private final SectionService sectionService;

    public ConferenceInfoAdminCommand(ConferenceService conferenceService, SectionService sectionService) {
        this.conferenceService = conferenceService;
        this.sectionService = sectionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        Long conferenceId = Long.valueOf(extractor.extractParameter(request,"conference_id"));

        Optional<Conference> optionalConference = conferenceService.getById(conferenceId);
        if (!optionalConference.isPresent()) {
            throw new ServiceException("Specified conference doesn't exist");
        }
        Conference conference = optionalConference.get();
        List<Section> sections = sectionService.getAllByConferenceId(conference.getId());

        request.setAttribute("conference", conference);
        request.setAttribute("sections", sections);

        return CommandResult.forward("/WEB-INF/conferenceInfoAdmin.jsp");
    }

}