package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.entity.Answer;
import com.epam.conference.entity.User;
import com.epam.conference.service.AnswerService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AnswerAdminCommand extends AbstractCommand {

    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String QUESTION_ID_PARAMETER_NAME = "question_id";
    private final static String CONTENT_PARAMETER_NAME = "content";
    private final static String CONTROLLER_COMMAND_SUCCESS = "controller?command=success";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final AnswerService answerService;

    public AnswerAdminCommand(RequestParameterExtractor parameterExtractor,
                              RequestParameterValidator parameterValidator, AnswerService answerService) {
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
        String content = extractor.extractString(request, CONTENT_PARAMETER_NAME);
        if (!validator.isValid(CONTENT_PARAMETER_NAME, content)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", CONTENT_PARAMETER_NAME));
        }
        Answer answer = new Answer(null, user.getId(), content);
        answerService.save(answer, questionId);

        return CommandResult.redirect(CONTROLLER_COMMAND_SUCCESS);
    }

}