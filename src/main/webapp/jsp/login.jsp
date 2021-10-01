<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="login"/>
    <fmt:message key="login.email"/><input type="text" name="email"><br>
    <fmt:message key="login.password"/><input type="text" name="password"><br>
    <a href="${pageContext.request.contextPath}/controller?command=registration_page" method="post"><fmt:message key="header.registration"/></a>
    ${errorBlockedMessage}
    ${errorNonActivatedMessage}
    ${errorLogInMessage}<br>
    <input type="submit" value="<fmt:message key="login.button.signin"/>">
</form>
</body>
</html>
