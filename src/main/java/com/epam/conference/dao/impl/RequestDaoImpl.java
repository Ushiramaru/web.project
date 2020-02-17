package com.epam.conference.dao.impl;

import com.epam.conference.dao.RequestDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dto.RequestDto;
import com.epam.conference.entity.Request;
import com.epam.conference.entity.enums.RequestState;
import com.epam.conference.mapper.impl.dto.RequestDtoRowMapper;

import java.sql.Connection;
import java.util.List;

public class RequestDaoImpl extends AbstractDao<Request> implements RequestDao {

    private static final String SAVE_QUERY = "insert into request (section_id, user_id, topic) VALUES (?, ?, ?)";
    private static final String SET_STATE_QUERY = "update request set state = ? where id = ?";
    private static final String ALL_DTO_BY_USER_ID_QUERY = "select * from request " +
            "inner join section " +
            "on request.section_id = section.id and request.user_id = ? " +
            "inner join conference " +
            "on section.conference_id = conference.id";
    private static final String ALL_DTO_QUERY = "select * from request " +
            "inner join section " +
            "on request.section_id = section.id " +
            "inner join conference " +
            "on section.conference_id = conference.id";

    public RequestDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long save(Request item) throws DaoException {
        return super.executeUpdateQuery(SAVE_QUERY, item.getSectionId(), item.getUserId(), item.getTopic());
    }

    @Override
    public List<RequestDto> getAllDtoByUserId(Long id) throws DaoException {
        return super.executeJoinQuery(ALL_DTO_BY_USER_ID_QUERY, new RequestDtoRowMapper(), id);
    }

    @Override
    public List<RequestDto> getAllDto() throws DaoException {
        return super.executeJoinQuery(ALL_DTO_QUERY, new RequestDtoRowMapper());
    }

    @Override
    public void rejectById(Long id) throws DaoException {
        super.executeUpdateQuery(SET_STATE_QUERY, RequestState.REJECTED.toString(), id);
    }

    @Override
    public void acceptById(Long id) throws DaoException {
        super.executeUpdateQuery(SET_STATE_QUERY, RequestState.ACCEPTED.toString(), id);
    }

    @Override
    protected String getTableName() {
        return Request.TABLE;
    }

}