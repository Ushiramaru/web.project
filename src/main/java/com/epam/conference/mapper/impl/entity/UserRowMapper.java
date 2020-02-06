package com.epam.conference.mapper.impl.entity;

import com.epam.conference.entity.User;
import com.epam.conference.entity.enums.UserRole;
import com.epam.conference.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(User.ID);
        String name = resultSet.getString(User.NAME);
        UserRole isAdministrator = UserRole.valueOf(resultSet.getString(User.ROLE));
        boolean isActive = resultSet.getBoolean(User.IS_ACTIVE);

        return new User(id, name, isAdministrator, isActive);
    }

}