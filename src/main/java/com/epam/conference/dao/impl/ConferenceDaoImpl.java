package com.epam.conference.dao.impl;

import com.epam.conference.dao.ConferenceDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.Conference;
import com.epam.conference.mapper.impl.entity.ConferenceRowMapper;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

public class ConferenceDaoImpl extends AbstractDao<Conference> implements ConferenceDao {

    private final static String SAVE_QUERY = "insert into conference (name, start_date, end_date) VALUES (?, ?, ?)";
    private final static String GET_ALL_BY_RELEVANT_QUERY = "select * from conference where is_relevant = ?";
    private final static String SET_RELEVANT_QUERY = "update conference set is_relevant = ? where id = ?";

    public ConferenceDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void blockById(Long id) throws DaoException {
        super.executeUpdateQuery(SET_RELEVANT_QUERY, false, id);
    }

    @Override
    public void unblockById(Long id) throws DaoException {
        super.executeUpdateQuery(SET_RELEVANT_QUERY, true, id);
    }

    @Override
    public List<Conference> getAllRelevant() throws DaoException {
        return super.executeQuery(GET_ALL_BY_RELEVANT_QUERY, new ConferenceRowMapper(), true);
    }

    @Override
    public Long save(Conference item) throws DaoException {
        return super.executeUpdateQuery(SAVE_QUERY, item.getName(), Timestamp.valueOf(item.getStartDate()), Timestamp.valueOf(item.getEndDate()));
    }

    @Override
    protected String getTableName() {
        return Conference.TABLE;
    }

}