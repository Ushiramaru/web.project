package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.dto.QuestionDto;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AnswerPageCommand implements Command {

    private final QuestionService questionService;

    public AnswerPageCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long questionId = Long.valueOf(request.getParameter("question_id"));
        Optional<QuestionDto> question = questionService.getFullInfoById(questionId);
        if (!question.isPresent()) {
            throw new ServiceException("specified question doesn't exist");
        }
        request.setAttribute("question", question.get());

        return CommandResult.forward("/WEB-INF/adminAnswer.jsp");
    }

}