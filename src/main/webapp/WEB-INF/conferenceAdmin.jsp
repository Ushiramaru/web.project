<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="Conferences Administration Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Info</th>
                    <th>Relevant</th>
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
                                <input type="submit" value="Edit">
                            </form>
                        </td>
                        <td>
                        <td>
                            <form action="controller" method="post">
                                <c:choose>
                                    <c:when test="${conference.relevant}">
                                        <c:set var="command" value="conferenceBlock"/>
                                        <c:set var="commandName" value="Скрыть"/>
                                    </c:when>
                                    <c:when test="${!conference.relevant}">
                                        <c:set var="command" value="conferenceUnblock"/>
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