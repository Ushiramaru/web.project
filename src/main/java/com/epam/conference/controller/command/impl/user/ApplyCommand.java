package com.epam.conference.controller.command.impl.user;

import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.impl.AbstractCommand;
import com.epam.conference.controller.command.parameter.extractor.RequestParameterExtractor;
import com.epam.conference.controller.command.parameter.validator.RequestParameterValidator;
import com.epam.conference.entity.Request;
import com.epam.conference.entity.Section;
import com.epam.conference.entity.User;
import com.epam.conference.service.RequestService;
import com.epam.conference.service.SectionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ApplyCommand extends AbstractCommand {

    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String TOPIC_PARAMETER_NAME = "topic";
    private final static String SECTION_NOT_FOUND_MESSAGE = "specified section doesn't exist or relevant";
    private final static String SECTION_ID_PARAMETER_NAME = "section_id";
    private final static String CONTROLLER_COMMAND_SUCCESS = "controller?command=success";
    private final static String INVALID_PARAMETER_MESSAGE = "Invalid ? value";

    private final RequestService requestService;
    private final SectionService sectionService;

    public ApplyCommand(RequestParameterExtractor parameterExtractor, RequestParameterValidator parameterValidator,
                        RequestService requestService, SectionService sectionService) {
        super(parameterExtractor, parameterValidator);
        this.requestService = requestService;
        this.sectionService = sectionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        RequestParameterExtractor extractor = super.getParameterExtractor();
        RequestParameterValidator validator = super.getParameterValidator();
        Long sectionId = extractor.extractLong(request, SECTION_ID_PARAMETER_NAME);
        if (!validator.isValid(SECTION_ID_PARAMETER_NAME, sectionId)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", SECTION_ID_PARAMETER_NAME));
        }
        Optional<Section> optionalSection;
        optionalSection = sectionService.getRelevantById(sectionId);
        if (!optionalSection.isPresent()) {
            throw new ServiceException(SECTION_NOT_FOUND_MESSAGE);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
        String topic = extractor.extractString(request, TOPIC_PARAMETER_NAME);
        if (!validator.isValid(TOPIC_PARAMETER_NAME, topic)) {
            throw new ServiceException(INVALID_PARAMETER_MESSAGE.replace("?", TOPIC_PARAMETER_NAME));
        }
        Request newRequest = new Request(null, sectionId, user.getId(), topic, null);
        requestService.save(newRequest);

        return CommandResult.redirect(CONTROLLER_COMMAND_SUCCESS);
    }

}