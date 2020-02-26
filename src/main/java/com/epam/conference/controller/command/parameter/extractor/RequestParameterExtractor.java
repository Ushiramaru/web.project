package com.epam.conference.controller.command.parameter.extractor;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public interface RequestParameterExtractor {

    String extractString(HttpServletRequest request, String name);

    Long extractLong(HttpServletRequest request, String name);

    LocalDateTime extractDate(HttpServletRequest request, String name);

    String[] extractStrings(HttpServletRequest request, String name);

}