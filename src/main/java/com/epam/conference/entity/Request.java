package com.epam.conference.entity;

import com.epam.conference.entity.enums.RequestState;

public class Request implements Identifiable {

    public static final String TABLE = "request";
    public static final String ID = "id";
    public static final String SECTION_ID = "section_id";
    public static final String USER_ID = "user_id";
    public static final String TOPIC = "topic";
    public static final String STATE = "state";

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