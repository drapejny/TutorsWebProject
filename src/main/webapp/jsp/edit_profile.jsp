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
    <input type="hidden" name="command" value="upload_user_photo">
    <input type="file" name="file" multiple accept="image/*,image/jpeg"><br>
    <input type="submit" value="<fmt:message key="edit.photo.change"/>">
</form>
<span class="success-message">${successEditMessage}</span>
<span class="fail-message">${errorWrongDataMessage}</span>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="edit_profile">
    <fmt:message key="profile.first_name"/><input type="text" name="first_name" value="${sessionScope.user.firstName}" required pattern="[A-zА-яЁё`'.-]{1,32}" maxlength="32"><br>
    <fmt:message key="profile.last_name"/><input type="text" name="last_name" value="${sessionScope.user.lastName}" required pattern="[A-zА-яЁё`'.-]{1,32}" maxlength="32"><br>
    <button type="submit" class="simple-btn"><fmt:message key="edit.profile.button"/></button>
    <button type="reset" class="simple-btn"><fmt:message key="edit.reset"/></button>
</form>
<span class="success-message">${successEditPassword}</span>
<span class="fail-message">${errorWrongPasswordMessage}</span>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="edit_password">
    <fmt:message key="profile.password"/><input name="password" type="password" required pattern="^\w{6,20}$" maxlength="20"><br>
    <fmt:message key="profile.new_password"/><input name="new_password" type="password" required pattern="^\w{6,20}$" maxlength="20"><br>
    <button type="submit" class="simple-btn"><fmt:message key="edit.profile.button"/></button>
</form>
</body>
</html>
