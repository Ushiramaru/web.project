package com.epam.conference.entity;

import com.epam.conference.entity.enums.RequestState;

public class Request implements Identifiable {

    public final static String TABLE = "request";
    public final static String ID = "id";
    public final static String SECTION_ID = "section_id";
    public final static String USER_ID = "user_id";
    public final static String TOPIC = "topic";
    public final static String STATE = "state";

    private final Long id;
    private final Long sectionId;
    private final Long userId;
    private final String topic;
    private final RequestState state;

    public Request(Long id, Long sectionId, Long userId, String topic, RequestState state) {
        this.id = id;
        this.sectionId = sectionId;
        this.userId = userId;
        this.topic = topic;
        this.state = state;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTopic() {
        return topic;
    }

    public RequestState getState() {
        return state;
    }

}