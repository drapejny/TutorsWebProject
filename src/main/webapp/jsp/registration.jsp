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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/registration.js"></script>
<html>
<head>
    <title>
        <fmt:message key="registration.title"/>
    </title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<form class="registration-form" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="registration">
    <fmt:message key="registration.first_name"/>
    <input id="firstname" type="text" name="first_name" required pattern="[A-zА-яЁё`'.-]{1,32}" maxlength="32"><br>
    <fmt:message key="registration.last_name" />
    <input id="lastname" type="text" name="last_name" required pattern="[A-zА-яЁё`'.-]{1,32}" maxlength="32"><br>
    <fmt:message key="registration.email"/>
    <input id="email" type="email" name="email" required maxlength="100"><br>
    <fmt:message key="registration.password"/>
    <input id="password" type="password" name="password" required pattern="^\w{6,20}$" maxlength="20"><br>
    <fmt:message key="registration.password_repeat"/>
    <input id="password-repeat" type="password" name="password_repeat" required pattern="^\w{6,20}$" maxlength="20"><br>
    <span class="fail-message">${errorEmailExistsMessage}</span>
    <span class="fail-message">${errorWrongDataMessage}</span>
    <button type="submit" class="simple-btn"><fmt:message key="registration.button.signup"/></button>
</form>
</body>
</html>
