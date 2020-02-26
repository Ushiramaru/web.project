package com.epam.conference.controller.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private String code;

    public void init(FilterConfig fConfig) {
        code = fConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (code != null) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }

        chain.doFilter(request, response);
    }

    public void destroy() {

    }

}