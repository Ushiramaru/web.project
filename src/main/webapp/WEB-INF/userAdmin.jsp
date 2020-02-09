<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<%--    TODO title <fmt:message key="title.usersAdminPage" bundle="${locale}"/>--%>
<u:htmlBase title="Users Administration Page">
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
                            <c:when test="${user.role eq 'ADMINISTRATOR'}"><fmt:message key="label.roleAdmin" bundle="${locale}"/></c:when>
                            <c:when test="${user.role eq 'USER'}"><fmt:message key="label.roleUser" bundle="${locale}"/></c:when>
                        </c:choose>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <c:choose>
                                <c:when test="${user.active}">
                                    <c:set var="command" value="block"/>
<%--                                    TODO value <fmt:message key="label.block" bundle="${locale}"/>--%>
                                    <c:set var="commandName" value="Заблокировать"/>
                                </c:when>
                                <c:when test="${!user.active}">
                                    <c:set var="command" value="unblock"/>
<%--                                    TODO value <fmt:message key="label.unblock" bundle="${locale}"/>--%>
                                    <c:set var="commandName" value="Разблокировать"/>
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