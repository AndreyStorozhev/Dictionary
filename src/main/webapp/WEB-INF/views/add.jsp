<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Char</title>
</head>
<body>
    <form:form method="post" modelAttribute="addKeyChar">
        KeyDictionary
        <form:input path="key"/>
        Value
        <input name="value">
        <button type="submit">Add</button>
    </form:form>
</body>
</html>
