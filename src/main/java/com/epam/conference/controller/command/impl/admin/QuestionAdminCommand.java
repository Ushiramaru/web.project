package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Question;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuestionAdminCommand implements Command {

    private final static String QUESTIONS_ATTRIBUTE_NAME = "questions";
    private final static String QUESTION_JSP = "/WEB-INF/question.jsp";

    private final QuestionService questionService;

    public QuestionAdminCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Question> questions = questionService.getAll();
        request.setAttribute(QUESTIONS_ATTRIBUTE_NAME, questions);

        return CommandResult.forward(QUESTION_JSP);
    }

}