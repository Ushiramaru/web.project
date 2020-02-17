package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.entity.Conference;
import com.epam.conference.entity.Section;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateConferenceCommand implements Command {

    //  TODO private final static String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm";
    private final static String CONFERENCE_NAME_PARAMETER_NAME = "conference_name";
    private final static String START_DATE_PARAMETER_NAME = "start_date";
    private final static String END_DATE_PARAMETER_NAME = "end_date";
    private final static String SECTION_TOPIC_PARAMETERS_NAME = "section-topic[]";
    private final static String ARGUMENT_MUST_NOT_BE_EMPTY = "Argument must not be empty";
    private final static String CONTROLLER_COMMAND_CONFERENCE_INFO_ADMIN_CONFERENCE_ID = "controller?" +
            "command=conferenceInfoAdmin&conference_id=";

    private final ConferenceService conferenceService;

    public CreateConferenceCommand(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        String conferenceName = extractor.extractParameter(request, CONFERENCE_NAME_PARAMETER_NAME);
        String startDateString = extractor.extractParameter(request, START_DATE_PARAMETER_NAME);
        String endDateString = extractor.extractParameter(request, END_DATE_PARAMETER_NAME);
        String[] sectionTopics = extractor.extractParameterValues(request, SECTION_TOPIC_PARAMETERS_NAME);

        LocalDateTime startDate = parseDate(startDateString);
        LocalDateTime endDate = parseDate(endDateString);

        Conference conference = new Conference(null, conferenceName, startDate, endDate, true);
        List<Section> sections = new ArrayList<>();
        for (String sectionTopic : sectionTopics) {
            Section section = new Section(null, null, sectionTopic);
            sections.add(section);
        }
        Long conferenceId = conferenceService.save(conference, sections);

        return CommandResult.redirect(CONTROLLER_COMMAND_CONFERENCE_INFO_ADMIN_CONFERENCE_ID + conferenceId);
    }

    private LocalDateTime parseDate(String input) {
        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException(ARGUMENT_MUST_NOT_BE_EMPTY);
        }

        return LocalDateTime.parse(input);
    }

}