package com.epam.conference.entity;

public class Answer implements Identifiable {

    public final static String TABLE = "answer";
    public final static String ID = "id";
    public final static String ADMINISTRATOR_ID = "administrator_id";
    public final static String CONTENT = "content";

    private final Long id;
    private final Long administratorId;
    private final String content;

    public Answer(Long id, Long administratorId, String content) {
        this.id = id;
        this.administratorId = administratorId;
        this.content = content;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getAdministratorId() {
        return administratorId;
    }

    public String getContent() {
        return content;
    }

}
