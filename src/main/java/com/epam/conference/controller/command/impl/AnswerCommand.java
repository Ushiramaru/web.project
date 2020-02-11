package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.dto.AnswerDto;
import com.epam.conference.entity.Question;
import com.epam.conference.entity.User;
import com.epam.conference.service.AnswerService;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AnswerCommand implements Command {

    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String QUESTION_ID_PARAMETER_NAME = "question_id";
    private static final String ANSWER_ATTRIBUTE_NAME = "answer";
    private static final String JSP = "/WEB-INF/userAnswer.jsp";

    private final AnswerService answerService;
    private final QuestionService questionService;

    public AnswerCommand(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
        Long questionId = Long.valueOf(extractor.extractParameter(request, QUESTION_ID_PARAMETER_NAME));
//TODO
        Optional<Question> optionalQuestion = questionService.getById(questionId);
        if (!optionalQuestion.isPresent()) {
            throw new ServiceException("You doesn't have permission for this action");
        }
        Question question = optionalQuestion.get();

        if (!question.getUserId().equals(user.getId())) {
            throw new ServiceException("You doesn't have permission for this action");
        }

        Optional<AnswerDto> optionalAnswer = answerService.getById(question.getAnswerId());
        if (!optionalAnswer.isPresent()) {
            throw new ServiceException("Question hasn't answer");
        }

        request.setAttribute(ANSWER_ATTRIBUTE_NAME, optionalAnswer.get());

        return CommandResult.forward(JSP);
    }

}