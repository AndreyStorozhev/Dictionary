<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>search</title>
</head>
<body>
    <c:if test="${not empty key.key_char}">
        <p>${key.key_char} -
            <c:forEach items="${key.values}" var="itemChar">
                ${itemChar.value}
            </c:forEach>
        </p>
    </c:if>
    <c:if test="${not empty key.key_char}">
        <p>${key.key_num} -
            <c:forEach items="${key.values}" var="itemNum">
                ${itemNum.value}
            </c:forEach>
        </p>
    </c:if>
</body>
</html>
