package com.epam.conference.dto;

import com.epam.conference.entity.enums.UserRole;

/**
 * The type QuestionDto displays full information about entity {@link com.epam.conference.entity.Question}.
 *
 * @see com.epam.conference.entity.Question
 * @see com.epam.conference.entity.User
 */
public class QuestionDto implements Dto {

    /**
     * The constant QUESTION_ID represented full name of {@link com.epam.conference.entity.Question} id field
     * in storage.
     */
    public final static String QUESTION_ID = "question.id";
    /**
     * The constant QUESTION_CONTENT represented full name of {@link com.epam.conference.entity.Question} content field
     * in storage.
     */
    public final static String QUESTION_CONTENT = "question.content";
    /**
     * The constant USER_NAME represented full name of {@link com.epam.conference.entity.User} name field in storage.
     */
    public final static String USER_NAME = "user.name";
    /**
     * The constant USER_ROLE represented full name of {@link com.epam.conference.entity.User} role field in storage.
     */
    public final static String USER_ROLE = "user.role";

    private final Long questionId;
    private final String questionContent;
    private final String userName;
    private final UserRole userRole;

    /**
     * Instantiates a new QuestionDto.
     *
     * @param questionId      the question id
     * @param questionContent the question content
     * @param userName        the user name
     * @param userRole        the user role
     */
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

    /**
     * Gets question content.
     *
     * @return the question content
     */
    public String getQuestionContent() {
        return questionContent;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets user role.
     *
     * @return the user role
     */
    public UserRole getUserRole() {
        return userRole;
    }

}