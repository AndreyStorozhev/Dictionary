<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <script src="<spring:url value="/resources/js/jquery-3.3.1.min.js"/>" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#dictionary_char').click(function () {
                $(this).next().slideToggle("fast");
            });
            $('#dictionary_num').click(function () {
                $(this).next().slideToggle("fast");
            });
        });
    </script>
</head>
<body>
<script type="text/javascript" >
    function deleteData(str) {
        $.ajax({
            type:"GET",
            url:"/remove/" + str,
            data:str,
            dataType:"json",
            success:function (data) {
                console.log(data);
            }
        });
    }
</script>
    <div id="dictionary_char">Dictionary Char</div>
    <div class="menu_char" style="display:none;">
        <ul>
            <c:if test="${not empty listKeyChar}">
                <c:forEach items="${listKeyChar}" var="charItem">
                    <li>${charItem.key} -
                        <c:if test="${not empty charItem.values}" >
                            <c:forEach items="${charItem.values}" var="value">
                                ${value.value}
                            </c:forEach>
                        </c:if>
                        <button onclick="deleteData(${charItem.id})">Delete</button>
                    </li>
                </c:forEach>
                <p></p>
                <a href="<c:url value="/add/0"/>">Add dictionary</a>
            </c:if>
        </ul>
    </div>
    <div id="dictionary_num">Dictionary Number</div>
    <div class="menu_num" style="display:none;">
        <ul>
            <c:if test="${not empty listKeyNum}">
                <c:forEach items="${listKeyNum}" var="numItem">
                    <li>${numItem.key} -
                        <c:if test="${not empty numItem.values}">
                            <c:forEach items="${numItem.values}" var="value">
                                ${value.value}
                            </c:forEach>
                        </c:if>
                        <a href="/remove/${numItem.id}">remove</a>
                    </li>
                </c:forEach>
                <p></p>
                <a href="<c:url value="/add/1"/>">Add dictionary</a>
            </c:if>
        </ul>
    </div>
    <form action="${pageContext.request.contextPath}/search" method="get">
        Search: <input class="text" name="name" type="text">
        <input type="submit" value="Search">
    </form>
</body>
</html>
