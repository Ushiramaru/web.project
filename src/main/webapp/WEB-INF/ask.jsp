<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.askPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <main role="main" class="flex-shrink-0">
        <form action="controller" method="post">
            <input name="command" value="ask" type="hidden">
            <label><fmt:message key="label.question" bundle="${locale}"/>:
                <input name="content" value="" type="text">
            </label>
            <input type="submit" value="<fmt:message key="submit.ask" bundle="${locale}"/>">
        </form>
    </main>
</u:htmlBase>