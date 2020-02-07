package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.entity.Conference;

import java.util.List;

public interface ConferenceDao extends Dao<Conference> {

    List<Conference> getAllRelevant() throws DaoException;

}