package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
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

    private final AnswerService answerService;
    private final QuestionService questionService;

    public AnswerCommand(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long questionId = Long.valueOf(request.getParameter("question_id"));

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

        request.setAttribute("answer", optionalAnswer.get());

        return CommandResult.forward("/WEB-INF/userAnswer.jsp");
    }

}