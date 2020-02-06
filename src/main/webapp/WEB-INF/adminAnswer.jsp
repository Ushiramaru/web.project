<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="AnswerAdmin Page">
    <main role="main" class="flex-shrink-0">
        <jsp:useBean id="question" scope="request" type="com.epam.conference.dto.QuestionDto"/>
        <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
            <div class="card-body">
                <h5 class="card-title">Вопрос: <c:out value="${question.questionContent}"/></h5>
                <p class="card-text">Вопрос задал: <c:out value="${question.userName}"/></p>
                <p class="card-text">Роль пользователя:
                    <c:choose>
                    <c:when test="${question.userRole eq 'ADMINISTRATOR'}">ADMINISTRATOR</c:when>
                    <c:when test="${question.userRole eq 'USER'}">USER</c:when>
                    </c:choose>
                <form action="controller" method="post">
                    <input name="command" value="answerAdmin" type="hidden">
                    <input name="question_id" value="<c:out value="${question.id}"/>" type="hidden">
                    <label>Ответ:
                        <input name="content" value="" type="text">
                    </label>
                    <input type="submit" value="Answer">
                </form>
            </div>
        </div>
    </main>
</u:htmlBase>