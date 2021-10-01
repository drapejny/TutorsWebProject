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
<h3><fmt:message key="application.wait_for_moderator"/></h3>
<fmt:message key="application.phone"/>${requestScope.tutor.phone}<br>
<fmt:message key="application.city"/>${requestScope.tutor.city}<br>
<fmt:message key="application.education"/>${requestScope.tutor.education}<br>
<fmt:message key="application.info"/>${requestScope.tutor.info}<br>
<fmt:message key="application.price"/>${requestScope.tutor.pricePerHour}<br>
<a href="${pageContext.request.contextPath}/controller?command=delete_application"><fmt:message key="application.delete"/></a>
</body>
</html>
