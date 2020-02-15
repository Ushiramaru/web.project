package com.epam.conference.controller.command.impl.user;

import com.epam.conference.controller.command.Command;
import com.epam.conference.controller.command.CommandResult;
import com.epam.conference.controller.command.ParameterExtractor;
import com.epam.conference.entity.Section;
import com.epam.conference.service.SectionService;
import com.epam.conference.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ApplyPageCommand implements Command {

    private final static String SECTION_ID_ATTRIBUTE_NAME = "section_id";
    private final static String SECTION_NOT_FOUND_MESSAGE = "specified section doesn't exist or relevant";
    private final static String SECTION_ATTRIBUTE_NAME = "section";
    private final static String APPLY_JSP = "/WEB-INF/apply.jsp";

    private final SectionService sectionService;

    public ApplyPageCommand(SectionService sectionService) {
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
        request.setAttribute(SECTION_ATTRIBUTE_NAME, optionalSection.get());

        return CommandResult.forward(APPLY_JSP);
    }

}