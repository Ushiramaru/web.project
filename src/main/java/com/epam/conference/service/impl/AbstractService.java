package com.epam.conference.service.impl;

import com.epam.conference.dao.Dao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.helper.DaoHelper;
import com.epam.conference.dao.helper.DaoHelperFactory;
import com.epam.conference.entity.Identifiable;
import com.epam.conference.service.Service;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T extends Identifiable> implements Service<T> {

    protected final DaoHelperFactory daoHelperFactory;

    public AbstractService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Optional<T> getById(Long id) throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            Dao<T> dao = getCurrentDao(factory);
            return dao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<T> getAll() throws ServiceException {
        try (DaoHelper factory = daoHelperFactory.create()) {
            Dao<T> dao = getCurrentDao(factory);
            return dao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    protected abstract Dao<T> getCurrentDao(DaoHelper factory);

}