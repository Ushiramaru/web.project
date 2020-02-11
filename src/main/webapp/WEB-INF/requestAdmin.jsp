<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.requestsAdminPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <div class="table-responsive">
        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th><fmt:message key="label.requestTopic" bundle="${locale}"/></th>
                <th><fmt:message key="label.sectionTopic" bundle="${locale}"/></th>
                <th><fmt:message key="label.conferenceName" bundle="${locale}"/></th>
                <th class="btn-cell"><fmt:message key="label.state" bundle="${locale}"/></th>
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
                            <input type="hidden" name="command" value="requestReject">
                            <button class="btn btn-lg btn-primary btn-block btn-table btn-table-multi <c:if test="${request.state eq 'REJECTED'}"><c:out value="choose"/></c:if>" type="submit">
                                <fmt:message key="submit.reject" bundle="${locale}"/>
                            </button>
                        </form>
                        <form action="controller" method="post">
                            <input type="hidden" name="request_id" value="${request.id}">
                            <input type="hidden" name="command" value="requestAccept">
                            <button class="btn btn-lg btn-primary btn-block btn-table btn-table-multi <c:if test="${request.state eq 'ACCEPTED'}"><c:out value="choose"/></c:if>" type="submit">
                                <fmt:message key="submit.accept" bundle="${locale}"/>
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</u:htmlBase>