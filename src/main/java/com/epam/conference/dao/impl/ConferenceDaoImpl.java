package com.epam.conference.dao.impl;

import com.epam.conference.dao.ConferenceDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.Conference;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ConferenceDaoImpl extends AbstractDao<Conference> implements ConferenceDao {

    private static final String SAVE_QUERY = "insert into conference (name, start_date, end_date, img) VALUES ('?', '?', '?', '?')";

    public ConferenceDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Conference> getById(Long id) throws DaoException {
        return super.getById(id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        super.removeById(id);
    }

    @Override
    public List<Conference> getAll() throws DaoException {
        return super.getAll();
    }

    @Override
    protected String getTableName() {
        return Conference.TABLE;
    }

    @Override
    public Long save(Conference item) throws DaoException {
        return super.executeUpdateQuery(SAVE_QUERY, item.getName(), item.getStartDate(), item.getEndDate(), item.getImagePath());
    }

}