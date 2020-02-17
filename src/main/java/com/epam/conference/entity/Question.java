package com.epam.conference.entity;

public class Question implements Identifiable {

    public static final String TABLE = "question";
    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String ANSWER_ID = "answer_id";
    public static final String CONTENT = "content";

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