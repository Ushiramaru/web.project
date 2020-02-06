<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="Conference Information Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <c:forEach var="section" items="${requestScope.sections}">
                <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${section.topic}"/></h5>
                        <form action="controller" method="get">
                            <input name="command" value="applyPage" type="hidden">
                            <input name="section_id" value="<c:out value="${section.id}"/>" type="hidden">
                            <input value="Apply" type="submit">
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