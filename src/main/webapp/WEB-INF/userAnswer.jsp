<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<%--    TODO title <fmt:message key="title.answerPage" bundle="${locale}"/>--%>
<u:htmlBase title="Answer Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
                <div class="card-body">
                    <h5 class="card-title">
                        <fmt:message key="label.answerFrom" bundle="${locale}"/>:<c:out value="${requestScope.answer.administratorName}"/>
                    </h5>
                    <p class="card-text">
                        <fmt:message key="label.answer" bundle="${locale}"/>:<c:out value="${requestScope.answer.answerContent}"/>
                    </p>
                </div>
            </div>
        </div>
    </main>
</u:htmlBase>