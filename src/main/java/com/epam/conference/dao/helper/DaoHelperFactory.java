package com.epam.conference.dao.helper;

import com.epam.conference.connection.ConnectionPool;
import com.epam.conference.dao.exception.DaoException;

import java.sql.SQLException;

public class DaoHelperFactory {

    public DaoHelper create() throws DaoException {
        DaoHelper helper;

        try {
            helper = new DaoHelper(ConnectionPool.getInstance());
        } catch (SQLException | InterruptedException e) {
            throw new DaoException(e);
        }

        return helper;
    }

}