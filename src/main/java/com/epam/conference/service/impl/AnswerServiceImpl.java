package com.epam.conference.service.impl;

import com.epam.conference.dao.AnswerDao;
import com.epam.conference.dao.Dao;
import com.epam.conference.dao.QuestionDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.dto.AnswerDto;
import com.epam.conference.entity.Answer;
import com.epam.conference.entity.Question;
import com.epam.conference.service.AnswerService;
import com.epam.conference.service.exception.ServiceException;

import java.util.Optional;

public class AnswerServiceImpl extends AbstractService<Answer> implements AnswerService {

    public AnswerServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    @Override
    public Long save(Answer item, Long questionId) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            Long answerId;
            try {
                factory.startTransaction();
                AnswerDao answerDao = factory.createAnswerDao();
                answerId = answerDao.save(item);

                QuestionDao questionDao = factory.createQuestionDao();
                questionDao.answer(questionId, answerId);
            } catch (DaoException e) {
                factory.rollback();
                throw e;
            }
            factory.commit();

            return answerId;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<AnswerDto> getByQuestionAndUserId(Long questionId, Long userId) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            Optional<AnswerDto> optionalAnswer;
            try {
                factory.startTransaction();
                QuestionDao questionDao = factory.createQuestionDao();
                Optional<Question> optionalQuestion = questionDao.getById(questionId);
                if (!optionalQuestion.isPresent()) {
                    return Optional.empty();
                }
                Question question = optionalQuestion.get();

                if (!question.getUserId().equals(userId)) {
                    return Optional.empty();
                }

                AnswerDao answerDao = factory.createAnswerDao();
                optionalAnswer = answerDao.getFullInfoById(question.getAnswerId());
            } catch (DaoException e) {
                factory.rollback();
                throw e;
            }
            factory.commit();

            return optionalAnswer;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    protected Dao<Answer> getCurrentDao(DaoHelper factory) {
        return factory.createAnswerDao();
    }

}