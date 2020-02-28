package com.epam.conference.controller.command.parameter.extractor;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class RequestParameterExtractorImpl implements RequestParameterExtractor {

    @Override
    public String extractString(HttpServletRequest request, String name) {
        return extractParameter(request, name);
    }

    @Override
    public Long extractLong(HttpServletRequest request, String name) {
        String value = extractParameter(request, name);

        return Long.valueOf(value);
    }

    @Override
    public LocalDateTime extractDate(HttpServletRequest request, String name) {
        String value = extractParameter(request, name);

        return LocalDateTime.parse(value);
    }

    @Override
    public String[] extractStrings(HttpServletRequest request, String name) {
        return extractParameters(request, name);
    }

    @Override
    public Long extractLongOrSpecified(HttpServletRequest request, String name, Long defaultValue) {
        String value = request.getParameter(name);
        if (value == null) {
            return defaultValue;
        }

        return Long.valueOf(value);
    }

    private String extractParameter(HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        if (param == null) {
            throw new IllegalArgumentException("Parameter " + name + " must be declared");
        }

        return param;
    }

    private String[] extractParameters(HttpServletRequest request, String name) {
        String[] param = request.getParameterValues(name);
        if (param == null) {
            throw new IllegalArgumentException("Parameter " + name + " must be declared");
        }

        return param;
    }

}