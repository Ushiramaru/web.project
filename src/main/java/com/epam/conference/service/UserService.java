package com.epam.conference.service;

import com.epam.conference.entity.User;
import com.epam.conference.service.exception.ServiceException;

import java.util.Optional;

public interface UserService extends Service<User> {

    void blockById(Long id) throws ServiceException;

    void unblockById(Long id) throws ServiceException;

    Optional<User> login(String login, String password) throws ServiceException;

}