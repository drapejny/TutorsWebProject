<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="application.title"/></title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="add_application">
    <c:forEach var="element" items="${applicationScope.subjects}" varStatus="status">
        <input type="checkbox" name="subject" value="${element.subjectId}">${element.subjectName}<br>
    </c:forEach>
    <fmt:message key="application.phone"/><input type="text" name="phone" value="${requestScope.phone}" required pattern="^\+375[0-9]{9}$" maxlength="13"><br>
    <fmt:message key="application.city"/><input type="text" name="city" value="${requestScope.city}" required pattern="[A-zА-яЁё`'.-]{1,32}" maxlength="32"><br>
    <fmt:message key="application.education"/><textarea name="education" required maxlength="300">${requestScope.education}</textarea><br>
    <fmt:message key="application.info"/><textarea name="info" required maxlength="500">${requestScope.info}</textarea> <br>
    <fmt:message key="application.price"/><input type="number" name="price" value="${requestScope.price}" required min="1" max="999"><br>
    <button type="submit" class="simple-btn"><fmt:message key="application.submit.button"/></button>
    <span class="fail-message">${errorWrongDataMessage}</span>
</form>
</body>
</html>
