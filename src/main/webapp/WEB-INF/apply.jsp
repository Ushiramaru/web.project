<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>

<u:htmlBase title="Apply Page">
    <jsp:useBean id="section" scope="request" type="com.epam.conference.entity.Section"/>
    <label><c:out value="${section.topic}"/></label>
    <form action="controller" method="post">
        <input name="command" value="apply" type="hidden">
        <input name="section_id" value="<c:out value="${section.id}"/>" type="hidden">
        <label>Тема доклада:
            <input name="topic" value="" type="text">
        </label>
        <input type="submit" value="Apply">
    </form>
</u:htmlBase>