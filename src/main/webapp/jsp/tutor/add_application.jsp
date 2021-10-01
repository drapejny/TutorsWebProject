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
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="add_application">
    <c:forEach var="element" items="${sessionScope.subjects}" varStatus="status">
        <input type="checkbox" name="subject" value="${element.subjectId}">${element.subjectName}<br>
    </c:forEach>
    <fmt:message key="application.phone"/><input type="text" name="phone" value="${requestScope.phone}"><br>
    <fmt:message key="application.city"/><input type="text" name="city" value="${requestScope.city}"><br>
    <fmt:message key="application.education"/><input type="text" name="education" value="${requestScope.education}"><br>
    <fmt:message key="application.info"/><input type="text" name="info" value="${requestScope.info}"><br>
    <fmt:message key="application.price"/><input type="text" name="price" value="${requestScope.price}"><br>
    <input type="submit" value="<fmt:message key="application.submit.button"/>">
    ${errorWrongDataMessage}
</form>
</body>
</html>
