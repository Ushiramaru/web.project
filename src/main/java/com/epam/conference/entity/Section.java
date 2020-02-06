package com.epam.conference.entity;

public class Section implements Identifiable {

    public static final String TABLE = "section";
    public static final String ID = "id";
    public static final String CONFERENCE_ID = "conference_id";
    public static final String TOPIC = "topic";

    private final Long id;
    private final Long conferenceId;
    private final String topic;

    public Section(Long id, Long conferenceId, String topic) {
        this.id = id;
        this.conferenceId = conferenceId;
        this.topic = topic;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    public String getTopic() {
        return topic;
    }

}