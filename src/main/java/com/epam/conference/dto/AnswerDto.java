package com.epam.conference.dto;

public class AnswerDto implements Dto {

    public final static String ANSWER_ID = "answer.id";
    public final static String ANSWER_CONTENT = "answer.content";

    public final static String ADMINISTRATOR_NAME = "user.name";

    private final Long answerId;
    private final String answerContent;

    private final String administratorName;

    public AnswerDto(Long answerId, String answerContent, String administratorName) {
        this.answerId = answerId;
        this.answerContent = answerContent;
        this.administratorName = administratorName;
    }

    @Override
    public Long getId() {
        return answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public String getAdministratorName() {
        return administratorName;
    }
}