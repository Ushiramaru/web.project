<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="controller" method="post">
    <input name="command" value="login" type="hidden">
    <label>Логин:
        <input name="login" value="pDestroyer" type="text">
    </label>
    <br>
    <label>Пароль:
        <input name="password" value="qwert" type="password">
    </label>
    <br>
    <input type="submit" value="Войти">
</form>
<c:if test="${sessionScope.containsKey('wrong')}">
    <label><c:out value="${sessionScope.get('wrong')}"/></label>
</c:if>
</body>
</html>