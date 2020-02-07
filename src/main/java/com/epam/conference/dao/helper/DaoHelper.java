package com.epam.conference.dao.helper;

import com.epam.conference.connection.ConnectionPool;
import com.epam.conference.connection.ProxyConnection;
import com.epam.conference.dao.*;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dao.exception.DaoRuntimeException;
import com.epam.conference.dao.impl.*;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

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
    public void close() throws DaoException {
        try {
            connection.returnToPool();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DaoRuntimeException(ex);
            }
            throw new DaoException(e);
        }
    }

}