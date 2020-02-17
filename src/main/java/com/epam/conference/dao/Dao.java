package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.Identifiable;

import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifiable> {

    Optional<T> getById(Long id) throws DaoException;

    List<T> getAll() throws DaoException;

    Long save(T item) throws DaoException;

    void removeById(Long id) throws DaoException;

}