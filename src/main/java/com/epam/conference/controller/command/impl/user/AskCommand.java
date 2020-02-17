package com.epam.conference.controller.command.impl.user;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.entity.Question;
import com.epam.conference.entity.User;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AskCommand implements Command {

    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String CONTENT_PARAMETER_NAME = "content";
    private final static String CONTROLLER_COMMAND_SUCCESS = "controller?command=success";

    private final QuestionService questionService;

    public AskCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
        String content = extractor.extractParameter(request, CONTENT_PARAMETER_NAME);

        Question question = new Question(null, user.getId(), null, content);
        questionService.save(question);

        return CommandResult.redirect(CONTROLLER_COMMAND_SUCCESS);
    }

}