package com.epam.conference.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StandardSessionAttributeInstallationFilter implements Filter {

    private final static String LANGUAGE_ATTRIBUTE_NAME = "language";
    private final static String PAGE_SIZE_ATTRIBUTE_NAME = "pageSize";
    private final static String LANGUAGE_INIT_PARAMETER_NAME = "language";
    private final static String PAGE_SIZE_INIT_PARAMETER_NAME = "pageSize";

    private String language;
    private Long pageSize;

    public void init(FilterConfig fConfig) {
        language = fConfig.getInitParameter(LANGUAGE_INIT_PARAMETER_NAME);
        pageSize = Long.valueOf(fConfig.getInitParameter(PAGE_SIZE_INIT_PARAMETER_NAME));
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(LANGUAGE_ATTRIBUTE_NAME, language);
        session.setAttribute(PAGE_SIZE_ATTRIBUTE_NAME, pageSize);

        chain.doFilter(request, response);
    }

    public void destroy() {

    }

}