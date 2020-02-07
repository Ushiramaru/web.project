package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
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

    private SectionService sectionService;
    private ConferenceService conferenceService;

    public ConferenceInfoCommand(SectionService sectionService, ConferenceService conferenceService) {
        this.sectionService = sectionService;
        this.conferenceService = conferenceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long conferenceId = Long.valueOf(request.getParameter("conference_id"));

        Optional<Conference> optionalConference = conferenceService.getById(conferenceId);
        if (!optionalConference.isPresent()) {
            throw new ServiceException("Specified conference doesn't exist");
        }
        Conference conference = optionalConference.get();
        if (!conference.isRelevant()) {
            throw new ServiceException("Specified conference doesn't relevant");
        }
        request.setAttribute("conference", conference);

        List<Section> sections = sectionService.getAllByConferenceId(conferenceId);
        request.setAttribute("sections", sections);


        return CommandResult.forward("/WEB-INF/conferenceInfo.jsp");
    }

}