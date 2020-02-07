package com.epam.conference.service;

import com.epam.conference.dao.SectionDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.entity.Section;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class SectionService {

    private DaoHelperFactory daoHelperFactory;

    public SectionService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<Section> getById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            SectionDao dao = factory.createSectionDao();
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Section> getAllByConferenceId(Long conferenceId) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            SectionDao dao = factory.createSectionDao();
            return dao.getAllByConferenceId(conferenceId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}