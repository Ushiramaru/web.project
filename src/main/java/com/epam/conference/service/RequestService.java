package com.epam.conference.service;

import com.epam.conference.dao.RequestDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.dto.RequestDto;
import com.epam.conference.entity.Request;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class RequestService {

    private DaoHelperFactory daoHelperFactory;

    public RequestService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void rejectById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            dao.rejectById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void acceptById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            dao.acceptById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void save(Request request) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            dao.save(request);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Request> getById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<RequestDto> getAllDtoByUserId(Long id)  throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            return dao.getAllDtoByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<RequestDto> getAllDto()  throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            return dao.getAllDto();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void removeById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            dao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}