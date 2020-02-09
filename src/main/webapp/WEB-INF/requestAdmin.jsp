<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="locale" var="locale"/>
<%--    TODO title <fmt:message key="title.requestsAdminPage" bundle="${locale}"/>--%>
<u:htmlBase title="Requests Administration Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <table>
                <thead>
                <tr>
                    <th><fmt:message key="label.requestTopic" bundle="${locale}"/></th>
                    <th><fmt:message key="label.sectionTopic" bundle="${locale}"/></th>
                    <th><fmt:message key="label.conferenceName" bundle="${locale}"/></th>
                    <th><fmt:message key="label.state" bundle="${locale}"/></th>
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
                                        <input type="submit" value="<fmt:message key="submit.reject" bundle="${locale}"/>">
                                    </c:when>
                                    <c:when test="${request.state eq 'REJECTED'}">
                                        <input type="hidden" name="command" value="requestAccept">
                                        <input type="submit" value="<fmt:message key="submit.accept" bundle="${locale}"/>">
                                    </c:when>
                                    <c:when test="${request.state eq 'NOT_REVIEWED'}">
                                        <label>
                                            <select name="command" required>
                                                <option value="requestAccept"><fmt:message key="submit.accept" bundle="${locale}"/></option>
                                                <option value="requestReject" selected><fmt:message key="submit.reject" bundle="${locale}"/></option>
                                            </select>
                                        </label>
                                        <input type="submit" value="<fmt:message key="submit.setValue" bundle="${locale}"/>">
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