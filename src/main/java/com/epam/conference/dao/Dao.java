package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.Identifiable;

import java.util.List;
import java.util.Optional;

/**
 * The interface Dao.
 *
 * @param <T> the type parameter of stored elements
 */
public interface Dao<T extends Identifiable> {

    /**
     * Gets element by id.
     *
     * @param id the id of element
     * @return the element with specified id from storage
     * @throws DaoException when there are problems with the data storage
     */
    Optional<T> getById(Long id) throws DaoException;

    /**
     * Gets all elements.
     *
     * @return the all elements from storage
     * @throws DaoException when there are problems with the data storage
     */
    List<T> getAll() throws DaoException;

    /**
     * Gets count elements started with from.
     *
     * @param count the count of returned elements
     * @param from  start index of returned elements
     * @return the specified count elements from storage starting from specified from
     * @throws DaoException when there are problems with the data storage
     */
    List<T> getElementsFrom(Long count, Long from) throws DaoException;

    /**
     * Save item in storage.
     *
     * @param item the item what stored in storage
     * @return the value of id of saved item in storage
     * @throws DaoException when there are problems with the data storage
     */
    Long save(T item) throws DaoException;

    /**
     * Gets elements count in storage.
     *
     * @return the elements count in storage
     * @throws DaoException when there are problems with the data storage
     */
    Long getElementsCount() throws DaoException;

    /**
     * Remove element from storage by id.
     *
     * @param id the id of removed element
     * @throws DaoException when there are problems with the data storage
     */
    void removeById(Long id) throws DaoException;

}