<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="pageCount" required="true" rtexprvalue="true" type="java.lang.Long" %>
<%@ attribute name="activePage" required="true" rtexprvalue="true" type="java.lang.Long" %>
<%@ attribute name="commandName" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="startPage" required="false" rtexprvalue="true" type="java.lang.Long" %>
<c:set var="startPage" value="${(empty startPage) ? 1 : startPage}"/>
<nav>
    <ul class="pagination justify-content-center mt-4 mb-0">
        <c:if test="${activePage > startPage}">
            <li class="page-item bg-dark">
                <a class="page-link bg-dark" href="controller?command=<c:out value="${commandName}&pageNumber=${activePage - 1}"/>">
                    <span>&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${activePage <= startPage}">
            <li class="page-item disabled bg-dark">
                <a class="page-link bg-dark" href="">
                    <span>&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:forEach varStatus="status" begin="${startPage}" end="${pageCount}">
            <c:if test="${status.index == activePage}">
                <li class="page-item active bg-dark">
                    <a class="page-link bg-dark" href="controller?command=<c:out value="${commandName}&pageNumber=${status.index}"/>">
                        <c:out value="${status.index}"/>
                    </a>
                </li>
            </c:if>
            <c:if test="${status.index != activePage}">
                <li class="page-item bg-dark">
                    <a class="page-link bg-dark" href="controller?command=<c:out value="${commandName}&pageNumber=${status.index}"/>">
                        <c:out value="${status.index}"/>
                    </a>
                </li>
            </c:if>
        </c:forEach>
        <c:if test="${activePage < pageCount}">
            <li class="page-item bg-dark">
                <a class="page-link bg-dark" href="controller?command=<c:out value="${commandName}&pageNumber=${activePage + 1}"/>">
                    <span>&raquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${activePage == pageCount}">
            <li class="page-item disabled bg-dark">
                <a class="page-link bg-dark" href="">
                    <span>&raquo;</span>
                </a>
            </li>
        </c:if>
    </ul>
</nav>