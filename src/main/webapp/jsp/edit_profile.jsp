<%--
  Created by IntelliJ IDEA.
  User: Slizh Anton
  Date: 16.09.2021
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="edit.profile.title"/></title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
dsf
<img src="data:image/jpg;base64,${sessionScope.user.photo}" width="200" height="200"><br>
<form action="${pageContext.request.contextPath}/uploadServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="photo" multiple accept="image/*,image/jpeg"><br>
    <input type="submit" value="Изменить">
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="edit_profile">
    <fmt:message key="profile.first_name"/><input type="text" value="${sessionScope.user.firstName}"><br>
    <fmt:message key="profile.last_name"/><input type="text" value="${sessionScope.user.lastName}"><br>
    <fmt:message key="profile.city"/><input type="text" value="${sessionScope.user.city}"><br>
    <input type="submit" value="<fmt:message key="edit.profile.button"/>">

</form>



</body>
</html>
