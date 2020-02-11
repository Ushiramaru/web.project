<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.requestsPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <c:forEach var="request" items="${requestScope.requests}">
        <div class="card mt-2 text-success">
            <div class="card-body">
                <h5 class="card-title"><c:out value="${request.requestTopic}"/></h5>
                <p class="card-text">
                    <fmt:message key="label.sectionTopic" bundle="${locale}"/>:<c:out value="${request.sectionTopic}"/>
                </p>
                <p class="card-text"><fmt:message key="label.conferenceName" bundle="${locale}"/>:
                    <a href="controller?command=conferenceInfo&conference_id=<c:out value="${request.conferenceId}"/>">
                        <c:out value="${request.conferenceName}"/>
                    </a>
                </p>
                <p>
                    <c:choose>
                        <c:when test="${request.state eq 'ACCEPTED'}">
                            <fmt:message key="label.accepted" bundle="${locale}"/>
                        </c:when>
                        <c:when test="${request.state eq 'REJECTED'}">
                            <fmt:message key="label.rejected" bundle="${locale}"/>
                        </c:when>
                        <c:when test="${request.state eq 'NOT_REVIEWED'}">
                            <fmt:message key="label.notReviewed" bundle="${locale}"/>
                        </c:when>
                    </c:choose>
                </p>
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="cancelRequest">
                    <input type="hidden" name="request_id" value="<c:out value="${request.id}"/>">
                    <button class="btn btn-lg btn-primary btn-block btn-custom" type="submit">
                        <fmt:message key="submit.cancel" bundle="${locale}"/>
                    </button>
                </form>
            </div>
        </div>
    </c:forEach>
</u:htmlBase>