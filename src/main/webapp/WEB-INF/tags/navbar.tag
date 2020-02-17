<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <label class="navbar-brand">Conference</label>
    <button id="navbar-button" class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <c:if test="${sessionScope.containsKey('user')}">
                <c:set value="${sessionScope.user.role}" var="role"/>
            </c:if>
            <c:if test="${!sessionScope.containsKey('user')}">
                <c:set value="UNREGISTERED" var="role"/>
            </c:if>
            <c:choose>
                <c:when test="${role == 'UNREGISTERED'}">
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=loginPage">
                            <fmt:message key="nav.login" bundle="${locale}"/>
                        </a>
                    </li>
                </c:when>
                <c:when test="${role == 'ADMINISTRATOR'}">
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
                <c:when test="${role == 'USER'}">
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
            <c:if test="${role != 'UNREGISTERED'}">
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=logout">
                        <fmt:message key="nav.exit" bundle="${locale}"/>
                    </a>
                </li>
            </c:if>
            <button id="local-button" class="navbar-toggler" type="button" data-toggle="collapse" data-target="#localCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="nav-local collapse" id="localCollapse">
                <c:if test="${requestScope.containsKey('javax.servlet.forward.query_string')}">
                    <c:set value="${requestScope.get('javax.servlet.forward.query_string')}" var="back"/>
                    <jsp:useBean id="back" type="java.lang.String"/>
                    <c:set value="${back.replace('&', '__')}" var="back"/>
                </c:if>
                <c:if test="${!requestScope.containsKey('javax.servlet.forward.query_string')}">
                    <c:set value="command=mainPage" var="back"/>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=language&l=ru_RU&back=<c:out value="${back}"/>">
                        RU
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=language&l=en_EN&back=<c:out value="${back}"/>">
                        EN
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="controller?command=language&l=by_BY&back=<c:out value="${back}"/>">
                        BY
                    </a>
                </li>
            </div>
        </ul>
    </div>
</nav>