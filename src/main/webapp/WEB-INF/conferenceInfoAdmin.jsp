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
                <fmt:formatDate value="${conference.startDate}" type="both" dateStyle="short" timeStyle="short"/>
                <c:out value="${conference.startDate}"/>
            </p>
            <p class="card-text">
                <fmt:message key="label.endDate" bundle="${locale}"/>:
                <fmt:formatDate value="${conference.endDate}" type="both" dateStyle="short" timeStyle="short"/>
                <c:out value="${conference.endDate}"/>
            </p>
        </div>
    </div>
    <c:forEach var="section" items="${requestScope.sections}">
        <div class="card mt-2 text-success">
            <div class="card-body">
                <h5 class="card-title"><c:out value="${section.topic}"/></h5>
                <form class="form-signin" action="controller" method="post">
                    <input type="hidden" name="command" value="sectionEdit">
                    <input type="hidden" name="section_id" value="<c:out value="${section.id}"/>">
                    <textarea maxlength="50" name="content" type="text" class="form-control"
                        placeholder="<fmt:message key="label.newTopic" bundle="${locale}"/>" required=""></textarea>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        <fmt:message key="submit.editSection" bundle="${locale}"/>
                    </button>
                </form>
            </div>
        </div>
    </c:forEach>
</u:htmlBase>