package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dto.AnswerDto;
import com.epam.conference.entity.Answer;

import java.util.Optional;

/**
 * The interface Answer dao.
 *
 * @see Answer
 * @see AnswerDto
 */
public interface AnswerDao extends Dao<Answer> {

    /**
     * Gets {@link AnswerDto} of element by id.
     *
     * @param id the id of {@link Answer} element
     * @return the {@link AnswerDto} of {@link Answer} element with specified id from storage
     * @throws DaoException when there are problems with the data storage
     * @see AnswerDto
     */
    Optional<AnswerDto> getFullInfoById(Long id) throws DaoException;

}