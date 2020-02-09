<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.conferenceInfoAdminPage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <div class="card mt-2 text-success" style="background-color: yellow; color: yellowgreen">
                <div class="card-body">
                    <jsp:useBean id="conference" scope="request" type="com.epam.conference.entity.Conference"/>
                    <h5 class="card-title"><c:out value="${conference.name}"/></h5>
                    <p class="card-text">
                        <fmt:message key="label.startDate" bundle="${locale}"/>:<c:out value="${conference.startDate}"/>
                    </p>
                    <p class="card-text">
                        <fmt:message key="label.endDate" bundle="${locale}"/>:<c:out value="${conference.endDate}"/>
                    </p>
                </div>
            </div>
            <c:forEach var="section" items="${requestScope.sections}">
                <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${section.topic}"/></h5>
                        <form action="controller" method="get">
                            <input type="hidden" name="command" value="sectionEdit">
                            <input type="hidden" name="section_id" value="<c:out value="${section.id}"/>">
                            <label><fmt:message key="label.newTopic" bundle="${locale}"/>:
                                <input type="text" name="section_topic" value="">
                            </label>
                            <input type="submit" value="<fmt:message key="submit.editSection" bundle="${locale}"/>">
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
</u:htmlBase>