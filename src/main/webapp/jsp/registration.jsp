<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title>
        <fmt:message key="registration.title"/>
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/registration_form_validation.js"></script>
</head>
<body>
<div class="wrapper">
    <c:import url="fragment/header.jsp"/>
    <main class="main">
        <form id="registration_form" action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="registration">
            <div class="form_container">
                <div class="form_container_registration_item">
                    <div class="form_input_container firstname">
                        <fmt:message key="registration.first_name"/>
                        <input class="form_input" type="text" name="first_name" maxlength="32"><br>
                        <span class="validation-message"><fmt:message key="validation.firstname"/></span>
                    </div>
                    <div class="form_input_container lastname">
                        <fmt:message key="registration.last_name"/>
                        <input class="form_input" type="text" name="last_name" maxlength="32"><br>
                        <span class="validation-message"><fmt:message key="validation.lastname"/></span>
                    </div>
                    <div class="form_input_container email">
                        <fmt:message key="registration.email"/>
                        <input class="form_input" type="email" name="email" maxlength="100"><br>
                        <span class="validation-message"><fmt:message key="validation.email"/></span>
                    </div>
                    <div class="form_input_container password">
                        <fmt:message key="registration.password"/>
                        <input id="password_1" class="form_input" type="password" name="password" maxlength="20"><br>
                        <span class="bad-passwords-message"><fmt:message key="validation.bad-passwords"/></span>
                        <span class="validation-message"><fmt:message key="validation.password"/></span>
                    </div>
                    <div class="form_input_container password">
                        <fmt:message key="registration.password_repeat"/>
                        <input id="password_2" class="form_input" type="password" name="password_repeat" maxlength="20"><br>
                        <span class="bad-passwords-message"><fmt:message key="validation.bad-passwords"/></span>
                        <span class="validation-message"><fmt:message key="validation.password"/></span>
                    </div>
                    <span class="fail-message">${errorEmailExistsMessage}</span>
                    <span class="fail-message">${errorWrongDataMessage}</span>
                    <button type="submit" class="simple-btn"><fmt:message key="registration.button.signup"/></button>
                </div>
            </div>
        </form>
    </main>
    <c:import url="fragment/footer.jsp"/>
</div>
</body>
</html>
