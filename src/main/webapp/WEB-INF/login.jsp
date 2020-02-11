<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="title.loginPage" bundle="${locale}"/></title>
    <link href="${pageContext.request.contextPath}/style/signin.css" rel="stylesheet">
</head>
<body class="text-center">
<form class="form-signin bg-dark" action="controller" method="post">
    <div class="nav-local">
        <ul class="navbar-locale">
            <li class="nav-item">
                <a class="nav-link" href="controller?command=language&l=ru_RU&back=command=loginPage">
                    RU
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=language&l=en_EN&back=command=loginPage">
                    EN
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="controller?command=language&l=by_BY&back=command=loginPage">
                    BY
                </a>
            </li>
        </ul>
    </div>
    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="label.loginMessage" bundle="${locale}"/></h1>
    <input name="command" value="login" type="hidden">
    <label for="inputLogin" class="sr-only"><fmt:message key="label.login" bundle="${locale}"/></label>
    <input name="login" value="pDestroyer" type="text" id="inputLogin" class="form-control"
           placeholder="<fmt:message key="label.login" bundle="${locale}"/>" required="" autofocus="">
    <label for="inputPassword" class="sr-only"><fmt:message key="label.password" bundle="${locale}"/></label>
    <input name="password" value="qwert" type="password" id="inputPassword" class="form-control"
           placeholder="<fmt:message key="label.password" bundle="${locale}"/>" required="">
    <button class="btn btn-lg btn-primary btn-block" type="submit">
        <fmt:message key="submit.login" bundle="${locale}"/>
    </button>
    <c:if test="${sessionScope.containsKey('wrong')}">
        <p class="mt-5 mb-3 text-muted">
            <c:out value="${sessionScope.get('wrong')}"/>
        </p>
    </c:if>
</form>
</body>
</html>