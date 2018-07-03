<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>search</title>
</head>
<body>
    <c:if test="${not empty key}">
        <p>${key.key} -
            <c:forEach items="${key.values}" var="item">
                ${item.value}
                <a href="<c:url value="/search/removeValue/${item.id}"/>">del</a>
            </c:forEach>
        </p>
    </c:if>
</body>
</html>
