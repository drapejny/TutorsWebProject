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
<c:import url="/jsp/fragment/header.jsp"/>
<ctg:user-photo photo="${sessionScope.user.photo}" height="200" width="200"/>
<form action="${pageContext.request.contextPath}/uploadServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="upload_tutor_photo">
    <input type="file" name="file" multiple accept="image/*,image/jpeg"><br>
    <input type="submit" value="Изменить">
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="edit_tutor_profile">
    <c:forEach var="element" items="${applicationScope.subjects}" varStatus="status">
        <input type="checkbox" name="subject" value="${element.subjectId}"
               <c:set var="contains" value="false"/>
                <c:forEach var="item" items="${sessionScope.subjects}">
                    <c:if test="${item.subjectId eq element.subjectId}">
                        <c:set var="contains" value="true"/>
                    </c:if>
                </c:forEach>
                <c:if test="${contains eq true}">checked</c:if>
        >${element.subjectName}<br>
    </c:forEach>
    <fmt:message key="profile.first_name"/><input type="text" name="first_name" value="${sessionScope.user.firstName}" required pattern="[A-zА-яЁё`'.-]{1,32}" maxlength="32"><br>
    <fmt:message key="profile.last_name"/><input type="text" name="last_name" value="${sessionScope.user.lastName}" required pattern="[A-zА-яЁё`'.-]{1,32}" maxlength="32"><br>
    <fmt:message key="profile.phone"/><input type="text" name="phone" value="${sessionScope.user.phone}" required pattern="^\+375[0-9]{9}$" maxlength="13"><br>
    <fmt:message key="profile.info"/><textarea name="info" required maxlength="500">${sessionScope.user.info}</textarea><br>
    <fmt:message key="profile.price"/><input type="number" name="price" value="${sessionScope.user.pricePerHour}" required min="1" max="999"><br>
    <fmt:message key="profile.isActive"/><input type="checkbox" name="is_active" value="${user.isActive}" <c:if test="${user.isActive eq true}">checked</c:if>><br>
    <input type="submit" value="<fmt:message key="edit.profile.button"/>">
    <input type="reset" value="<fmt:message key="edit.reset"/>"><hr>
    ${errorWrongDataMessage}
    ${successEditMessage}
</form>
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
