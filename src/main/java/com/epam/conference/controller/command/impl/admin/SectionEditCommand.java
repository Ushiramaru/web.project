package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.entity.Section;
import com.epam.conference.service.SectionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SectionEditCommand extends AbstractCommand {

    private final static String SECTION_ID_PARAMETER_NAME = "section_id";
    private final static String SECTION_TOPIC_PARAMETER_NAME = "section_topic";
    private final static String SECTION_NOT_FOUND_MESSAGE = "specified section doesn't exist or relevant";
    private final static String CONTROLLER_COMMAND_CONFERENCE_INFO_ADMIN_CONFERENCE_ID = "controller?" +
            "command=conferenceInfoAdmin&conference_id=";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final SectionService sectionService;

    public SectionEditCommand(RequestParameterExtractor parameterExtractor,
                              RequestParameterValidator parameterValidator, SectionService sectionService) {
        super(parameterExtractor, parameterValidator);
        this.sectionService = sectionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        Long sectionId = extractor.extractLong(request, SECTION_ID_PARAMETER_NAME);
        if (!validator.isValid(SECTION_ID_PARAMETER_NAME, sectionId)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", SECTION_ID_PARAMETER_NAME));
        }
        String sectionTopic = extractor.extractString(request, SECTION_TOPIC_PARAMETER_NAME);
        if (!validator.isValid(SECTION_TOPIC_PARAMETER_NAME, sectionTopic)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", SECTION_TOPIC_PARAMETER_NAME));
        }

        Optional<Section> optionalSection = sectionService.getById(sectionId);
        if (!optionalSection.isPresent()) {
            throw new ServiceException(SECTION_NOT_FOUND_MESSAGE);
        }
        Section section = optionalSection.get();

        sectionService.editById(section.getId(), sectionTopic);

        return CommandResult.redirect(CONTROLLER_COMMAND_CONFERENCE_INFO_ADMIN_CONFERENCE_ID + section.getConferenceId());
    }

}