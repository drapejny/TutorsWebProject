<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="main.title"/></title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
MIAN PAGE! !!! ! !
<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <ul>
        <li><a href="#"><fmt:message key="main.users"/></a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=all_subjects_page"><fmt:message key="main.subjects"/></a></li>
        <li><a href="#"><fmt:message key="main.feedbacks"/></a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=all_applications_page"><fmt:message key="main.applications"/></a></li>
    </ul>
</c:if><br>
</body>
</html>
