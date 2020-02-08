<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="Requests Administration Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <table>
                <thead>
                <tr>
                    <th>Request Topic</th>
                    <th>Section Topic</th>
                    <th>Conference Name</th>
                    <th>State</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="request" items="${requestScope.requests}">
                    <tr>
                        <td><c:out value="${request.requestTopic}"/></td>
                        <td><c:out value="${request.sectionTopic}"/></td>
                        <td><c:out value="${request.conferenceName}"/></td>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="request_id" value="${request.id}">
                                <c:choose>
                                    <c:when test="${request.state eq 'ACCEPTED'}">
                                        <input type="hidden" name="command" value="requestReject">
                                        <input type="submit" value="Отказать">
                                    </c:when>
                                    <c:when test="${request.state eq 'REJECTED'}">
                                        <input type="hidden" name="command" value="requestAccept">
                                        <input type="submit" value="Одобрить">
                                    </c:when>
                                    <c:when test="${request.state eq 'NOT_REVIEWED'}">
                                        <label>
                                            <select name="command" required>
                                                <option value="requestAccept">Одобрить</option>
                                                <option value="requestReject" selected>Отказать</option>
                                            </select>
                                        </label>
                                        <input type="submit" value="Установить значение">
                                    </c:when>
                                </c:choose>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</u:htmlBase>