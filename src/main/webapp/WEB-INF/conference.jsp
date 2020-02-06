<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="Conferences Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <c:forEach var="conference" items="${requestScope.conferences}">
                <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
                    <div class="card-body">
                        <img src="<c:out value="image/ + ${conference.imagePath}"/>" class="card-img-top float-right"
                             alt="image" style="width: 100px; height: 100px">
                        <h5 class="card-title"><c:out value="${conference.name}"/></h5>
                        <p class="card-text">Start date: <c:out value="${conference.startDate}"/></p>
                        <p class="card-text">End date: <c:out value="${conference.endDate}"/></p>
                        <form action="controller" method="get">
                            <input name="command" value="conferenceInfo" type="hidden">
                            <input name="conference_id" value="<c:out value="${conference.id}"/>" type="hidden">
                            <input value="View sections" type="submit">
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