package com.epam.conference.controller.command.impl.user;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.entity.Question;
import com.epam.conference.entity.User;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AskCommand extends AbstractCommand {

    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String CONTENT_PARAMETER_NAME = "content";
    private final static String CONTROLLER_COMMAND_SUCCESS = "controller?command=success";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final QuestionService questionService;

    public AskCommand(RequestParameterExtractor parameterExtractor, RequestParameterValidator parameterValidator,
                      QuestionService questionService) {
        super(parameterExtractor, parameterValidator);
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
        String content = extractor.extractString(request, CONTENT_PARAMETER_NAME);
        if (!validator.isValid(CONTENT_PARAMETER_NAME, content)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", CONTENT_PARAMETER_NAME));
        }

        Question question = new Question(null, user.getId(), null, content);
        questionService.save(question);

        return CommandResult.redirect(CONTROLLER_COMMAND_SUCCESS);
    }

}