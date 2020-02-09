package com.epam.conference.dao.impl;

import com.epam.conference.dao.UserDao;
import com.epam.conference.mapper.impl.entity.UserRowMapper;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.User;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String FIND_BY_LOGIN_AND_PASSWORD = "select * from user where login = ? and password = MD5(?)";
    private static final String SAVE_QUERY = "insert into user (login, password, name) VALUES ('?', MD5('?'), '?')";
    private static final String SET_IS_ACTIVE_QUERY = "update user set is_active = ? where id = ?";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
    }

    public Long registerUser(String login, String password, String name) throws DaoException {
        return super.executeUpdateQuery(SAVE_QUERY, login, password, name);
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
    public Optional<User> getById(Long id) throws DaoException {
        return super.getById(id);
    }

    @Override
    public List<User> getAll() throws DaoException {
        return super.getAll();
    }

    @Override
    public void removeById(Long id) throws DaoException {
        super.removeById(id);
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