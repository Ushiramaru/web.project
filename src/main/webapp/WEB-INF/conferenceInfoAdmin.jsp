<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.conferenceInfoAdminPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <div class="card mt-2 text-success">
        <div class="card-body">
            <jsp:useBean id="conference" scope="request" type="com.epam.conference.entity.Conference"/>
            <h5 class="card-title"><c:out value="${conference.name}"/></h5>
            <p class="card-text">
                <fmt:message key="label.startDate" bundle="${locale}"/>:
                <c:out value="${conference.startDate}"/>
            </p>
            <p class="card-text">
                <fmt:message key="label.endDate" bundle="${locale}"/>:
                <c:out value="${conference.endDate}"/>
            </p>
        </div>
    </div>
    <c:forEach var="section" items="${requestScope.sections}">
        <div class="card mt-2 text-success">
            <div class="card-body">
                <h5 class="card-title"><c:out value="${section.topic}"/></h5>
                <form class="form-signin" action="controller" method="post">
<%--                    <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="label.newTopic" bundle="${locale}"/></h1>--%>
                    <input type="hidden" name="command" value="sectionEdit">
                    <input type="hidden" name="section_id" value="<c:out value="${section.id}"/>">
                    <textarea type="text" name="section_topic" placeholder="<fmt:message key="label.newTopic" bundle="${locale}"/>"
                              class="form-control" maxlength="50" required></textarea>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        <fmt:message key="submit.editSection" bundle="${locale}"/>
                    </button>
                </form>
            </div>
        </div>
    </c:forEach>
</u:htmlBase>