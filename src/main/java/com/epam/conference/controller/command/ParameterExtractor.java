package com.epam.conference.controller.command;

import javax.servlet.http.HttpServletRequest;

public class ParameterExtractor {

    public String extractParameter(HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        if (param == null) {
            throw new IllegalArgumentException("Parameter " + name + " must be declared");
        }

        return param;
    }

    public String[] extractParameterValues(HttpServletRequest request, String name) {
        String[] param = request.getParameterValues(name);
        if (param == null) {
            throw new IllegalArgumentException("Parameter " + name + " must be declared");
        }

        return param;
    }

}
