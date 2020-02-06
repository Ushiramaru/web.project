package com.epam.conference.mapper.impl.entity;

import com.epam.conference.entity.Section;
import com.epam.conference.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionRowMapper implements RowMapper<Section> {

    @Override
    public Section map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Section.ID);
        Long conferenceId = resultSet.getLong(Section.CONFERENCE_ID);
        String topic = resultSet.getString(Section.TOPIC);

        return new Section(id, conferenceId, topic);
    }

}