<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message key="title.createConferencePage" bundle="${locale}" var="title"/>
<u:htmlBase title="${title}">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/addNewSectionForm.js"></script>
    <form class="form-signin" action="controller" method="post">
        <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="title.createConferencePage" bundle="${locale}"/></h1>
        <input type="hidden" name="command" value="createConference">
        <label class="sr-only"><fmt:message key="label.conferenceName" bundle="${locale}"/></label>
        <input id="inputConferenceName" type="text" name="conference_name" class="form-control"
               placeholder="<fmt:message key="label.question" bundle="${locale}"/>" required="" autofocus=""/>
        <label for="inputStartDate"><fmt:message key="label.startDate" bundle="${locale}"/></label>
        <input id="inputStartDate" type="datetime-local" name="start_date" class="form-control"/>
        <label for="inputEndDate"><fmt:message key="label.endDate" bundle="${locale}"/></label>
        <input id="inputEndDate" type="datetime-local" name="end_date" class="form-control"/>
        <button class="btn btn-lg btn-primary btn-block" id="add-section-button" type="button">
            <fmt:message key="button.addSection" bundle="${locale}"/></button>
        <div id="sections-div">
            <textarea maxlength="50" type="text" name="section-topic[]" class="form-control" placeholder="1" required></textarea>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <fmt:message key="submit.create" bundle="${locale}"/>
        </button>
    </form>
</u:htmlBase>