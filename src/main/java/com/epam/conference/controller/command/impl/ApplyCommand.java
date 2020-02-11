package com.epam.conference.controller.command.impl;

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

    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String TOPIC_PARAMETER_NAME = "topic";
    private static final String CONTROLLER_COMMAND_SUCCESS = "controller?command=success";

    private final RequestService requestService;
    private final SectionService sectionService;

    public ApplyCommand(RequestService requestService, SectionService sectionService) {
        this.requestService = requestService;
        this.sectionService = sectionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ParameterExtractor extractor = new ParameterExtractor();
        Long sectionId = Long.valueOf(extractor.extractParameter(request, "section_id"));
        Optional<Section> optionalSection = sectionService.getRelevantById(sectionId);
        if (!optionalSection.isPresent()) {
            throw new ServiceException("specified section doesn't exist or relevant");
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_ATTRIBUTE_NAME);
        String topic = extractor.extractParameter(request, TOPIC_PARAMETER_NAME);
        Request newRequest = new Request(null, sectionId, user.getId(), topic, null);
        requestService.save(newRequest);

        return CommandResult.redirect(CONTROLLER_COMMAND_SUCCESS);
    }

}