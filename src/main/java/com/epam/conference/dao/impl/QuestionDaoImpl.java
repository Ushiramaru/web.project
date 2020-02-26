package com.epam.conference.dao.impl;

import com.epam.conference.dao.QuestionDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dto.QuestionDto;
import com.epam.conference.entity.Question;
import com.epam.conference.mapper.impl.dto.QuestionDtoRowMapper;
import com.epam.conference.mapper.impl.entity.QuestionRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

    private final static String SAVE_QUERY = "insert into question (user_id, content) VALUES (?, ?)";
    private final static String FIND_BY_USER_ID = "select * from question where user_id = ?";
    private final static String DTO_BY_ID_QUERY = "select * from question " +
            "inner join user " +
            "on question.user_id = user.id and question.id = ? ";
    private final static String ANSWER_QUERY = "update question set answer_id = ? WHERE id = ?";

    public QuestionDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Question> getAllByUserId(Long id) throws DaoException {
        return super.executeQuery(FIND_BY_USER_ID, new QuestionRowMapper(), id);
    }

    @Override
    public Optional<QuestionDto> getFullInfoById(Long id) throws DaoException {
        return super.executeJoinForSingleResult(DTO_BY_ID_QUERY, new QuestionDtoRowMapper(), id);
    }

    @Override
    public void answer(Long questionId, Long answerId) throws DaoException {
        super.executeUpdateQuery(ANSWER_QUERY, answerId, questionId);
    }

    @Override
    public Long save(Question item) throws DaoException {
        return super.executeUpdateQuery(SAVE_QUERY, item.getUserId(), item.getContent());
    }

    @Override
    protected String getTableName() {
        return Question.TABLE;
    }

}