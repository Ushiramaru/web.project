package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.Conference;

import java.util.List;

/**
 * The interface Conference dao.
 *
 * @see Conference#isRelevant()
 */
public interface ConferenceDao extends Dao<Conference> {

    /**
     * Gets all relevant {@link Conference} elements from data storage.
     *
     * @return the all relevant {@link Conference} elements from storage
     * @throws DaoException when there are problems with the data storage
     * @see Conference#isRelevant()
     */
    List<Conference> getAllRelevant() throws DaoException;

    /**
     * Block {@link Conference} by id.
     *
     * @param id the id of {@link Conference} element
     * @throws DaoException when there are problems with the data storage
     * @see Conference#isRelevant()
     */
    void blockById(Long id) throws DaoException;

    /**
     * Unblock {@link Conference} by id.
     *
     * @param id the id of {@link Conference} element
     * @throws DaoException when there are problems with the data storage
     * @see Conference#isRelevant()
     */
    void unblockById(Long id) throws DaoException;

}