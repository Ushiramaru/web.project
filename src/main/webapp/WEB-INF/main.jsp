<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<%--    TODO title <fmt:message key="title.mainPage" bundle="${locale}"/>--%>
<u:htmlBase title="Main Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <p>
                <fmt:message key="label.welcome" bundle="${locale}"/>
            </p>
        </div>
    </main>
</u:htmlBase>