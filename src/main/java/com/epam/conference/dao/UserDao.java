package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User>{

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    void blockById(Long id) throws DaoException;

    void unblockById(Long id) throws DaoException;

}