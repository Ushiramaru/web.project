package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.entity.Conference;
import com.epam.conference.entity.Section;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateConferenceCommand implements Command {

    private final static String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm";
//    private final static SimpleDateFormat FORMAT = new DateTimeFormatter(DATE_TIME_FORMAT_PATTERN);

    private final ConferenceService conferenceService;

    public CreateConferenceCommand(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        String conferenceName = extractor.extractParameter(request,"conference_name");
        String startDateString = extractor.extractParameter(request,"start_date");
        String endDateString = extractor.extractParameter(request,"end_date");
        String[] sectionTopics = extractor.extractParameterValues(request,"section-topic[]");

        LocalDateTime startDate = parseDate(startDateString);
        LocalDateTime endDate = parseDate(endDateString);

        Conference conference = new Conference(null, conferenceName, startDate, endDate, true);
        List<Section> sections = new ArrayList<>();
        for (String sectionTopic : sectionTopics) {
            Section section = new Section(null, null, sectionTopic);
            sections.add(section);
        }
        Long conferenceId = conferenceService.create(conference, sections);

        return CommandResult.redirect("controller?command=conferenceInfoAdmin&conference_id=" + conferenceId);
    }

    private LocalDateTime parseDate(String input) {
        if (input == null || input.length() == 0) {
            throw new IllegalArgumentException("Argument must be not empty");
        }

        return LocalDateTime.parse(input);
    }

}