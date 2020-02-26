package com.epam.conference.entity;

public class Question implements Identifiable {

    public final static String TABLE = "question";
    public final static String ID = "id";
    public final static String USER_ID = "user_id";
    public final static String ANSWER_ID = "answer_id";
    public final static String CONTENT = "content";

    private final Long id;
    private final Long userId;
    private final Long answerId;
    private final String content;

    public Question(Long id, Long userId, Long answerId, String content) {
        this.id = id;
        this.userId = userId;
        this.answerId = answerId;
        this.content = content;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public String getContent() {
        return content;
    }

}