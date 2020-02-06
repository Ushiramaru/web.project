package com.epam.conference.mapper.impl.entity;

import com.epam.conference.entity.Question;
import com.epam.conference.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {

    @Override
    public Question map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Question.ID);
        Long userId = resultSet.getLong(Question.USER_ID);
        Long answerId = resultSet.getLong(Question.ANSWER_ID);
        String content = resultSet.getString(Question.CONTENT);

        return new Question(id, userId, answerId, content);
    }

}