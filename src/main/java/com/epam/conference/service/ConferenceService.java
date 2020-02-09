package com.epam.conference.service;

import com.epam.conference.dao.ConferenceDao;
import com.epam.conference.dao.SectionDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.entity.Conference;
import com.epam.conference.entity.Section;
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
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void blockById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            ConferenceDao dao = factory.createConferenceDao();
            dao.blockById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void unblockById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            ConferenceDao dao = factory.createConferenceDao();
            dao.unblockById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Conference> getAllRelevant() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            ConferenceDao dao = factory.createConferenceDao();
            return dao.getAllRelevant();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Conference> getById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            ConferenceDao dao = factory.createConferenceDao();
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Long create(Conference conference, List<Section> sections) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            Long conferenceId;
            try {
                factory.startTransaction();
                ConferenceDao conferenceDao = factory.createConferenceDao();
                conferenceId = conferenceDao.save(conference);

                SectionDao sectionDao = factory.createSectionDao();
                for (Section section : sections) {
                    sectionDao.save(new Section(null, conferenceId, section.getTopic()));
                }
            } catch (DaoException e) {
                factory.rollback();
                throw e;
            }
            factory.commit();

            return conferenceId;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}