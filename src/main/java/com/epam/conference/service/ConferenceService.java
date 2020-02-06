package com.epam.conference.service;

import com.epam.conference.dao.ConferenceDao;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.entity.Conference;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class ConferenceService {

    private DaoHelperFactory daoHelperFactory;

    public ConferenceService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Conference> getAll() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            ConferenceDao dao = factory.createConferenceDao();
            return dao.getAll();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Conference> getById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            ConferenceDao dao = factory.createConferenceDao();
            return dao.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

}