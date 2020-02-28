package com.epam.conference.controller.command.impl.admin;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.entity.Conference;
import com.epam.conference.service.ConferenceService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ConferenceAdminCommand extends AbstractCommand {

    private final static Long FIRST_PAGE_NUMBER = 1L;
    private final static String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private final static String CONFERENCE_ADMIN_JSP = "/WEB-INF/conferenceAdmin.jsp";
    private final static String PAGE_NUMBER_PARAMETER_NAME = "pageNumber";
    private final static String PAGE_SIZE_ATTRIBUTE_NAME = "pageSize";
    private final static String PAGE_COUNT_ATTRIBUTE_NAME = "pageCount";
    private final static String ACTIVE_PAGE_ATTRIBUTE_NAME = "activePage";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final ConferenceService service;

    public ConferenceAdminCommand(RequestParameterExtractor parameterExtractor,
                                  RequestParameterValidator parameterValidator, ConferenceService service) {
        super(parameterExtractor, parameterValidator);
        this.service = service;
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

        List<Conference> conferences = service.getElementsByPage(pageNumber - 1, pageSize);
        request.setAttribute(CONFERENCES_ATTRIBUTE_NAME, conferences);
        long elementsCount = service.getElementsCount();
        long pageCount = elementsCount / pageSize;
        if (Math.floorMod(elementsCount, pageSize) != 0) {
            pageCount++;
        }
        request.setAttribute(PAGE_COUNT_ATTRIBUTE_NAME, pageCount);
        request.setAttribute(ACTIVE_PAGE_ATTRIBUTE_NAME, pageNumber);

        return CommandResult.forward(CONFERENCE_ADMIN_JSP);
    }

}