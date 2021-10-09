
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customtags" prefix="ctg"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mycss.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/myjs.js"></script>

<header>
    <div class="header__container">
        <div class="header__logo">
            <a href="${pageContext.request.contextPath}/controller?command=main_page">LOGO</a>
        </div>
        <ul class="header__menu menu">
            <li class="menu__item">
                <a class="link menu__link" href="${pageContext.request.contextPath}/controller?command=search_page">
                    <fmt:message key="main.tutors"/>
                </a>
            </li>
            <c:if test="${sessionScope.user.role == 'ADMIN'}">
                <li class="menu__item">
                    <a class="link menu__link" href="${pageContext.request.contextPath}/controller?command=search_users_page">
                        <fmt:message key="main.users"/>
                    </a>
                </li>
                <li class="menu__item">
                    <a class="link menu__link" href="${pageContext.request.contextPath}/controller?command=all_subjects_page">
                        <fmt:message key="main.subjects"/>
                    </a>
                </li>
                <li class="menu__item">
                    <a class="link menu__link" href="${pageContext.request.contextPath}/controller?command=all_applications_page">
                        <fmt:message key="main.applications"/>
                    </a>
                </li>
            </c:if>
            <li class="menu__item">
                <a class="link meny__link" href="#">
                    <fmt:message key="main.aboutus"/>
                </a>
            </li>
        </ul>
    </div>

    <c:if test="${sessionScope.user.role eq 'GUEST'}">
        <a href="${pageContext.request.contextPath}/controller?command=login_page"><fmt:message key="header.login"/></a>
    </c:if>
    <c:if test="${sessionScope.user.role ne 'GUEST'}">
        <br>
        <ctg:user-photo photo="${sessionScope.user.photo}" height="50" width="50"/>
        <div class="dropdown">
            <button onclick="myFunction()" class="dropbtn">${sessionScope.user.firstName} ${sessionScope.user.lastName}</button>
            <div id="myDropdown" class="dropdown-content">
                <c:choose>
                    <c:when test="${sessionScope.user.role eq 'USER' || sessionScope.user.role eq 'ADMIN'}">
                        <a href="${pageContext.request.contextPath}/controller?command=profile_page"><fmt:message key="header.dropdown.profile"/></a>
                    </c:when>
                    <c:when test="${sessionScope.user.role eq 'TUTOR'}">
                        <a href="${pageContext.request.contextPath}/controller?command=tutor_profile_page&tutor_id=${sessionScope.user.tutorId}"><fmt:message key="header.dropdown.profile"/></a>
                    </c:when>
                </c:choose>
                <a href="#"><fmt:message key="header.dropdown.bookmarks"/></a>
                <a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="header.dropdown.logout"/></a>
            </div>
        </div>
    </c:if>

    <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU&current_url=${pageContext.request.requestURL}"><img src="${pageContext.request.contextPath}/img/ru.png" alt="ru"></a>
    <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_EN&current_url=${pageContext.request.requestURL}"><img src="${pageContext.request.contextPath}/img/en.png" alt="en"></a>
    <hr>
</header>
