package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dto.RequestDto;
import com.epam.conference.entity.Request;

import java.util.List;

public interface RequestDao extends Dao<Request> {

    List<RequestDto> getAllDtoByUserId(Long id) throws DaoException;

    List<RequestDto> getAllDto() throws DaoException;

    void rejectById(Long id) throws DaoException;

    void acceptById(Long id) throws DaoException;

}