<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="Conference Information Administration Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <div class="card mt-2 text-success" style="background-color: yellow; color: yellowgreen">
                <div class="card-body">
                    <jsp:useBean id="conference" scope="request" type="com.epam.conference.entity.Conference"/>
                    <h5 class="card-title"><c:out value="${conference.name}"/></h5>
                    <p class="card-text">Start date: <c:out value="${conference.startDate}"/></p>
                    <p class="card-text">End date: <c:out value="${conference.endDate}"/></p>
                </div>
            </div>
            <c:forEach var="section" items="${requestScope.sections}">
                <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${section.topic}"/></h5>
                        <form action="controller" method="get">
                            <input name="command" value="sectionEdit" type="hidden">
                            <input name="section_id" value="<c:out value="${section.id}"/>" type="hidden">
                            <label>New Topic:
                                <input name="section_topic" value="" type="text">
                            </label>
                            <input value="Edit Section" type="submit">
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
</u:htmlBase>