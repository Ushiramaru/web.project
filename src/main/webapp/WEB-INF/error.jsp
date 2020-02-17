<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" isErrorPage="true" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.errorPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <c:choose>
        <c:when test="${pageContext.errorData.statusCode == 400}">
            <h1 class="h-center"><fmt:message key="error.badRequest" bundle="${locale}"/></h1>
        </c:when>
        <c:when test="${pageContext.errorData.statusCode == 403}">
            <h1 class="h-center"><fmt:message key="error.forbidden" bundle="${locale}"/></h1>
        </c:when>
        <c:when test="${pageContext.errorData.statusCode == 404}">
            <h1 class="h-center"><fmt:message key="error.notFound" bundle="${locale}"/></h1>
        </c:when>
        <c:when test="${pageContext.errorData.statusCode == 500}">
            <h1 class="h-center"><fmt:message key="error.internalServer" bundle="${locale}"/></h1>
        </c:when>
        <c:otherwise>
            <h1 class="h-center"><fmt:message key="error.message" bundle="${locale}"/></h1>
        </c:otherwise>
    </c:choose>
</u:htmlBase>