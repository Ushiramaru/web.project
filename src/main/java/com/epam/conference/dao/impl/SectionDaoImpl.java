package com.epam.conference.dao.impl;

import com.epam.conference.dao.SectionDao;
import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.Section;
import com.epam.conference.mapper.impl.entity.SectionRowMapper;

import java.sql.Connection;
import java.util.List;

public class SectionDaoImpl extends AbstractDao<Section> implements SectionDao {

    private final static String SAVE_QUERY = "insert into section (conference_id, topic) VALUES (?, ?)";
    private final static String FIND_BY_CONFERENCE_ID = "select * from section where conference_id = ?";
    private final static String EDIT_BY_ID_QUERY = "update section set topic = ? where id = ?";

    public SectionDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Section> getAllByConferenceId(Long id) throws DaoException {
        return super.executeQuery(FIND_BY_CONFERENCE_ID, new SectionRowMapper(), id);
    }

    @Override
    public void editById(Long sectionId, Object... params) throws DaoException {
        super.executeUpdateQuery(EDIT_BY_ID_QUERY, params[0], sectionId);
    }

    @Override
    public Long save(Section item) throws DaoException {
        return super.executeUpdateQuery(SAVE_QUERY, item.getConferenceId(), item.getTopic());
    }

    @Override
    protected String getTableName() {
        return Section.TABLE;
    }

}