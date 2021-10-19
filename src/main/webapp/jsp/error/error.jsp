<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<c:if test="${not empty pageContext.exception}">
    <h3>${pageContext.exception} </h3>
    <h3> Stack trace:</h3>
    <c:forEach var="element" items="${pageContext.exception.stackTrace}">
        ${element}
        <br/>
    </c:forEach>
</c:if>
<c:if test="${not empty requestScope.exception}">
    <c:set var="exception" value="${requestScope.exception}"/>
    <h3>${exception} </h3>
    <h3> Stack trace:</h3>
    <c:forEach var="element" items="${exception.stackTrace}">
        ${element}
        <br/>
    </c:forEach>
    </p>
</c:if>
</body>
</html>