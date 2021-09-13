<%--
  Created by IntelliJ IDEA.
  User: Slizh Anton
  Date: 13.09.2021
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="confirmaion.title"/></title>
</head>
<body>
<fmt:message key="confirmation.message"/><br>
<a href="${pageContext.request.contextPath}/controller?command=main_page"><fmt:message key="confirmation.gotomain"/></a>
</body>
</html>
