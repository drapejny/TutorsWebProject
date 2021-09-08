<%--
  Created by IntelliJ IDEA.
  User: Slizh Anton
  Date: 08.09.2021
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title>
        <fmt:message key="registration.title"/>
    </title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="registration">
    <fmt:message key="registration.firstname"/><input type="text" name="first_name"><br>
    <fmt:message key="registration.lastname"/><input type="text" name="last_name"><br>
    <fmt:message key="registration.email"/><input type="text" name="email"><br>
    <fmt:message key="registration.password"/><input type="text" name="password"><br>
</form>
</body>
</html>
