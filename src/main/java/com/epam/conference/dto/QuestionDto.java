package com.epam.conference.dto;

import com.epam.conference.entity.enums.UserRole;

public class QuestionDto implements Dto {

    public final static String QUESTION_ID = "question.id";
    public final static String QUESTION_CONTENT = "question.content";

    public final static String USER_NAME = "user.name";
    public final static String USER_ROLE = "user.role";

    private final Long questionId;
    private final String questionContent;

    private final String userName;
    private final UserRole userRole;

    public QuestionDto(Long questionId, String questionContent, String userName, UserRole userRole) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.userName = userName;
        this.userRole = userRole;
    }

    @Override
    public Long getId() {
        return questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getUserName() {
        return userName;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}