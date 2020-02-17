<%@tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>
<!doctype html>
<html lang="en" class="h-100">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>

    <!-- Css start-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/root.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/navbar.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/container.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/card.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/form.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/table.css">
    <!-- Css end-->

</head>
<body class="d-flex flex-column h-100">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/collapse.js"></script>
<script src="${pageContext.request.contextPath}/js/collapseLocal.js"></script>
<header>
    <u:navbar/>
</header>
<main role="main" class="flex-shrink-0">
    <div class="container p-4">
        <jsp:doBody/>
    </div>
</main>
<footer class="footer mt-auto py-3 bg-dark text-light">
    <div class="container">
        <span>Conference</span>
    </div>
</footer>
</body>
</html>