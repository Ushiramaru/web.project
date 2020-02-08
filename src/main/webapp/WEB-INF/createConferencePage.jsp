<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<u:htmlBase title="Create Conference Page">
    <main role="main" class="flex-shrink-0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script>
            <%@ include file="./js/addNewSectionForm.js" %>
        </script>
        <div class="container p-4">
            <form action="controller" method="post">
                <input type="hidden" name="command" value="createConference">
                <label>Conference Name:
                    <input type="text" name="conference_name" value=""/>
                </label>
                <label>Start Date:
                    <input type="datetime-local" name="start_date" value=""/>
                </label>
                <label>End Date:
                    <input type="datetime-local" name="end_date" value=""/>
                </label>
                <div id="sections-div">
                    <label>Section 1:
                        <input type="text" name="section-topic[]"/>
                    </label>
                </div>
                <input type="submit" value="Создать">
            </form>
            <button id="add-section-button">Добавить Секцию</button>
        </div>
    </main>
</u:htmlBase>