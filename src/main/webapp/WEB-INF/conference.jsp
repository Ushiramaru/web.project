<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<%--    TODO title <fmt:message key="title.conferencesPage" bundle="${locale}"/>--%>
<u:htmlBase title="Conferences Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <c:forEach var="conference" items="${requestScope.conferences}">
                <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
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
                            <input type="submit" value="<fmt:message key="submit.viewSections" bundle="${locale}"/>">
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
</u:htmlBase>