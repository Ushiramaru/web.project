package com.epam.conference.service.impl;

import com.epam.conference.dao.Dao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.dao.UserDao;
import com.epam.conference.entity.User;
import com.epam.conference.service.UserService;
import com.epam.conference.service.exception.ServiceException;

import java.util.Optional;

public class UserServiceImpl extends AbstractService<User> implements UserService {

    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory);
    }

    @Override
    public void blockById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            dao.blockById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void unblockById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            dao.unblockById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            UserDao dao = factory.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    protected Dao<User> getCurrentDao(DaoHelper factory) {
        return factory.createUserDao();
    }

}