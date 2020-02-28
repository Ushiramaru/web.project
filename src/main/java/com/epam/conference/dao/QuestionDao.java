package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dto.QuestionDto;
import com.epam.conference.entity.Question;

import java.util.List;
import java.util.Optional;

/**
 * The interface Question dao.
 *
 * @see Question
 * @see QuestionDto
 */
public interface QuestionDao extends Dao<Question> {

    /**
     * Gets all {@link Question} elements for specified {@link com.epam.conference.entity.User} by user's id.
     *
     * @param id the id of {@link com.epam.conference.entity.User}
     * @return the all {@link Question} elements by user's id from storage
     * @throws DaoException when there are problems with the data storage
     */
    List<Question> getAllByUserId(Long id) throws DaoException;

    /**
     * Gets {@link QuestionDto} of element by id.
     *
     * @param id the id of {@link Question}
     * @return the {@link QuestionDto} of {@link Question} element with specified id from storage
     * @throws DaoException when there are problems with the data storage
     * @see QuestionDto
     */
    Optional<QuestionDto> getFullInfoById(Long id) throws DaoException;

    /**
     * Link {@link Question} element with the corresponding {@link com.epam.conference.entity.Answer} element.
     *
     * @param questionId the id of {@link Question} element
     * @param answerId   the id of {@link com.epam.conference.entity.Answer} element
     * @throws DaoException when there are problems with the data storage
     */
    void answer(Long questionId, Long answerId) throws DaoException;

}