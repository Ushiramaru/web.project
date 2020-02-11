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
        List<UserRole> admin = Collections.singletonList(UserRole.ADMINISTRATOR);
        commandRoleMap.put("success", admin);
        commandRoleMap.put("answerAdmin", admin);
        commandRoleMap.put("userAdmin", admin);
        commandRoleMap.put("answerPage", admin);
        commandRoleMap.put("questionAdmin", admin);
        commandRoleMap.put("unblock", admin);
        commandRoleMap.put("block", admin);
        commandRoleMap.put("conferenceAdmin", admin);
        commandRoleMap.put("conferenceUnblock", admin);
        commandRoleMap.put("conferenceBlock", admin);
        commandRoleMap.put("conferenceInfoAdmin", admin);
        commandRoleMap.put("sectionEdit", admin);
        commandRoleMap.put("requestAdmin", admin);
        commandRoleMap.put("requestReject", admin);
        commandRoleMap.put("requestAccept", admin);
        commandRoleMap.put("createConferencePage", admin);
        commandRoleMap.put("createConference", admin);

        List<UserRole> user = Collections.singletonList(UserRole.USER);
        commandRoleMap.put("applyPage", user);
        commandRoleMap.put("apply", user);
        commandRoleMap.put("request", user);
        commandRoleMap.put("cancelRequest", user);
        commandRoleMap.put("conference", user);
        commandRoleMap.put("conferenceInfo", user);
        commandRoleMap.put("question", user);
        commandRoleMap.put("answer", user);
        commandRoleMap.put("askPage", user);
        commandRoleMap.put("ask", user);

        List<UserRole> userAdmin = Arrays.asList(UserRole.USER, UserRole.ADMINISTRATOR);
        commandRoleMap.put("success", userAdmin);
        commandRoleMap.put("mainPage", userAdmin);
        commandRoleMap.put("logout", userAdmin);
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