<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <label class="navbar-brand">Conference</label>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
            aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <jsp:useBean id="user" scope="session" type="com.epam.conference.entity.User"/>
            <c:choose>
                <c:when test="${user.role eq 'ADMINISTRATOR'}">
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=conferenceAdmin">
                            <fmt:message key="nav.conferences" bundle="${locale}"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=requestAdmin">
                            <fmt:message key="nav.requests" bundle="${locale}"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=questionAdmin">
                            <fmt:message key="nav.questions" bundle="${locale}"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=userAdmin">
                            <fmt:message key="nav.users" bundle="${locale}"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=createConferencePage">
                            <fmt:message key="nav.createConference" bundle="${locale}"/>
                        </a>
                    </li>
                </c:when>
                <c:when test="${user.role eq 'USER'}">
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=conference">
                            <fmt:message key="nav.conferences" bundle="${locale}"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=request">
                            <fmt:message key="nav.requests" bundle="${locale}"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=question">
                            <fmt:message key="nav.questions" bundle="${locale}"/>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=askPage">
                            <fmt:message key="nav.askQuestion" bundle="${locale}"/>
                        </a>
                    </li>
                </c:when>
            </c:choose>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=logout">
                    <fmt:message key="nav.exit" bundle="${locale}"/>
                </a>
            </li>
        </ul>
    </div>
</nav>