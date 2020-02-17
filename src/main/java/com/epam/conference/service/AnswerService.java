package com.epam.conference.service;

import com.epam.conference.dto.AnswerDto;
import com.epam.conference.entity.Answer;
import com.epam.conference.service.exception.ServiceException;

import java.util.Optional;

public interface AnswerService extends Service<Answer> {

    Long save(Answer item, Long questionId) throws ServiceException;

    Optional<AnswerDto> getByQuestionAndUserId(Long questionId, Long userId) throws ServiceException;

}