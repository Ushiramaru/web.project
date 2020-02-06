<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="Requests Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <c:forEach var="request" items="${requestScope.requests}">
                <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${request.requestTopic}"/></h5>
                        <p class="card-text">Section topic: <c:out value="${request.sectionTopic}"/></p>
                        <p class="card-text">Conference name:
                            <a href="controller?command=conferenceInfo&conference_id=<c:out value="${request.conferenceId}"/>">
                                <c:out value="${request.conferenceName}"/>
                            </a>
                        </p>
                        <p>
                            <c:choose>
                                <c:when test="${request.state eq 'ACCEPTED'}">ACCEPTED</c:when>
                                <c:when test="${request.state eq 'REJECTED'}">REJECTED</c:when>
                                <c:when test="${request.state eq 'NOT_REVIEWED'}">NOT REVIEWED</c:when>
                            </c:choose>
                        </p>
                        <form action="controller" method="post">
                            <input name="command" value="cancelRequest" type="hidden">
                            <input name="request_id" value="<c:out value="${request.id}"/>" type="hidden">
                            <input value="Cancel" type="submit">
                        </form>
                    </div>
                </div>
            </c:forEach>
            <nav>
                <ul class="pagination justify-content-center mt-4 mb-0">
                    <li class="page-item disabled  bg-dark">
                        <a class="page-link bg-dark" href="#">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="page-item active"><a class="page-link bg-dark" href="#">1</a></li>
                    <li class="page-item"><a class="page-link bg-dark" href="#">2</a></li>
                    <li class="page-item"><a class="page-link bg-dark" href="#">3</a></li>
                    <li class="page-item">
                        <a class="page-link bg-dark" href="#">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </main>
</u:htmlBase>