package com.epam.conference.mapper.impl.dto;

import com.epam.conference.dto.QuestionDto;
import com.epam.conference.entity.enums.UserRole;
import com.epam.conference.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDtoRowMapper implements RowMapper<QuestionDto> {

    @Override
    public QuestionDto map(ResultSet resultSet) throws SQLException {
        Long questionId = resultSet.getLong(QuestionDto.QUESTION_ID);
        String questionContent = resultSet.getString(QuestionDto.QUESTION_CONTENT);

        String userName = resultSet.getString(QuestionDto.USER_NAME);
        UserRole userRole = UserRole.valueOf(resultSet.getString(QuestionDto.USER_ROLE));

        return new QuestionDto(questionId, questionContent, userName, userRole);
    }

}