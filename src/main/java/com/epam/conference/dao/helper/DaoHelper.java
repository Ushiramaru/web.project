package com.epam.conference.dao.helper;

import com.epam.conference.connection.ConnectionPool;
import com.epam.conference.connection.ProxyConnection;
import com.epam.conference.dao.*;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.exception.DaoTransactionException;
import com.epam.conference.dao.impl.*;
import org.apache.log4j.Logger;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private final static Logger LOGGER = Logger.getLogger(DaoHelper.class);

    private ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) {
        this.connection = pool.getConnection();
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }

    public ConferenceDao createConferenceDao() {
        return new ConferenceDaoImpl(connection);
    }

    public SectionDao createSectionDao() {
        return new SectionDaoImpl(connection);
    }

    public RequestDao createRequestDao() {
        return new RequestDaoImpl(connection);
    }

    public QuestionDao createQuestionDao() {
        return new QuestionDaoImpl(connection);
    }

    public AnswerDao createAnswerDao() {
        return new AnswerDaoImpl(connection);
    }

    @Override
    public void close() {
        connection.returnToPool();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void commit() throws DaoException {
        try {
            try {
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DaoException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoTransactionException(e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}