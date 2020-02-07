package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
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

    private final RequestService requestService;
    private final SectionService sectionService;

    public ApplyCommand(RequestService requestService, SectionService sectionService) {
        this.requestService = requestService;
        this.sectionService = sectionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long sectionId = Long.valueOf(request.getParameter("section_id"));
        Optional<Section> optionalSection = sectionService.getRelevantById(sectionId);
        if (!optionalSection.isPresent()) {
            throw new ServiceException("specified section doesn't exist or relevant");
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String topic = request.getParameter("topic");
        Request newRequest = new Request(null, sectionId, user.getId(), topic, null);
        requestService.save(newRequest);

        return CommandResult.redirect("controller?command=success");
    }

}