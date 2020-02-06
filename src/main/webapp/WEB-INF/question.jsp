<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="Question Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <c:forEach var="question" items="${requestScope.questions}">
                <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
                    <div class="card-body">
                        <c:set value="${question.answerId != 0}" var="isAnswered"/>
                        <h5 class="card-title">
                            <c:out value="${isAnswered ? 'Answered' : 'Not answered'}"/>
                        </h5>
                        <p class="card-text">Question: <c:out value="${question.content}"/></p>
                        <c:choose>
                            <c:when test="${sessionScope.user.role eq 'USER'}">
                                <c:if test="${isAnswered}">
                                    <form action="controller" method="get">
                                        <input name="command" value="answer" type="hidden">
                                        <input name="question_id" value="<c:out value="${question.id}"/>" type="hidden">
                                        <input value="View answer" type="submit">
                                    </form>
                                </c:if>
                            </c:when>
                            <c:when test="${sessionScope.user.role eq 'ADMINISTRATOR'}">
                                <c:if test="${!isAnswered}">
                                    <form action="controller" method="get">
                                        <input name="command" value="answerPage" type="hidden">
                                        <input name="question_id" value="<c:out value="${question.id}"/>" type="hidden">
                                        <input value="Answer" type="submit">
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