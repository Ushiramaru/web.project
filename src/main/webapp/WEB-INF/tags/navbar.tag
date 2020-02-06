<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <a class="nav-link" href="controller?command=conferenceAdmin">Конференции</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=requestAdmin">Заявки</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=questionAdmin">Вопросы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=userAdmin">Пользователи</a>
                    </li>
                </c:when>
                <c:when test="${user.role eq 'USER'}">
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=conference">Конференции</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=request">Заявки</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=question">Вопросы</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=askPage">Задать вопрос</a>
                    </li>
                </c:when>
            </c:choose>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=logout">Выйти</a>
            </li>
            <c:if test="${user.role eq 'ADMINISTRATOR'}">

            </c:if>
        </ul>
    </div>
</nav>