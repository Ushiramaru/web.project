package com.epam.conference.service;

import com.epam.conference.dto.QuestionDto;
import com.epam.conference.entity.Question;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface QuestionService extends Service<Question> {

    void save(Question item) throws ServiceException;

    Optional<QuestionDto> getFullInfoById(Long id) throws ServiceException;

    List<Question> getAllByUserId(Long id) throws ServiceException;

}