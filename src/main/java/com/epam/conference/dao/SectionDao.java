package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.Section;

import java.util.List;

/**
 * The interface Section dao.
 *
 * @see Section
 * @see com.epam.conference.entity.Conference
 */
public interface SectionDao extends Dao<Section> {

    /**
     * Gets all {@link Section} elements for specified {@link com.epam.conference.entity.Conference} by conference's id.
     *
     * @param id the id of {@link com.epam.conference.entity.Conference} element
     * @return the all {@link Section} elements for specified {@link com.epam.conference.entity.Conference}
     * @throws DaoException when there are problems with the data storage
     * @see com.epam.conference.entity.Conference
     */
    List<Section> getAllByConferenceId(Long id) throws DaoException;

    /**
     * Edit {@link Section} element by it's id.
     *
     * @param sectionId the id of {@link Section} element
     * @param params    the array of editable fields of {@link Section}
     * @throws DaoException when there are problems with the data storage
     */
    void editById(Long sectionId, Object... params) throws DaoException;

}