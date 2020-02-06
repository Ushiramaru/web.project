package com.epam.conference.mapper.impl.dto;

import com.epam.conference.dto.AnswerDto;
import com.epam.conference.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerDtoRowMapper implements RowMapper<AnswerDto> {

    @Override
    public AnswerDto map(ResultSet resultSet) throws SQLException {
        Long answerId = resultSet.getLong(AnswerDto.ANSWER_ID);
        String answerContent = resultSet.getString(AnswerDto.ANSWER_CONTENT);

        String administratorName = resultSet.getString(AnswerDto.ADMINISTRATOR_NAME);

        return new AnswerDto(answerId, answerContent, administratorName);
    }

}