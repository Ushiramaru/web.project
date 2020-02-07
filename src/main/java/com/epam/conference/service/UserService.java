package com.epam.conference.service;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.dao.UserDao;
import com.epam.conference.entity.User;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class UserService {

    private DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> getAll() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}