package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.entity.Answer;
import com.epam.conference.entity.User;
import com.epam.conference.service.AnswerService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AnswerAdminCommand implements Command {

    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String QUESTION_ID_PARAMETER_NAME = "question_id";
    private static final String CONTENT_PARAMETER_NAME = "content";
    private static final String CONTROLLER_COMMAND_SUCCESS = "controller?command=success";

    private final AnswerService answerService;

    public AnswerAdminCommand(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
        Long questionId = Long.valueOf(extractor.extractParameter(request, QUESTION_ID_PARAMETER_NAME));
        String content = extractor.extractParameter(request, CONTENT_PARAMETER_NAME);
        Answer answer = new Answer(null, user.getId(), content);
        answerService.save(answer, questionId);

        return CommandResult.redirect(CONTROLLER_COMMAND_SUCCESS);
    }

}