package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dto.QuestionDto;
import com.epam.conference.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDao extends Dao<Question> {

    List<Question> getAllByUserId(Long id) throws DaoException;

    Optional<QuestionDto> getFullInfoById(Long id) throws DaoException;

    void answer(Long questionId, Long answerId) throws DaoException;

}