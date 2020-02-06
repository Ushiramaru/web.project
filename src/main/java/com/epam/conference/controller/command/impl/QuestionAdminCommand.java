package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Question;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuestionAdminCommand implements Command {

    private final QuestionService questionService;

    public QuestionAdminCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Question> questions = questionService.getAll();
        request.setAttribute("questions", questions);

        return CommandResult.forward("/WEB-INF/question.jsp");
    }

}