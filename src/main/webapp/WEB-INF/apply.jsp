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
    <form action="controller" method="post">
        <input name="command" value="apply" type="hidden">
        <input name="section_id" value="<c:out value="${section.id}"/>" type="hidden">
        <label><fmt:message key="label.topic" bundle="${locale}"/>:
            <input name="topic" value="" type="text">
        </label>
        <input type="submit" value="<fmt:message key="submit.apply" bundle="${locale}"/>">
    </form>
</u:htmlBase>