<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <c:if test="${not empty listKey}">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Key</th>
                    <th>Value</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listKey}" var="key">
                    <tr>
                        <td><a href="/person/${key.id}">${key.id}</a></td>
                        <td>${key.key}</td>
                        <td>
                            <c:forEach items="${key.values}" var="value">
                                ${value.value}
                            </c:forEach>
                        </td>
                        <td><a href="/update/${key.id}">Update</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
