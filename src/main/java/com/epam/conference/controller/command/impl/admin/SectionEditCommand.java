package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.entity.Section;
import com.epam.conference.service.SectionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SectionEditCommand implements Command {

    private final static String SECTION_ID_PARAMETER_NAME = "section_id";
    private final static String SECTION_TOPIC_PARAMETER_NAME = "section_topic";
    private final static String SECTION_NOT_FOUND_MESSAGE = "specified section doesn't exist or relevant";
    private final static String CONTROLLER_COMMAND_CONFERENCE_INFO_ADMIN_CONFERENCE_ID = "controller?" +
            "command=conferenceInfoAdmin&conference_id=";

    private final SectionService sectionService;

    public SectionEditCommand(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        Long sectionId = Long.valueOf(extractor.extractParameter(request, SECTION_ID_PARAMETER_NAME));
        String sectionTopic = extractor.extractParameter(request, SECTION_TOPIC_PARAMETER_NAME);

        Optional<Section> optionalSection = sectionService.getById(sectionId);
        if (!optionalSection.isPresent()) {
            throw new ServiceException(SECTION_NOT_FOUND_MESSAGE);
        }
        Section section = optionalSection.get();

        sectionService.editById(section.getId(), sectionTopic);

        return CommandResult.redirect(CONTROLLER_COMMAND_CONFERENCE_INFO_ADMIN_CONFERENCE_ID + section.getConferenceId());
    }

}