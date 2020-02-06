package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Request;
import com.epam.conference.entity.User;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CancelRequestCommand implements Command {

    private final RequestService requestService;

    public CancelRequestCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long requestId = Long.valueOf(request.getParameter("request_id"));

        Optional<Request> optionalRequest = requestService.getById(requestId);

        if (!optionalRequest.isPresent()) {
            throw new ServiceException("You doesn't have permission for this action");
        }
        Request userRequest = optionalRequest.get();

        if (!userRequest.getUserId().equals(user.getId())) {
            throw new ServiceException("You doesn't have permission for this action");
        }
        requestService.removeById(requestId);

        return CommandResult.redirect("controller?command=request");
    }

}