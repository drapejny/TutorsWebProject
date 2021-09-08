<%--
  Created by IntelliJ IDEA.
  User: Slizh Anton
  Date: 06.09.2021
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title>
        <fmt:message key="profile.title"/>
    </title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<h3>${sessionScope.user.firstName} ${sessionScope.user.lastName}</h3><br>
<fmt:message key="profile.email"/> ${sessionScope.user.email}<br>
<fmt:message key="profile.phone"/> ${sessionScope.user.phone}<br>
<fmt:message key="profile.city"/> ${sessionScope.user.city}<br>
</body>
</html>
