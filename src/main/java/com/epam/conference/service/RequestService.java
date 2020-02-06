package com.epam.conference.service;

import com.epam.conference.dao.RequestDao;
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

    public void save(Request request) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            dao.save(request);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Request> getById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            return dao.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<RequestDto> getAllByUserId(Long id)  throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            return dao.getAllDtoByUserId(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void removeById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            RequestDao dao = factory.createRequestDao();
            dao.removeById(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

}