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

public class ApplyCommand implements Command {

    private final RequestService requestService;

    public ApplyCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long sectionId = Long.valueOf(request.getParameter("section_id"));
        String topic = request.getParameter("topic");

        Request newRequest = new Request(null, sectionId, user.getId(), topic, null);
        requestService.save(newRequest);

        return CommandResult.redirect("controller?command=success");
    }

}