<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<html>
<head>
    <title><fmt:message key="title.loginPage" bundle="${locale}"/></title>
</head>
<body>
<form action="controller" method="post">
    <input name="command" value="login" type="hidden">
    <label><fmt:message key="label.login" bundle="${locale}"/>:
        <input name="login" value="pDestroyer" type="text">
    </label>
    <br>
    <label><fmt:message key="label.password" bundle="${locale}"/>:
        <input name="password" value="qwert" type="password">
    </label>
    <br>
    <input type="submit" value="<fmt:message key="submit.login" bundle="${locale}"/>">
</form>
<c:if test="${sessionScope.containsKey('wrong')}">
    <label><c:out value="${sessionScope.get('wrong')}"/></label>
</c:if>
</body>
</html>