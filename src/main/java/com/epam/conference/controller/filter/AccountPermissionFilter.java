package com.epam.conference.controller.filter;

import com.epam.conference.entity.User;
import com.epam.conference.entity.enums.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class AccountPermissionFilter implements Filter {

    private final static Map<String, List<UserRole>> commandRoleMap = new HashMap<>();

    static {
        List<UserRole> userRoles = new ArrayList<>(Collections.singletonList(UserRole.ADMINISTRATOR));
        commandRoleMap.put("success", userRoles);
        commandRoleMap.put("answerAdmin", userRoles);
        commandRoleMap.put("userAdmin", userRoles);
        commandRoleMap.put("answerPage", userRoles);
        commandRoleMap.put("questionAdmin", userRoles);
        commandRoleMap.put("unblock", userRoles);
        commandRoleMap.put("block", userRoles);
        commandRoleMap.put("conferenceAdmin", userRoles);
        commandRoleMap.put("conferenceUnblock", userRoles);
        commandRoleMap.put("conferenceBlock", userRoles);
        commandRoleMap.put("conferenceInfoAdmin", userRoles);
        commandRoleMap.put("sectionEdit", userRoles);

        userRoles = new ArrayList<>(Collections.singletonList(UserRole.USER));
        commandRoleMap.put("applyPage", userRoles);
        commandRoleMap.put("apply", userRoles);
        commandRoleMap.put("request", userRoles);
        commandRoleMap.put("cancelRequest", userRoles);
        commandRoleMap.put("conference", userRoles);
        commandRoleMap.put("conferenceInfo", userRoles);
        commandRoleMap.put("question", userRoles);
        commandRoleMap.put("answer", userRoles);
        commandRoleMap.put("askPage", userRoles);
        commandRoleMap.put("ask", userRoles);

        userRoles = new ArrayList<>(Arrays.asList(UserRole.USER, UserRole.ADMINISTRATOR));
        commandRoleMap.put("success", userRoles);
        commandRoleMap.put("mainPage", userRoles);
        commandRoleMap.put("logout", userRoles);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserRole role;
        if (user != null) {
            role = user.getRole();
        } else {
            role = UserRole.UNREGISTERED;
        }

        String command = request.getParameter("command");
        if (commandRoleMap.containsKey(command)) {
            List<UserRole> userRoles = commandRoleMap.get(command);
            if (!userRoles.contains(role)) {
                ((HttpServletResponse) servletResponse).sendRedirect("/error.jsp");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}