<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.usersAdminPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <main role="main" class="flex-shrink-0">
        <table>
            <thead>
            <tr>
                <th><fmt:message key="label.name" bundle="${locale}"/></th>
                <th><fmt:message key="label.role" bundle="${locale}"/></th>
                <th><fmt:message key="label.isActive" bundle="${locale}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <td><c:out value="${user.name}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${user.role eq 'ADMINISTRATOR'}">
                                <fmt:message key="label.roleAdmin" bundle="${locale}"/>
                            </c:when>
                            <c:when test="${user.role eq 'USER'}">
                                <fmt:message key="label.roleUser" bundle="${locale}"/>
                            </c:when>
                        </c:choose>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <c:choose>
                                <c:when test="${user.active}">
                                    <c:set var="command" value="block"/>
                                    <fmt:message key="label.block" bundle="${locale}" var="commandName"/>
                                </c:when>
                                <c:when test="${!user.active}">
                                    <c:set var="command" value="unblock"/>
                                    <fmt:message key="label.unblock" bundle="${locale}" var="commandName"/>
                                </c:when>
                            </c:choose>
                            <input type="hidden" name="command" value="${command}">
                            <input type="hidden" name="user_id" value="${user.id}">
                            <input type="submit" value="${commandName}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </main>
</u:htmlBase>