package com.epam.conference.dto;

import com.epam.conference.entity.enums.RequestState;

/**
 * The type RequestDto displays full information about entity {@link com.epam.conference.entity.Request}.
 *
 * @see com.epam.conference.entity.Request
 * @see com.epam.conference.entity.Section
 * @see com.epam.conference.entity.Conference
 */
public class RequestDto implements Dto {

    /**
     * The constant REQUEST_ID represented full name of {@link com.epam.conference.entity.Request} id field in storage.
     */
    public final static String REQUEST_ID = "request.id";
    /**
     * The constant REQUEST_TOPIC represented full name of {@link com.epam.conference.entity.Request} topic field
     * in storage.
     */
    public final static String REQUEST_TOPIC = "request.topic";
    /**
     * The constant REQUEST_STATE represented full name of {@link com.epam.conference.entity.Request} state field
     * in storage.
     */
    public final static String REQUEST_STATE = "request.state";
    /**
     * The constant SECTION_TOPIC represented full name of {@link com.epam.conference.entity.Section} topic field
     * in storage.
     */
    public final static String SECTION_TOPIC = "section.topic";
    /**
     * The constant CONFERENCE_ID represented full name of {@link com.epam.conference.entity.Conference} id field
     * in storage.
     */
    public final static String CONFERENCE_ID = "conference.id";
    /**
     * The constant CONFERENCE_NAME represented full name of {@link com.epam.conference.entity.Conference} name field
     * in storage.
     */
    public final static String CONFERENCE_NAME = "conference.name";

    private final Long requestId;
    private final String requestTopic;
    private final RequestState state;
    private final String sectionTopic;
    private final Long conferenceId;
    private final String conferenceName;

    /**
     * Instantiates a new Request dto.
     *
     * @param requestId      the request id
     * @param requestTopic   the request topic
     * @param state          the state
     * @param sectionTopic   the section topic
     * @param conferenceId   the conference id
     * @param conferenceName the conference name
     */
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

    /**
     * Gets request topic.
     *
     * @return the request topic
     */
    public String getRequestTopic() {
        return requestTopic;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public RequestState getState() {
        return state;
    }

    /**
     * Gets section topic.
     *
     * @return the section topic
     */
    public String getSectionTopic() {
        return sectionTopic;
    }

    /**
     * Gets conference id.
     *
     * @return the conference id
     */
    public Long getConferenceId() {
        return conferenceId;
    }

    /**
     * Gets conference name.
     *
     * @return the conference name
     */
    public String getConferenceName() {
        return conferenceName;
    }

}