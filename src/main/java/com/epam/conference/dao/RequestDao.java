package com.epam.conference.dao;

import com.epam.conference.dao.exception.DaoException;
import com.epam.conference.dto.RequestDto;
import com.epam.conference.entity.Request;

import java.util.List;

/**
 * The interface Request dao.
 *
 * @see Request
 * @see RequestDto
 */
public interface RequestDao extends Dao<Request> {

    /**
     * Gets all {@link RequestDto} for specified {@link com.epam.conference.entity.User} by user's id.
     *
     * @param id the id of {@link com.epam.conference.entity.User}
     * @return the all {@link RequestDto} of {@link Request} elements by user's id from storage
     * @throws DaoException when there are problems with the data storage
     * @see RequestDto
     */
    List<RequestDto> getAllWithFullInfoByUserId(Long id) throws DaoException;

    /**
     * Gets all {@link RequestDto} from storage.
     *
     * @return the all {@link RequestDto} from storage
     * @throws DaoException when there are problems with the data storage
     * @see RequestDto
     */
    List<RequestDto> getAllWithFullInfo() throws DaoException;

    /**
     * Reject {@link Request} by id.
     *
     * @param id the id of {@link Request} element
     * @throws DaoException when there are problems with the data storage
     * @see Request#getState()
     */
    void rejectById(Long id) throws DaoException;

    /**
     * Accept {@link Request} by id.
     *
     * @param id the id of {@link Request} element
     * @throws DaoException when there are problems with the data storage
     * @see Request#getState()
     */
    void acceptById(Long id) throws DaoException;

}