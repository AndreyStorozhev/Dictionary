<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<p><spring:message code="dictionary.add"/> </p>
<form:form method="post" modelAttribute="updateKey">
    <form:label path="key" cssClass="label">
        Key
    </form:label>
    <form:input path="key" cssClass="text-input"/>
    <div>
        <form:errors path="key" cssClass="label error"/>
    </div>
    <p></p>
    Value
    <input name="value">
    <button type="submit">Add</button>
</form:form>
</body>
</html>
