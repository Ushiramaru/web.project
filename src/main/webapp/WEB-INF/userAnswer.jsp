<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.answerPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <div class="card mt-2 text-success">
        <div class="card-body">
            <h5 class="card-title">
                <fmt:message key="label.answerFrom" bundle="${locale}"/>:<c:out value="${requestScope.answer.administratorName}"/>
            </h5>
            <p class="card-text">
                <fmt:message key="label.answer" bundle="${locale}"/>:<c:out value="${requestScope.answer.answerContent}"/>
            </p>
        </div>
    </div>
</u:htmlBase>