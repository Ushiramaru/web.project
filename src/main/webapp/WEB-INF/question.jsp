<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.questionPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <c:forEach var="question" items="${requestScope.questions}">
        <div class="card mt-2 text-success">
            <div class="card-body">
                <c:set value="${question.answerId != 0}" var="isAnswered"/>
                <h5 class="card-title">
                    <c:if test="${isAnswered}">
                        <fmt:message key="label.answered" bundle="${locale}" var="answerState"/>
                    </c:if>
                    <c:if test="${!isAnswered}">
                        <fmt:message key="label.notAnswered" bundle="${locale}" var="answerState"/>
                    </c:if>
                    <c:out value="${answerState}"/>
                </h5>
                <p class="card-text">
                    <fmt:message key="label.question" bundle="${locale}"/>:<c:out value="${question.content}"/>
                </p>
                <c:choose>
                    <c:when test="${sessionScope.user.role eq 'USER'}">
                        <c:if test="${isAnswered}">
                            <form action="controller" method="get">
                                <input type="hidden" name="command" value="answer">
                                <input type="hidden" name="question_id" value="<c:out value="${question.id}"/>">
                                <button class="btn btn-lg btn-primary btn-block btn-custom" type="submit">
                                    <fmt:message key="submit.viewAnswer" bundle="${locale}"/>
                                </button>
                            </form>
                        </c:if>
                    </c:when>
                    <c:when test="${sessionScope.user.role eq 'ADMINISTRATOR'}">
                        <c:if test="${!isAnswered}">
                            <form action="controller" method="get">
                                <input type="hidden" name="command" value="answerPage">
                                <input type="hidden" name="question_id" value="<c:out value="${question.id}"/>">
                                <button class="btn btn-lg btn-primary btn-block btn-custom" type="submit">
                                    <fmt:message key="submit.answer" bundle="${locale}"/>
                                </button>
                            </form>
                        </c:if>
                    </c:when>
                </c:choose>

            </div>
        </div>
    </c:forEach>
</u:htmlBase>