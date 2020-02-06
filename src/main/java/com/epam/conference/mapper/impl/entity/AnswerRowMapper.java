package com.epam.conference.mapper.impl.entity;

import com.epam.conference.entity.Answer;
import com.epam.conference.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerRowMapper implements RowMapper<Answer> {

    @Override
    public Answer map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Answer.ID);
        Long administratorId = resultSet.getLong(Answer.ADMINISTRATOR_ID);
        String content = resultSet.getString(Answer.CONTENT);

        return new Answer(id, administratorId, content);
    }

}