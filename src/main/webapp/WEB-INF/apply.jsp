<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.applyPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <jsp:useBean id="section" scope="request" type="com.epam.conference.entity.Section"/>
    <label><c:out value="${section.topic}"/></label>
    <form class="form-signin" action="controller" method="post">
        <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="label.question" bundle="${locale}"/></h1>
        <input type="hidden" name="command" value="apply">
        <input type="hidden" name="section_id" value="<c:out value="${section.id}"/>">
        <label for="inputTopic" class="sr-only"><fmt:message key="label.topic" bundle="${locale}"/></label>
        <textarea type="text" name="topic" id="inputTopic" class="form-control" autofocus maxlength="50" required></textarea>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <fmt:message key="submit.apply" bundle="${locale}"/>
        </button>
    </form>
</u:htmlBase>