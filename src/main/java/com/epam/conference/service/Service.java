package com.epam.conference.service;

import com.epam.conference.entity.Identifiable;
import com.epam.conference.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface Service<T extends Identifiable> {

    Optional<T> getById(Long id) throws ServiceException;

    List<T> getAll() throws ServiceException;

}