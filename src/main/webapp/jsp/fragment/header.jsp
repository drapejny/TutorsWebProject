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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myjs.js"></script>

<header>
    <nav>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/controller?command=main_page">LOGO</a>
            </li>
            <li><a href="#"/> <fmt:message key="main.tutors"/></li>
            <li><a href="#"/> <fmt:message key="main.feedbacks"/></li>
        </ul>
    </nav>
    <c:if test="${empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/controller?command=login_page"><fmt:message key="header.login"/></a>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <br>
        <div class="dropdown">
            <button onclick="myFunction()" class="dropbtn">${sessionScope.user.firstName} ${sessionScope.user.lastName}</button>
            <div id="myDropdown" class="dropdown-content">
                <a href="${pageContext.request.contextPath}/controller?command=profile_page">Профиль</a>
                <a href="#">Редактировать</a>
                <a href="${pageContext.request.contextPath}/controller?command=logout">Выйти</a>
            </div>
        </div>
    </c:if>

    <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU">RU</a>
    <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_EN">EN</a>
    <hr>

</header>
