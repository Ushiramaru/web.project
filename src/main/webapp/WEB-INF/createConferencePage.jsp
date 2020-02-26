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
        <label for="inputConferenceName" class="sr-only"><fmt:message key="label.conferenceName" bundle="${locale}"/></label>
        <input type="text" name="conference_name" id="inputConferenceName" class="form-control"
               placeholder="<fmt:message key="label.conferenceName" bundle="${locale}"/>" autofocus maxlength="50" required/>
        <label for="inputStartDate"><fmt:message key="label.startDate" bundle="${locale}"/></label>
        <input type="datetime-local" name="start_date" id="inputStartDate" class="form-control" required/>
        <label for="inputEndDate"><fmt:message key="label.endDate" bundle="${locale}"/></label>
        <input type="datetime-local" name="end_date" id="inputEndDate" class="form-control" required/>
        <button class="btn btn-lg btn-primary btn-block" id="add-section-button" type="button">
            <fmt:message key="button.addSection" bundle="${locale}"/></button>
        <div id="sections-div">
            <label for="section-topic-1" id="new-section-label"><fmt:message key="label.section" bundle="${locale}"/> 1</label>
            <textarea type="text" name="section-topic[]" placeholder="1" id="section-topic-1"
                      class="form-control" maxlength="50" required></textarea>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
            <fmt:message key="submit.create" bundle="${locale}"/>
        </button>
    </form>
</u:htmlBase>