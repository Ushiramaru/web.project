package com.epam.conference.controller.command.impl.user;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Question;
import com.epam.conference.entity.User;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class QuestionCommand implements Command {

    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String QUESTIONS_ATTRIBUTE_NAME = "questions";
    private final static String QUESTION_JSP = "/WEB-INF/question.jsp";

    private final QuestionService questionService;

    public QuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);

        List<Question> questions = questionService.getAllByUserId(user.getId());
        request.setAttribute(QUESTIONS_ATTRIBUTE_NAME, questions);

        return CommandResult.forward(QUESTION_JSP);
    }

}