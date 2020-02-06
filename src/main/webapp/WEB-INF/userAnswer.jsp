<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="Answer Page">
    <main role="main" class="flex-shrink-0">
        <div class="container p-4">
            <div class="card mt-2 text-success" style="background-color: darkred; color: yellowgreen">
                <div class="card-body">
                    <h5 class="card-title">Ответ от: <c:out value="${requestScope.answer.administratorName}"/></h5>
                    <p class="card-text">Ответ: <c:out value="${requestScope.answer.answerContent}"/></p>
                </div>
            </div>
        </div>
    </main>
</u:htmlBase>