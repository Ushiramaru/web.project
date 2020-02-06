package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Question;
import com.epam.conference.entity.User;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AskCommand implements Command {

    private final QuestionService questionService;

    public AskCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String content = request.getParameter("content");

        Question question = new Question(null, user.getId(), null, content);
        questionService.save(question);

        return CommandResult.redirect("controller?command=success");
    }

}