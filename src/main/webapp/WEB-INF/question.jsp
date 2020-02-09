<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<%--    TODO title <fmt:message key="title.questionPage" bundle="${locale}"/>--%>
<u:htmlBase title="Question Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <c:forEach var="question" items="${requestScope.questions}">
                <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
                    <div class="card-body">
                        <c:set value="${question.answerId != 0}" var="isAnswered"/>
                        <h5 class="card-title">
<%--                            TODO value <fmt:message key="label.answered" bundle="${locale}"/>--%>
<%--                            TODO value <fmt:message key="label.notAnswered" bundle="${locale}"/>--%>
                            <c:out value="${isAnswered ? 'Answered' : 'Not answered'}"/>
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
                                        <input type="submit" value="<fmt:message key="submit.viewAnswer" bundle="${locale}"/>">
                                    </form>
                                </c:if>
                            </c:when>
                            <c:when test="${sessionScope.user.role eq 'ADMINISTRATOR'}">
                                <c:if test="${!isAnswered}">
                                    <form action="controller" method="get">
                                        <input type="hidden" name="command" value="answerPage">
                                        <input type="hidden" name="question_id" value="<c:out value="${question.id}"/>">
                                        <input type="submit" value="<fmt:message key="submit.answer" bundle="${locale}"/>">
                                    </form>
                                </c:if>
                            </c:when>
                        </c:choose>

                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
</u:htmlBase>