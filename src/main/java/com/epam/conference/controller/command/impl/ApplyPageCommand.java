package com.epam.conference.controller.command.impl;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.entity.Section;
import com.epam.conference.service.SectionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ApplyPageCommand implements Command {

    private final SectionService sectionService;

    public ApplyPageCommand(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long sectionId = Long.valueOf(request.getParameter("section_id"));
        Optional<Section> section = sectionService.getById(sectionId);
        if (!section.isPresent()) {
            throw new ServiceException("specified section doesn't exist");
        }
        request.setAttribute("section", section.get());

        return CommandResult.forward("/WEB-INF/apply.jsp");
    }

}