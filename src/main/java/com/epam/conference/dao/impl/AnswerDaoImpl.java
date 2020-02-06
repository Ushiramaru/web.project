package com.epam.conference.dao.impl;

import com.epam.conference.dao.AnswerDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dto.AnswerDto;
import com.epam.conference.entity.Answer;
import com.epam.conference.mapper.impl.dto.AnswerDtoRowMapper;

import java.sql.Connection;
import java.util.Optional;

public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {

    private static final String SAVE_QUERY = "insert into answer (administrator_id, content) VALUES (?, ?)";
    private static final String DTO_BY_ID_QUERY = "select * from answer " +
            "inner join user " +
            "on answer.administrator_id = user.id and answer.id = ? ";

    public AnswerDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<AnswerDto> getFullInfoById(Long id) throws DaoException {
        return super.executeJoinForSingleResult(DTO_BY_ID_QUERY, new AnswerDtoRowMapper(), id);
    }

    @Override
    protected String getTableName() {
        return Answer.TABLE;
    }

    @Override
    public Long save(Answer item) throws DaoException {
        return super.executeUpdateQuery(SAVE_QUERY, item.getAdministratorId(), item.getContent());
    }

}