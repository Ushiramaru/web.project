<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<%--    TODO title <fmt:message key="title.conferencesAdminPage" bundle="${locale}"/>--%>
<u:htmlBase title="Conferences Administration Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <table>
                <thead>
                <tr>
                    <th><fmt:message key="label.name" bundle="${locale}"/>Name</th>
                    <th><fmt:message key="label.startDate" bundle="${locale}"/>Start Date</th>
                    <th><fmt:message key="label.endDate" bundle="${locale}"/>End Date</th>
                    <th><fmt:message key="label.info" bundle="${locale}"/>Info</th>
                    <th><fmt:message key="label.relevant" bundle="${locale}"/>Relevant</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="conference" items="${requestScope.conferences}">
                    <tr>
                        <td><c:out value="${conference.name}"/></td>
                        <td><c:out value="${conference.startDate}"/></td>
                        <td><c:out value="${conference.endDate}"/></td>
                        <td>
                            <form action="controller" method="get">
                                <input type="hidden" name="command" value="conferenceInfoAdmin">
                                <input type="hidden" name="conference_id" value="${conference.id}">
                                <input type="submit" value="<fmt:message key="label.edit" bundle="${locale}"/>">
                            </form>
                        </td>
                        <td>
                            <form action="controller" method="post">
                                <c:choose>
                                    <c:when test="${conference.relevant}">
                                        <c:set var="command" value="conferenceBlock"/>
<%--                                       TODO value <fmt:message key="label.hidden" bundle="${locale}"/>--%>
                                        <c:set var="commandName" value="Скрыть"/>
                                    </c:when>
                                    <c:when test="${!conference.relevant}">
                                        <c:set var="command" value="conferenceUnblock"/>
<%--                                       TODO value <fmt:message key="label.unhidden" bundle="${locale}"/>--%>
                                        <c:set var="commandName" value="Раскрыть"/>
                                    </c:when>
                                </c:choose>
                                <input type="hidden" name="command" value="${command}">
                                <input type="hidden" name="conference_id" value="${conference.id}">
                                <input type="submit" value="${commandName}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</u:htmlBase>