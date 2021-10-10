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
    <form class="login-form" action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <fmt:message key="login.email"/>
        <input id="login-input" type="email" name="email" required maxlength="100"><br>
        <fmt:message key="login.password"/>
        <input id="login-input"  type="password" name="password" required pattern="^\w{6,18}$"  minlength="6" maxlength="20"><br>
        <input type="submit" value="<fmt:message key="login.button.signin"/>">
        <a href="${pageContext.request.contextPath}/controller?command=registration_page" method="post"><fmt:message key="header.registration"/></a>
        ${errorBlockedMessage}
        ${errorNonActivatedMessage}
        ${errorLogInMessage}<br>
    </form>
</div>

</body>
</html>
