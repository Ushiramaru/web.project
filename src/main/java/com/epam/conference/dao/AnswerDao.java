package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dto.AnswerDto;
import com.epam.conference.entity.Answer;

import java.util.Optional;

public interface AnswerDao extends Dao<Answer> {

    Optional<AnswerDto> getFullInfoById(Long id) throws DaoException;

}