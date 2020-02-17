package com.epam.conference.service;

import com.epam.conference.dto.RequestDto;
import com.epam.conference.entity.Request;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;

public interface RequestService extends Service<Request> {

    Long save(Request request) throws ServiceException;

    List<RequestDto> getAllWithFullInfo() throws ServiceException;

    void removeById(Long id) throws ServiceException;

    void rejectById(Long id) throws ServiceException;

    void acceptById(Long id) throws ServiceException;

    List<RequestDto> getAllWithFullInfoByUserId(Long id) throws ServiceException;

}