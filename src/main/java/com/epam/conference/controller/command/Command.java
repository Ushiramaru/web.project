package com.epam.conference.controller.command;

import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public interface Command {

    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

}