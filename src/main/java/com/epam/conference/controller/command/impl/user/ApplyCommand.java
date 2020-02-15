package com.epam.conference.controller.command.impl.user;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
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

public class ApplyCommand implements Command {

    private final static String USER_ATTRIBUTE_NAME = "user";
    private final static String TOPIC_PARAMETER_NAME = "topic";
    private final static String SECTION_NOT_FOUND_MESSAGE = "specified section doesn't exist or relevant";
    private final static String SECTION_ID_ATTRIBUTE_NAME = "section_id";
    private final static String CONTROLLER_COMMAND_SUCCESS = "controller?command=success";

    private final RequestService requestService;
    private final SectionService sectionService;

    public ApplyCommand(RequestService requestService, SectionService sectionService) {
        this.requestService = requestService;
        this.sectionService = sectionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        Long sectionId = Long.valueOf(extractor.extractParameter(request, SECTION_ID_ATTRIBUTE_NAME));
        Optional<Section> optionalSection;
        optionalSection = sectionService.getRelevantById(sectionId);
        if (!optionalSection.isPresent()) {
            throw new ServiceException(SECTION_NOT_FOUND_MESSAGE);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
        String topic = extractor.extractParameter(request, TOPIC_PARAMETER_NAME);
        Request newRequest = new Request(null, sectionId, user.getId(), topic, null);
        requestService.save(newRequest);

        return CommandResult.redirect(CONTROLLER_COMMAND_SUCCESS);
    }

}