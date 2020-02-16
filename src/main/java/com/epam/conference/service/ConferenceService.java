package com.epam.conference.service;

import com.epam.conference.entity.Conference;
import com.epam.conference.entity.Section;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;

public interface ConferenceService extends Service<Conference> {

    Long save(Conference conference, List<Section> sections) throws ServiceException;

    List<Conference> getAllRelevant() throws ServiceException;

    void blockById(Long id) throws ServiceException;

    void unblockById(Long id) throws ServiceException;

}