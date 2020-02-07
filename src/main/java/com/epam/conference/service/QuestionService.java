package com.epam.conference.service;

import com.epam.conference.dao.QuestionDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.dto.QuestionDto;
import com.epam.conference.entity.Question;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class QuestionService {

    private DaoHelperFactory daoHelperFactory;

    public QuestionService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void save(Question item) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            QuestionDao dao = factory.createQuestionDao();
            dao.save(item);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Question> getAllByUserId(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            QuestionDao dao = factory.createQuestionDao();
            return dao.getAllByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Question> getAll() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            QuestionDao dao = factory.createQuestionDao();
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Question> getById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            QuestionDao dao = factory.createQuestionDao();
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<QuestionDto> getFullInfoById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            QuestionDao dao = factory.createQuestionDao();
            return dao.getFullInfoById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}