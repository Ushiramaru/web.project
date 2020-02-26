package com.epam.conference.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StandardLocaleInstallationFilter implements Filter {

    private String language;

    public void init(FilterConfig fConfig) {
        language = fConfig.getInitParameter("language");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("language", language);

        chain.doFilter(request, response);
    }

    public void destroy() {

    }

}