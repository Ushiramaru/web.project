package com.epam.conference.service.impl;

import com.epam.conference.dao.Dao;
import com.epam.conference.dao.RequestDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.dto.RequestDto;
import com.epam.conference.entity.Request;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;

public class RequestServiceImpl extends AbstractService<Request> implements RequestService {

    public RequestServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    @Override
    public Long save(Request request) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            return dao.save(request);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<RequestDto> getAllWithFullInfo() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            return dao.getAllWithFullInfo();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void removeById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            dao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void rejectById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            dao.rejectById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void acceptById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            dao.acceptById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<RequestDto> getAllWithFullInfoByUserId(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            return dao.getAllWithFullInfoByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    protected Dao<Request> getCurrentDao(DaoHelper factory) {
        return factory.createRequestDao();
    }

}