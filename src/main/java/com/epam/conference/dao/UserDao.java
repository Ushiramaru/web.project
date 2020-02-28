package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.User;

import java.util.Optional;

/**
 * The interface User dao.
 *
 * @see User
 */
public interface UserDao extends Dao<User> {

    /**
     * Find {@link User} element in storage by login and password.
     *
     * @param login    the login of user
     * @param password the password of user
     * @return the {@link Optional} with {@link User} element if found, or {@link Optional#empty()} if otherwise
     * @throws DaoException when there are problems with the data storage
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Block {@link User} by id.
     *
     * @param id the id of {@link User}
     * @throws DaoException when there are problems with the data storage
     * @see User#isActive()
     */
    void blockById(Long id) throws DaoException;

    /**
     * Unblock {@link User} by id.
     *
     * @param id the id of {@link User}
     * @throws DaoException when there are problems with the data storage
     * @see User#isActive()
     */
    void unblockById(Long id) throws DaoException;

}