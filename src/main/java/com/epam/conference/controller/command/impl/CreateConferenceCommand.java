package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.Controller;
import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Conference;
import com.epam.conference.entity.Section;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.SectionService;
import com.epam.conference.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateConferenceCommand implements Command {

    private final static String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm";

    private final ConferenceService conferenceService;

    public CreateConferenceCommand(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            String conferenceName = request.getParameter("conference_name");
            String startDateString = request.getParameter("start_date");
            String endDateString = request.getParameter("end_date");
            String[] sectionTopics = request.getParameterValues("section-topic[]");

            SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT_PATTERN);

            Date startDate = format.parse(startDateString);
            Date endDate = format.parse(endDateString);

            Conference conference = new Conference(null, conferenceName, startDate, endDate, true);
            List<Section> sections = new ArrayList<>();
            for (String sectionTopic : sectionTopics) {
                Section section = new Section(null, null, sectionTopic);
                sections.add(section);
            }
            Long conferenceId = conferenceService.create(conference, sections);

            return CommandResult.redirect("controller?command=conferenceInfoAdmin&conference_id=" + conferenceId);
        } catch (ParseException e) {
            throw new ServiceException(e);
        }
    }

}