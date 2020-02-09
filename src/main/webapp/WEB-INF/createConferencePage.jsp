<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.createConferencePage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <main role="main" class="flex-shrink-0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/addNewSectionForm.js"></script>
        <div class="container p-4">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="createConference">
                <label><fmt:message key="label.conferenceName" bundle="${locale}"/>:
                    <input type="text" name="conference_name" value=""/>
                </label>
                <label><fmt:message key="label.startDate" bundle="${locale}"/>:
                    <input type="datetime-local" name="start_date" value=""/>
                </label>
                <label><fmt:message key="label.endDate" bundle="${locale}"/>:
                    <input type="datetime-local" name="end_date" value=""/>
                </label>
                <div id="sections-div">
<%--                    TODO fmt in js--%>
                    <label>Section 1:
                        <input type="text" name="section-topic[]"/>
                    </label>
                </div>
                <input type="submit" value="<fmt:message key="submit.create" bundle="${locale}"/>">
            </form>
            <button id="add-section-button"><fmt:message key="button.addSection" bundle="${locale}"/></button>
        </div>
    </main>
</u:htmlBase>