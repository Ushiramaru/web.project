package com.epam.conference.dto;

/**
 * The type AnswerDto displays full information about entity {@link com.epam.conference.entity.Answer}.
 *
 * @see com.epam.conference.entity.Answer
 * @see com.epam.conference.entity.User
 */
public class AnswerDto implements Dto {

    /**
     * The constant ANSWER_ID represented full name of {@link com.epam.conference.entity.Answer} id field in storage.
     *
     * @see com.epam.conference.entity.Answer#getId()
     */
    public final static String ANSWER_ID = "answer.id";
    /**
     * The constant ANSWER_CONTENT represented full name of {@link com.epam.conference.entity.Answer} content field in
     * storage.
     *
     * @see com.epam.conference.entity.Answer#getContent()
     */
    public final static String ANSWER_CONTENT = "answer.content";
    /**
     * The constant ADMINISTRATOR_NAME represented full name of {@link com.epam.conference.entity.User} name field in
     * storage.
     *
     * @see com.epam.conference.entity.User#getName()
     */
    public final static String ADMINISTRATOR_NAME = "user.name";

    /**
     * {@link com.epam.conference.entity.Answer} id
     *
     * @see com.epam.conference.entity.Answer#getId()
     */
    private final Long answerId;
    /**
     * {@link com.epam.conference.entity.Answer} content
     *
     * @see com.epam.conference.entity.Answer#getContent()
     */
    private final String answerContent;
    /**
     * The name of administrator who gave this answer
     *
     * @see com.epam.conference.entity.User#getName()
     */
    private final String administratorName;

    /**
     * Instantiates a new AnswerDto.
     *
     * @param answerId          the answer id
     * @param answerContent     the answer content
     * @param administratorName the administrator name
     */
    public AnswerDto(Long answerId, String answerContent, String administratorName) {
        this.answerId = answerId;
        this.answerContent = answerContent;
        this.administratorName = administratorName;
    }

    @Override
    public Long getId() {
        return answerId;
    }

    /**
     * Gets answer content.
     *
     * @return the answer content
     */
    public String getAnswerContent() {
        return answerContent;
    }

    /**
     * Gets administrator name.
     *
     * @return the administrator name
     */
    public String getAdministratorName() {
        return administratorName;
    }

}