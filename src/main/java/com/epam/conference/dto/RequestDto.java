package com.epam.conference.dto;

import com.epam.conference.entity.enums.RequestState;

public class RequestDto implements Dto {

    public final static String REQUEST_ID = "request.id";
    public final static String REQUEST_TOPIC = "request.topic";
    public final static String REQUEST_STATE = "request.state";

    public final static String SECTION_TOPIC = "section.topic";

    public final static String CONFERENCE_ID = "conference.id";
    public final static String CONFERENCE_NAME = "conference.name";

    private final Long requestId;
    private final String requestTopic;
    private final RequestState state;

    private final String sectionTopic;

    private final Long conferenceId;
    private final String conferenceName;

    public RequestDto(Long requestId, String requestTopic, RequestState state, String sectionTopic,
                      Long conferenceId, String conferenceName) {
        this.requestId = requestId;
        this.requestTopic = requestTopic;
        this.state = state;
        this.sectionTopic = sectionTopic;
        this.conferenceId = conferenceId;
        this.conferenceName = conferenceName;
    }

    @Override
    public Long getId() {
        return requestId;
    }

    public String getRequestTopic() {
        return requestTopic;
    }

    public RequestState getState() {
        return state;
    }

    public String getSectionTopic() {
        return sectionTopic;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

}