<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="Ask Page">
    <main role="main" class="flex-shrink-0">
        <form action="controller" method="post">
            <input name="command" value="ask" type="hidden">
            <label>Вопрос:
                <input name="content" value="" type="text">
            </label>
            <input type="submit" value="Ask">
        </form>
    </main>
</u:htmlBase>