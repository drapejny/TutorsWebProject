<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="application.title"/></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/form_validation.js"></script>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<form id="app_form" action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="add_application">
    <div class="form_container">
        <div class="left_part">
            <div class="form_input_container subjects">
                <span class="validation-message"><fmt:message key="validation.subjects"/></span>
                <c:forEach var="element" items="${applicationScope.subjects}">
                    <input class="subject_checkbox" type="checkbox" name="subject"
                           value="${element.subjectId}">${element.subjectName}<br>
                </c:forEach>
            </div>
        </div>
        <div class="right_part">
            <fmt:message key="application.phone"/>
            <div class="form_input_container phone">
                <input class="form_input" type="text" name="phone" value="${requestScope.phone}" maxlength="13"><br>
                <span class="validation-message"><fmt:message key="validation.phone"/></span>
            </div>
            <fmt:message key="application.city"/>
            <div class="form_input_container city">
                <input class="form_input" type="text" name="city" value="${requestScope.city}" maxlength="32"><br>
                <span class="validation-message"><fmt:message key="validation.city"/></span>
            </div>
            <fmt:message key="application.education"/>
            <div class="form_input_container education">
                    <textarea class="form_input" name="education"
                              maxlength="300" rows="6" cols="50">${requestScope.education}</textarea><br>
                <span class="validation-message"><fmt:message key="validation.education"/></span>
            </div>
            <fmt:message key="application.info"/>
            <div class="form_input_container info">
                <textarea class="form_input" name="info" maxlength="500" rows="10" cols="50">${requestScope.info}</textarea><br>
                <span class="validation-message"><fmt:message key="validation.info"/></span>
            </div>
            <fmt:message key="application.price"/>
            <div class="form_input_container price">
                <input class="form_input" type="text" name="price" value="${requestScope.price}" maxlength="3"><br>
                <span class="validation-message"><fmt:message key="validation.price"/></span>
            </div>
            <button type="submit" class="simple-btn"><fmt:message key="application.submit.button"/></button>
        </div>
    </div>
</form>
</body>
</html>
