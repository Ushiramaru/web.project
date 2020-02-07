package com.epam.conference.service;

import com.epam.conference.dao.AnswerDao;
import com.epam.conference.dao.QuestionDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.dto.AnswerDto;
import com.epam.conference.entity.Answer;
import com.epam.conference.service.exception.ServiceException;

import java.util.Optional;

public class AnswerService {

    private DaoHelperFactory daoHelperFactory;

    public AnswerService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<AnswerDto> getById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            AnswerDao dao = factory.createAnswerDao();
            return dao.getFullInfoById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void save(Answer item) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    public void answer(Answer item, Long questionId) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            factory.startTransaction();
            AnswerDao answerDao = factory.createAnswerDao();
            Long answerId = answerDao.save(item);

            QuestionDao questionDao = factory.createQuestionDao();
            questionDao.answer(questionId, answerId);
            factory.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}