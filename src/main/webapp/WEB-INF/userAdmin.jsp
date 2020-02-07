<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="Users Page">
    <main role="main" class="flex-shrink-0">
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Role</th>
                <th>isActive</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.users}">
                <tr>
                    <td><c:out value="${user.name}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${user.role eq 'ADMINISTRATOR'}">ADMINISTRATOR</c:when>
                            <c:when test="${user.role eq 'USER'}">USER</c:when>
                        </c:choose>
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <c:choose>
                                <c:when test="${user.active}">
                                    <c:set var="command" value="block"/>
                                    <c:set var="commandName" value="Заблокировать"/>
                                </c:when>
                                <c:when test="${!user.active}">
                                    <c:set var="command" value="unblock"/>
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