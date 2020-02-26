<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.answerAdminPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <jsp:useBean id="question" scope="request" type="com.epam.conference.dto.QuestionDto"/>
    <div class="card mt-2 text-success">
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
            <form class="form-signin" action="controller" method="post">
                <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="label.answer" bundle="${locale}"/></h1>
                <input type="hidden" name="command" value="answerAdmin">
                <input type="hidden" name="question_id" value="<c:out value="${question.id}"/>">
                <label for="inputAnswer" class="sr-only"><fmt:message key="label.answer" bundle="${locale}"/></label>
                <textarea type="text" name="content" id="inputAnswer" class="form-control" maxlength="50" required>
                </textarea>
                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    <fmt:message key="submit.answer" bundle="${locale}"/>
                </button>
            </form>
        </div>
    </div>
</u:htmlBase>