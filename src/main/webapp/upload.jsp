<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>JSP-Servlet Upload file</title>
</head>
<body>
<h2>Demo JSP-Servlet Upload File</h2>
<form method="post" action="/upload" enctype="multipart/form-data">
    Select file to upload: <input type="file"name="file" size="60" accept="image/*" /><br /><br />
    <input type="submit" value="Upload" />
</form>
<h1>
<%--    <img src="${pageContext.request.contextPath}/images/image.png">--%>
    <c:if test="${requestScope['message'] != null }">
        <c:out value="${message}"></c:out>
    </c:if>
</h1>

<div>
    <c:if test="${avatar != null}">
        <img src="/images/${avatar}" alt="">
    </c:if>

</div>
</body>
</html>
