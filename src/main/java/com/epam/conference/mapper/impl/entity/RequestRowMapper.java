package com.epam.conference.mapper.impl.entity;

import com.epam.conference.entity.Request;
import com.epam.conference.entity.enums.RequestState;
import com.epam.conference.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestRowMapper implements RowMapper<Request> {

    @Override
    public Request map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Request.ID);
        Long sectionId = resultSet.getLong(Request.SECTION_ID);
        Long userId = resultSet.getLong(Request.USER_ID);
        String topic = resultSet.getString(Request.TOPIC);
        RequestState state = RequestState.valueOf(resultSet.getString(Request.STATE));

        return new Request(id, sectionId, userId, topic, state);
    }

}