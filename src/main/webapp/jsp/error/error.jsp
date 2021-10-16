<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html><title>Error Page</title>
<body>
<c:if test="${not empty pageContext.exception}">
<c:set var="exception" value="${pageContext.exception}"/>
        <h1 >${head}</h1>
        <h3>${error_head} ${exception} </h3>
        <h3> Stack trace:</h3>
            <c:forEach var="element" items="${exception.stackTrace}">
                ${element}
                <br/>
            </c:forEach>
        </p>
</c:if>
Exception: ${exception}
<br/>
<hr/>
Message from exception: ${exception.message}
</body>
</html>