package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Section;
import com.epam.conference.service.SectionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SectionEditCommand implements Command {

    private final SectionService sectionService;

    public SectionEditCommand(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long sectionId = Long.valueOf(request.getParameter("section_id"));
        String sectionTopic = request.getParameter("section_topic");

        Optional<Section> optionalSection = sectionService.getById(sectionId);
        if (!optionalSection.isPresent()) {
            throw new ServiceException("Specified section doesn't exist");
        }
        Section section = optionalSection.get();

        sectionService.editById(section.getId(), sectionTopic);

        return CommandResult.redirect("controller?command=conferenceInfoAdmin&conference_id=" + section.getConferenceId());
    }

}