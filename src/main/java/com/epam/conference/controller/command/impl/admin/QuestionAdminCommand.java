package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.entity.Question;
import com.epam.conference.service.QuestionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class QuestionAdminCommand extends AbstractCommand {

    private final static Long FIRST_PAGE_NUMBER = 1L;
    private final static String QUESTIONS_ATTRIBUTE_NAME = "questions";
    private final static String QUESTION_JSP = "/WEB-INF/question.jsp";
    private final static String PAGE_NUMBER_PARAMETER_NAME = "pageNumber";
    private final static String PAGE_SIZE_ATTRIBUTE_NAME = "pageSize";
    private final static String PAGE_COUNT_ATTRIBUTE_NAME = "pageCount";
    private final static String ACTIVE_PAGE_ATTRIBUTE_NAME = "activePage";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final QuestionService questionService;

    public QuestionAdminCommand(RequestParameterExtractor parameterExtractor,
                                RequestParameterValidator parameterValidator, QuestionService questionService) {
        super(parameterExtractor, parameterValidator);
        this.questionService = questionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        HttpSession session = request.getSession();
        long pageSize = (long) session.getAttribute(PAGE_SIZE_ATTRIBUTE_NAME);
        Long pageNumber = extractor.extractLongOrSpecified(request, PAGE_NUMBER_PARAMETER_NAME, FIRST_PAGE_NUMBER);
        if (!validator.isValid(PAGE_NUMBER_PARAMETER_NAME, pageNumber)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", PAGE_NUMBER_PARAMETER_NAME));
        }
        List<Question> questions = questionService.getElementsByPage(pageNumber - 1, pageSize);
        request.setAttribute(QUESTIONS_ATTRIBUTE_NAME, questions);
        long elementsCount = questionService.getElementsCount();
        long pageCount = elementsCount / pageSize;
        if (Math.floorMod(elementsCount, pageSize) != 0) {
            pageCount++;
        }
        request.setAttribute(PAGE_COUNT_ATTRIBUTE_NAME, pageCount);
        request.setAttribute(ACTIVE_PAGE_ATTRIBUTE_NAME, pageNumber);

        return CommandResult.forward(QUESTION_JSP);
    }

}