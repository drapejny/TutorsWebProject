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
<%@ taglib uri="customtags" prefix="ctg"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="edit.profile.title"/></title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<ctg:user-photo photo="${sessionScope.user.photo}" height="200" width="200"/>
<form action="${pageContext.request.contextPath}/uploadServlet" method="post" enctype="multipart/form-data">
    <input type="file" name="photo" multiple accept="image/*,image/jpeg"><br>
    <input type="submit" value="Изменить">
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="edit_profile">
    <fmt:message key="profile.first_name"/><input type="text" name="first_name" value="${sessionScope.user.firstName}"><br>
    <fmt:message key="profile.last_name"/><input type="text" name="last_name" value="${sessionScope.user.lastName}"><br>
    <input type="submit" value="<fmt:message key="edit.profile.button"/>"><hr>
</form>
${errorWrongDataMessage}
${successEditMessage}
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="edit_password">
    <fmt:message key="profile.password"/><input name="password" type="text"><br>
    <fmt:message key="profile.new_password"/><input name="new_password" type="text"><br>
    <input type="submit" value="<fmt:message key="edit.profile.button"/>">
</form>
${successEditPassword}
${errorWrongPasswordMessage}
</body>
</html>
