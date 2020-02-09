<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.answerAdminPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <main role="main" class="flex-shrink-0">
        <jsp:useBean id="question" scope="request" type="com.epam.conference.dto.QuestionDto"/>
        <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
            <div class="card-body">
                <h5 class="card-title">
                    <fmt:message key="label.question" bundle="${locale}"/>: <c:out value="${question.questionContent}"/>
                </h5>
                <p class="card-text">
                    <fmt:message key="label.askQuestion" bundle="${locale}"/>: <c:out value="${question.userName}"/>
                </p>
                <p class="card-text"><fmt:message key="label.userRole" bundle="${locale}"/>:
                    <c:choose>
                        <c:when test="${question.userRole eq 'ADMINISTRATOR'}">
                            <fmt:message key="label.roleAdmin" bundle="${locale}"/>
                        </c:when>
                        <c:when test="${question.userRole eq 'USER'}">
                            <fmt:message key="label.roleUser" bundle="${locale}"/>
                        </c:when>
                    </c:choose>
                </p>
                <form action="controller" method="post">
                    <input name="command" value="answerAdmin" type="hidden">
                    <input name="question_id" value="<c:out value="${question.id}"/>" type="hidden">
                    <label><fmt:message key="label.answer" bundle="${locale}"/>:
                        <input name="content" value="" type="text">
                    </label>
                    <input type="submit" value="<fmt:message key="submit.answer" bundle="${locale}"/>">
                </form>
            </div>
        </div>
    </main>
</u:htmlBase>