<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.conferencesPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <c:forEach var="conference" items="${requestScope.conferences}">
        <div class="card mt-2 text-success">
            <div class="card-body">
                <h5 class="card-title"><c:out value="${conference.name}"/></h5>
                <p class="card-text"><fmt:message key="label.startDate" bundle="${locale}"/>:
                    <c:out value="${conference.startDate}"/>
                </p>
                <p class="card-text"><fmt:message key="label.endDate" bundle="${locale}"/>:
                    <c:out value="${conference.endDate}"/>
                </p>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="conferenceInfo">
                    <input type="hidden" name="conference_id" value="<c:out value="${conference.id}"/>">
                    <button class="btn btn-lg btn-primary btn-block btn-custom" type="submit">
                        <fmt:message key="submit.viewSections" bundle="${locale}"/>
                    </button>
                </form>
            </div>
        </div>
    </c:forEach>
</u:htmlBase>