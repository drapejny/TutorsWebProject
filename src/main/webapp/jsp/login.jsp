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
<div class="wrapper">
    <c:import url="fragment/header.jsp"/>
    <main class="main">
        <span class="success-message">${successRegistrationMessage}</span>
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="login"/>
            <fmt:message key="login.email"/>
            <input id="login-input" type="email" name="email" required maxlength="100"><br>
            <fmt:message key="login.password"/>
            <input id="login-input" type="password" name="password" required pattern="^\w{6,18}$" minlength="6"
                   maxlength="20"><br>
            <button type="submit" class="simple-btn"><fmt:message key="login.button.signin"/></button>
        </form>
        <span class="fail-message">${errorBlockedMessage}</span>
        <span class="fail-message">${errorNonActivatedMessage}</span>
        <span class="fail-message">${errorLogInMessage}</span>
        <a href="${pageContext.request.contextPath}/controller?command=registration_page">
            <fmt:message key="registration.button.signup"/>
        </a>
    </main>
    <c:import url="fragment/footer.jsp"/>
</div>
</body>
</html>
