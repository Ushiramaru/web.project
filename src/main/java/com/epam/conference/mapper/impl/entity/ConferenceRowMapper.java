package com.epam.conference.mapper.impl.entity;

import com.epam.conference.entity.Conference;
import com.epam.conference.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ConferenceRowMapper implements RowMapper<Conference> {

    @Override
    public Conference map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Conference.ID);
        String name = resultSet.getString(Conference.NAME);
        Date startDate = resultSet.getTimestamp(Conference.START_DATE);
        Date endDate = resultSet.getTimestamp(Conference.END_DATE);
        boolean isRelevant = resultSet.getBoolean(Conference.IS_RELEVANT);
        String imagePath = resultSet.getString(Conference.IMG);

        return new Conference(id, name, startDate, endDate, isRelevant, imagePath);
    }

}