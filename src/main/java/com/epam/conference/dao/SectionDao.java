package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.Section;

import java.util.List;

public interface SectionDao extends Dao<Section> {

    List<Section> getAllByConferenceId(Long id) throws DaoException;

    void editById(Long sectionId, Object... params) throws DaoException;

}