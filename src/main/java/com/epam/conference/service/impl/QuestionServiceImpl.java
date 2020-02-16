package com.epam.conference.service.impl;

import com.epam.conference.dao.Dao;
import com.epam.conference.dao.QuestionDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.dto.QuestionDto;
import com.epam.conference.entity.Question;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl extends AbstractService<Question> implements QuestionService {

    public QuestionServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    @Override
    public void save(Question item) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            QuestionDao dao = factory.createQuestionDao();
            dao.save(item);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<QuestionDto> getFullInfoById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            QuestionDao dao = factory.createQuestionDao();
            return dao.getFullInfoById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Question> getAllByUserId(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            QuestionDao dao = factory.createQuestionDao();
            return dao.getAllByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    protected Dao<Question> getCurrentDao(DaoHelper factory) {
        return factory.createQuestionDao();
    }

}