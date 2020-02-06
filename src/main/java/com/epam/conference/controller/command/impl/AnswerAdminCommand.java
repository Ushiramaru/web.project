package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Answer;
import com.epam.conference.entity.User;
import com.epam.conference.service.AnswerService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AnswerAdminCommand implements Command {

    private final AnswerService answerService;

    public AnswerAdminCommand(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long questionId = Long.valueOf(request.getParameter("question_id"));
        String content = request.getParameter("content");

        Answer answer = new Answer(null, user.getId(), content);
        answerService.answer(answer, questionId);

        return CommandResult.redirect("controller?command=success");
    }

}