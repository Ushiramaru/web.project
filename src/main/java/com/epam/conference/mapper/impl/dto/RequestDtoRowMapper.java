package com.epam.conference.mapper.impl.dto;

import com.epam.conference.dto.RequestDto;
import com.epam.conference.entity.enums.RequestState;
import com.epam.conference.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestDtoRowMapper implements RowMapper<RequestDto> {

    @Override
    public RequestDto map(ResultSet resultSet) throws SQLException {
        Long requestId = resultSet.getLong(RequestDto.REQUEST_ID);
        String requestTopic = resultSet.getString(RequestDto.REQUEST_TOPIC);
        RequestState state = RequestState.valueOf(resultSet.getString(RequestDto.REQUEST_STATE));

        String sectionTopic = resultSet.getString(RequestDto.SECTION_TOPIC);

        Long conferenceId = resultSet.getLong(RequestDto.CONFERENCE_ID);
        String conferenceName = resultSet.getString(RequestDto.CONFERENCE_NAME);

        return new RequestDto(requestId, requestTopic, state, sectionTopic, conferenceId, conferenceName);
    }

}