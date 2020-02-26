package com.epam.conference.entity;

public class Section implements Identifiable {

    public final static String TABLE = "section";
    public final static String ID = "id";
    public final static String CONFERENCE_ID = "conference_id";
    public final static String TOPIC = "topic";

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