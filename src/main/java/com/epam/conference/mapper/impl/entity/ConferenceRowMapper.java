package com.epam.conference.mapper.impl.entity;

import com.epam.conference.entity.Conference;
import com.epam.conference.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ConferenceRowMapper implements RowMapper<Conference> {

    @Override
    public Conference map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Conference.ID);
        String name = resultSet.getString(Conference.NAME);
        LocalDateTime startDate = resultSet.getTimestamp(Conference.START_DATE).toLocalDateTime();
        LocalDateTime endDate = resultSet.getTimestamp(Conference.END_DATE).toLocalDateTime();
        boolean isRelevant = resultSet.getBoolean(Conference.IS_RELEVANT);

        return new Conference(id, name, startDate, endDate, isRelevant);
    }

}