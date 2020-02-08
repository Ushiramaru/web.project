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

    public Optional<Section> getRelevantById(Long sectionId) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            SectionDao sectionDao = factory.createSectionDao();
            Optional<Section> optionalSection = sectionDao.getById(sectionId);
            if (!optionalSection.isPresent()) {
                return Optional.empty();
            }
            Section section = optionalSection.get();
            Long conferenceId = section.getConferenceId();

            ConferenceDao conferenceDao = factory.createConferenceDao();
            Optional<Conference> optionalConference = conferenceDao.getById(conferenceId);
            if (!optionalConference.isPresent()) {
                return Optional.empty();
            }
            Conference conference = optionalConference.get();

            return conference.isRelevant() ? Optional.of(section) : Optional.empty();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void editById(Long id, Object... params) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            SectionDao dao = factory.createSectionDao();
            dao.editById(id, params);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}