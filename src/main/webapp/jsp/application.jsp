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
    <title><fmt:message key="application.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/application.css" />
</head>
<body>
<c:import url="fragment/header.jsp"/>
<c:if test="${user.role eq 'USER'}">
    <span class="success-message"><fmt:message key="application.wait"/></span>
</c:if>
<div class="tutor_profile">
    <div class="left_part">
        <div class="img">
            <ctg:user-photo photo="${requestScope.application.photo}" height="200" width="200"/>
        </div>
    </div>
    <div class="right_part">
        <div class="name">${requestScope.application.firstName} ${application.lastName}</div>
        <div class="phone">${application.phone}</div>
        <div class="email">${application.email}</div>
        <ul>
            <c:forEach var="element" items="${requestScope.subjects}">
                <li>${element.subjectName}</li>
            </c:forEach>
        </ul>
        <div class="city"><span><fmt:message key="application.city"/></span>${application.city}</div>
        <div class="education_title"><fmt:message key="application.education"/></div>
        <div class="education"><c:out value="${application.education}"/></div>
        <div class="info_title"><fmt:message key="application.info"/></div>
        <div class="info"><c:out value="${application.info}"/></div>
        <div class="price_per_hour"><fmt:message key="application.price"/> <span>${application.pricePerHour} BYN</span></div>
        <c:if test="${user.role eq 'ADMIN'}">
            <div class="controls">
                <a href="${pageContext.request.contextPath}/controller?command=accept_application&user_id=${application.userId}">
                    <button class="accept"><fmt:message key="application.accept"/></button>
                </a>
                <a href="${pageContext.request.contextPath}/controller?command=reject_application&tutor_id=${application.tutorId}">
                    <button class="reject"><fmt:message key="application.reject"/></button>
                </a>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
