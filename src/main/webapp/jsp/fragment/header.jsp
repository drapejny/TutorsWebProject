<%--
  Created by IntelliJ IDEA.
  User: Slizh Anton
  Date: 04.09.2021
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>

<header>
    <nav>
        <ul>
            <li>LOGO</li>
            <li><a href="#"/> <fmt:message key="main.tutors"/></li>
            <li><a href="#"/> <fmt:message key="main.feedbacks"/></li>
        </ul>
    </nav>
    <c:if test="${empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/controller?command=login_page"><fmt:message key="header.login"/></a>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/controller?command=profile_page">${sessionScope.user.firstName} ${sessionScope.user.lastName}</a>
    </c:if>

    <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU">RU</a>
    <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_EN">EN</a>

</header>
