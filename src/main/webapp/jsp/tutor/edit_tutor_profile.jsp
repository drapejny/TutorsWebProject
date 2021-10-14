<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="edit.profile.title"/></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/edit_tutor_form_validation.js"></script>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<ctg:user-photo photo="${sessionScope.user.photo}" height="200" width="200"/>
<form action="${pageContext.request.contextPath}/uploadServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="upload_tutor_photo">
    <input type="file" name="file" multiple accept="image/*,image/jpeg"><br>
    <input type="submit" value="<fmt:message key="edit.photo.change"/>">
</form>
<span class="fail-message">${errorWrongDataMessage}</span>
<span class="success-message">${successEditMessage}</span>
<form id="edit_tutor_form" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="edit_tutor_profile">
    <div class="form_input_container subjects">
        <span class="validation-message"><fmt:message key="validation.subjects"/></span>
        <c:forEach var="element" items="${applicationScope.subjects}" varStatus="status">
            <input class="subject_checkbox" type="checkbox" name="subject" value="${element.subjectId}"
                <c:set var="contains" value="false"/>
            <c:forEach var="item" items="${sessionScope.subjects}">
            <c:if test="${item.subjectId eq element.subjectId}">
                <c:set var="contains" value="true"/>
            </c:if>
            </c:forEach>
                   <c:if test="${contains eq true}">checked</c:if>
            >${element.subjectName}<br>
        </c:forEach>
    </div>
    <div class="form_input_container firstname">
        <fmt:message key="profile.first_name"/>
        <input class="form_input" type="text" name="first_name" value="${sessionScope.user.firstName}"
               maxlength="32"><br>
        <span class="validation-message"><fmt:message key="validation.firstname"/></span>
    </div>
    <div class="form_input_container lastname">
        <fmt:message key="profile.last_name"/>
        <input type="text" name="last_name" value="${sessionScope.user.lastName}" maxlength="32"><br>
        <span class="validation-message"><fmt:message key="validation.lastname"/></span>
    </div>
    <div class="form_input_container phone">
        <fmt:message key="profile.phone"/>
        <input type="text" name="phone" value="${sessionScope.user.phone}" maxlength="13"><br>
        <span class="validation-message"><fmt:message key="validation.phone"/></span>
    </div>
    <div class="form_input_container info">
        <fmt:message key="profile.info"/>
        <textarea name="info" maxlength="500"><c:out value="${sessionScope.user.info}"/></textarea><br>
        <span class="validation-message"><fmt:message key="validation.info"/></span>
    </div>
    <div class="form_input_container price">
        <fmt:message key="profile.price"/>
        <input type="text" name="price" value="${sessionScope.user.pricePerHour}" maxlength="3"><br>
        <span class="validation-message"><fmt:message key="validation.price"/></span>
    </div>
    <fmt:message key="profile.isActive"/>
    <input type="checkbox" name="is_active" value="${user.isActive}"
           <c:if test="${user.isActive eq true}">checked</c:if>><br>
    <button type="submit" class="simple-btn"><fmt:message key="edit.profile.button"/></button>
    <button type="reset" class="simple-btn"><fmt:message key="edit.reset"/></button>
    <hr>
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
