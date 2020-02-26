package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.entity.Conference;
import com.epam.conference.entity.Section;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateConferenceCommand extends AbstractCommand {

    private final static String CONFERENCE_NAME_PARAMETER_NAME = "conference_name";
    private final static String START_DATE_PARAMETER_NAME = "start_date";
    private final static String END_DATE_PARAMETER_NAME = "end_date";
    private final static String SECTION_TOPIC_PARAMETERS_NAME = "section-topic[]";
    private final static String CONTROLLER_COMMAND_CONFERENCE_INFO_ADMIN_CONFERENCE_ID = "controller?" +
            "command=conferenceInfoAdmin&conference_id=";
    private final static String INVALID_DATE_RANGE_MESSAGE = "start_date can't be later than end_date";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final ConferenceService conferenceService;

    public CreateConferenceCommand(RequestParameterExtractor parameterExtractor,
                                   RequestParameterValidator parameterValidator, ConferenceService conferenceService) {
        super(parameterExtractor, parameterValidator);
        this.conferenceService = conferenceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        String conferenceName = extractor.extractString(request, CONFERENCE_NAME_PARAMETER_NAME);
        if (!validator.isValid(CONFERENCE_NAME_PARAMETER_NAME, conferenceName)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", CONFERENCE_NAME_PARAMETER_NAME));
        }
        LocalDateTime startDate = extractor.extractDate(request, START_DATE_PARAMETER_NAME);
        if (!validator.isValid(START_DATE_PARAMETER_NAME, startDate)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", START_DATE_PARAMETER_NAME));
        }
        LocalDateTime endDate = extractor.extractDate(request, END_DATE_PARAMETER_NAME);
        if (!validator.isValid(END_DATE_PARAMETER_NAME, endDate)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", END_DATE_PARAMETER_NAME));
        }

        if (startDate.equals(endDate) || startDate.isBefore(endDate)) {
            throw new ServiceException(INVALID_DATE_RANGE_MESSAGE);
        }

        String[] sectionTopics = extractor.extractStrings(request, SECTION_TOPIC_PARAMETERS_NAME);
        if (!validator.isValid(SECTION_TOPIC_PARAMETERS_NAME, sectionTopics)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", SECTION_TOPIC_PARAMETERS_NAME));
        }

        Conference conference = new Conference(null, conferenceName, startDate, endDate, true);
        List<Section> sections = new ArrayList<>();
        for (String sectionTopic : sectionTopics) {
            Section section = new Section(null, null, sectionTopic);
            sections.add(section);
        }
        Long conferenceId = conferenceService.save(conference, sections);

        return CommandResult.redirect(CONTROLLER_COMMAND_CONFERENCE_INFO_ADMIN_CONFERENCE_ID + conferenceId);
    }

}