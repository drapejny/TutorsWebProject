<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="customtags" prefix="ctg"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="main.applications"/></title>
    </head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<c:choose>
    <c:when test="${empty applications}">
        <span class="no-apps"><fmt:message key="application.noapps"/></span>
    </c:when>
    <c:otherwise>
        <div class="applications__container">
            <ul class="applications__list">
                <c:forEach var="element" items="${requestScope.applications}">
                    <a style="display: block" href="${pageContext.request.contextPath}/controller?command=application_page&tutor_id=${element.tutorId}">
                        <li class="applications__item">
                            <ctg:user-photo photo="${element.photo}" height="50" width="50"/>
                            <span class="first-last-name">${element.firstName} ${element.lastName}</span>
                            <span class="phone">${element.phone}</span>
                        </li>
                    </a>
                </c:forEach>
            </ul>
            <c:if test="${pageNumber != 1}">
                <a href="${pageContext.request.contextPath}/controller?command=all_applications_page&page_number=${pageNumber - 1}">предыдущая</a>
            </c:if>
                ${pageNumber}из${pageCount}
            <c:if test="${pageNumber != pageCount}">
                <a href="${pageContext.request.contextPath}/controller?command=all_applications_page&page_number=${pageNumber + 1}">следующая</a>
            </c:if>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>