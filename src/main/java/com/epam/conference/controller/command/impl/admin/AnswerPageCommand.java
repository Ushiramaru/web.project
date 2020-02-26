package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.dto.QuestionDto;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AnswerPageCommand extends AbstractCommand {

    private final static String QUESTION_ID_PARAMETER_NAME = "question_id";
    private final static String QUESTION_ATTRIBUTE_NAME = "question";
    private final static String QUESTION_NOT_FOUND_MESSAGE = "specified question doesn't exist";
    private final static String ADMIN_ANSWER_JSP = "/WEB-INF/adminAnswer.jsp";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final QuestionService questionService;

    public AnswerPageCommand(RequestParameterExtractor parameterExtractor, RequestParameterValidator parameterValidator,
                             QuestionService questionService) {
        super(parameterExtractor, parameterValidator);
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        Long questionId = extractor.extractLong(request, QUESTION_ID_PARAMETER_NAME);
        if (!validator.isValid(QUESTION_ID_PARAMETER_NAME, questionId)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", QUESTION_ID_PARAMETER_NAME));
        }
        Optional<QuestionDto> question;
        question = questionService.getFullInfoById(questionId);
        if (!question.isPresent()) {
            throw new ServiceException(QUESTION_NOT_FOUND_MESSAGE);
        }
        request.setAttribute(QUESTION_ATTRIBUTE_NAME, question.get());

        return CommandResult.forward(ADMIN_ANSWER_JSP);
    }

}