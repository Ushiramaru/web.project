package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.dto.QuestionDto;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AnswerPageCommand implements Command {

    public static final String QUESTION_ID_PARAMETER_NAME = "question_id";
    public static final String JSP = "/WEB-INF/adminAnswer.jsp";
    public static final String QUESTION_ATTRIBUTE_NAME = "question";
    private final QuestionService questionService;

    public AnswerPageCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        Long questionId = Long.valueOf(extractor.extractParameter(request, QUESTION_ID_PARAMETER_NAME));
        Optional<QuestionDto> question = questionService.getFullInfoById(questionId);
        if (!question.isPresent()) {
            throw new ServiceException("specified question doesn't exist");
        }
        request.setAttribute(QUESTION_ATTRIBUTE_NAME, question.get());

        return CommandResult.forward(JSP);
    }

}