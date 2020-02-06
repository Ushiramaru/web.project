package com.epam.conference.mapper;

import com.epam.conference.entity.*;
import com.epam.conference.mapper.impl.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> {

    T map(ResultSet resultSet) throws SQLException;

    static RowMapper<? extends Identifiable> create(String table) {
        switch (table) {
            case User.TABLE:
                return new UserRowMapper();
            case Answer.TABLE:
                return new AnswerRowMapper();
            case Conference.TABLE:
                return new ConferenceRowMapper();
            case Question.TABLE:
                return new QuestionRowMapper();
            case Request.TABLE:
                return new RequestRowMapper();
            case Section.TABLE:
                return new SectionRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table " + table);
        }
    }

}