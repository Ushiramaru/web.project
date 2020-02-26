package com.epam.conference.controller.command.impl.user;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.dto.AnswerDto;
import com.epam.conference.entity.User;
import com.epam.conference.service.AnswerService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AnswerCommand extends AbstractCommand {

    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String QUESTION_ID_PARAMETER_NAME = "question_id";
    private final static String ANSWER_ATTRIBUTE_NAME = "answer";
    private final static String ANSWER_NOT_FOUND_MESSAGE = "specified answer doesn't exist or relevant";
    private final static String USER_ANSWER_JSP = "/WEB-INF/userAnswer.jsp";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final AnswerService answerService;

    public AnswerCommand(RequestParameterExtractor parameterExtractor, RequestParameterValidator parameterValidator,
                         AnswerService answerService) {
        super(parameterExtractor, parameterValidator);
        this.answerService = answerService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
        Long questionId = extractor.extractLong(request, QUESTION_ID_PARAMETER_NAME);
        if (!validator.isValid(QUESTION_ID_PARAMETER_NAME, questionId)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", QUESTION_ID_PARAMETER_NAME));
        }

        Optional<AnswerDto> optionalAnswer;
        optionalAnswer = answerService.getByQuestionAndUserId(questionId, user.getId());
        if (!optionalAnswer.isPresent()) {
            throw new ServiceException(ANSWER_NOT_FOUND_MESSAGE);
        }

        request.setAttribute(ANSWER_ATTRIBUTE_NAME, optionalAnswer.get());

        return CommandResult.forward(USER_ANSWER_JSP);
    }

}