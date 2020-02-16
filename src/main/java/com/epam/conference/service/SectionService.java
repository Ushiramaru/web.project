package com.epam.conference.service;

import com.epam.conference.entity.Section;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface SectionService extends Service<Section> {

    List<Section> getAllByConferenceId(Long conferenceId) throws ServiceException;

    Optional<Section> getRelevantById(Long sectionId) throws ServiceException;

    void editById(Long id, Object... params) throws ServiceException;

}