package com.epam.conference.dao.impl;

import com.epam.conference.dao.Dao;
import com.epam.conference.dto.Dto;
import com.epam.conference.mapper.RowMapper;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.Identifiable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifiable> implements Dao<T> {

    private final static String MORE_THEN_ONE_RECORD_FOUND = "More then one record found";

    private final Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }

            return entities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected Long executeUpdateQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                } else {
                    return 0L;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            statement.setObject(i, params[i - 1]);
        }

        return statement;
    }

    protected <E extends Dto> List<E> executeJoinQuery(String query, RowMapper<E> mapper, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            List<E> dtoEntities = new ArrayList<>();
            while (resultSet.next()) {
                E dtoEntity = mapper.map(resultSet);
                dtoEntities.add(dtoEntity);
            }

            return dtoEntities;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected <E extends Dto> Optional<E> executeJoinForSingleResult(String query, RowMapper<E> builder, Object... params) throws DaoException {
        List<E> items = executeJoinQuery(query, builder, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new DaoException(MORE_THEN_ONE_RECORD_FOUND);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<T> getAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);

        return executeQuery("select * from " + table, mapper);
    }

    @Override
    public List<T> getElementsFrom(Long count, Long from) throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);

        return executeQuery("select * from " + table + " limit ?, ?", mapper, from, count);
    }

    @Override
    public Long getElementsCount() throws DaoException {
        String table = getTableName();
        try (PreparedStatement statement = createStatement("select count(*) from " + table);
             ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) {
                throw new DaoException("Can't get table size");
            }
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<T> getById(Long id) throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);

        return executeForSingleResult("select * from " + table + " where id = ?", mapper, id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        try (PreparedStatement statement = createStatement("delete from " + getTableName() + " where id = ?", id)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected Optional<T> executeForSingleResult(String query, RowMapper<T> builder, Object... params) throws DaoException {
        List<T> items = executeQuery(query, builder, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new DaoException(MORE_THEN_ONE_RECORD_FOUND);
        } else {
            return Optional.empty();
        }
    }

    protected abstract String getTableName();

}