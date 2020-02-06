package com.epam.conference.entity;

public class Answer implements Identifiable {

    public static final String TABLE = "answer";
    public static final String ID = "id";
    public static final String ADMINISTRATOR_ID = "administrator_id";
    public static final String CONTENT = "content";

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
