<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title>
        <fmt:message key="profile.title"/>
    </title>
</head>
<body>
<c:import url="../fragment/header.jsp"/>
<ctg:user-photo photo="${sessionScope.user.photo}" height="100" width="100"/>
<h3>${sessionScope.user.firstName} ${sessionScope.user.lastName}</h3><br>
<fmt:message key="profile.email"/> ${sessionScope.user.email}<br>
<a href="${pageContext.request.contextPath}/controller?command=edit_profile_page"><fmt:message key="profile.edit"/></a>
</body>
</html>
