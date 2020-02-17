package com.epam.conference.controller.command.impl.user;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.entity.Request;
import com.epam.conference.entity.User;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class CancelRequestCommand implements Command {

    private final static String REQUEST_NOT_FOUND_MESSAGE = "specified request doesn't exist or relevant";
    private final static String FORBIDDEN_MESSAGE = "user doesn't have permission for this action";
    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String REQUEST_ID_PARAMETER_NAME = "request_id";
    private final static String CONTROLLER_COMMAND_REQUEST = "controller?command=request";

    private final RequestService requestService;

    public CancelRequestCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
        Long requestId = Long.valueOf(extractor.extractParameter(request, REQUEST_ID_PARAMETER_NAME));

        Optional<Request> optionalRequest = requestService.getById(requestId);

        if (!optionalRequest.isPresent()) {
            throw new ServiceException(REQUEST_NOT_FOUND_MESSAGE);
        }
        Request userRequest = optionalRequest.get();

        if (!userRequest.getUserId().equals(user.getId())) {
            throw new ServiceException(FORBIDDEN_MESSAGE);
        }
        requestService.removeById(requestId);

        return CommandResult.redirect(CONTROLLER_COMMAND_REQUEST);
    }

}