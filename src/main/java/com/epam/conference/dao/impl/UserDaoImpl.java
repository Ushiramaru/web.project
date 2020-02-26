package com.epam.conference.dao.impl;

import com.epam.conference.dao.UserDao;
import com.epam.conference.mapper.impl.entity.UserRowMapper;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.User;

import java.sql.Connection;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private final static String FIND_BY_LOGIN_AND_PASSWORD = "select * from user where login = ? and password = MD5(?)";
    private final static String SAVE_QUERY = "insert into user (login, password, name) VALUES ('?', MD5('?'), '?')";
    private final static String SET_IS_ACTIVE_QUERY = "update user set is_active = ? where id = ?";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
    }

    @Override
    public void blockById(Long id) throws DaoException {
        super.executeUpdateQuery(SET_IS_ACTIVE_QUERY, false, id);
    }

    @Override
    public void unblockById(Long id) throws DaoException {
        super.executeUpdateQuery(SET_IS_ACTIVE_QUERY, true, id);
    }

    @Override
    public Long save(User item) throws DaoException {
        return super.executeUpdateQuery(SAVE_QUERY, "", "", item.getName());
    }

    @Override
    protected String getTableName() {
        return User.TABLE;
    }

}