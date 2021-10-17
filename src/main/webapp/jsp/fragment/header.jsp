<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/header.js"></script>

<header>

    <div class="header__section">
        <a href="${pageContext.request.contextPath}/controller?command=main_page"><h1>tutorz.by</h1></a>
    </div>
    <div class="header__section">
        <nav>
            <a href="${pageContext.request.contextPath}/controller?command=search_page">
                <fmt:message key="main.tutors"/>
            </a>
            <c:if test="${sessionScope.user.role == 'USER'}">
                <a href="${pageContext.request.contextPath}/controller?command=become_tutor"><fmt:message
                        key="profile.become_tutor"/></a>
            </c:if>
            <c:if test="${sessionScope.user.role == 'ADMIN'}">
                <a href="${pageContext.request.contextPath}/controller?command=search_users_page">
                    <fmt:message key="main.users"/>
                </a>
                <a href="${pageContext.request.contextPath}/controller?command=all_subjects_page">
                    <fmt:message key="main.subjects"/>
                </a>
                <a href="${pageContext.request.contextPath}/controller?command=all_applications_page">
                    <fmt:message key="main.applications"/>
                </a>
                <c:if test="${sessionScope.user.email eq 'admin@admin.com'}">
                    <a href="${pageContext.request.contextPath}/controller?command=all_admins_page">
                        <fmt:message key="admin.admins"/>
                    </a>
                </c:if>

            </c:if>
            <a href="${pageContext.request.contextPath}/controller?command=about_us_page">
                <fmt:message key="main.aboutus"/>
            </a>
        </nav>
    </div>
    <div class="header__section">
        <c:if test="${sessionScope.user.role eq 'GUEST'}">
            <a href="${pageContext.request.contextPath}/controller?command=login_page"><fmt:message
                    key="header.login"/></a>
        </c:if>
        <c:if test="${sessionScope.user.role ne 'GUEST'}">
            <br>
            <div onmouseenter="myFunction()" onmouseleave="myFunction()" class="dropdown">
                <button class="dropbtn">
                    <ctg:user-photo photo="${sessionScope.user.photo}" height="50" width="50"/>
                        ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                </button>
                <div id="myDropdown" class="dropdown-content">
                    <c:choose>
                        <c:when test="${sessionScope.user.role eq 'USER' || sessionScope.user.role eq 'ADMIN'}">
                            <a href="${pageContext.request.contextPath}/controller?command=edit_profile_page"><fmt:message
                                    key="header.dropdown.profile"/></a>
                        </c:when>
                        <c:when test="${sessionScope.user.role eq 'TUTOR'}">
                            <a href="${pageContext.request.contextPath}/controller?command=tutor_profile_page&tutor_id=${sessionScope.user.tutorId}"><fmt:message
                                    key="header.dropdown.profile"/></a>
                        </c:when>
                    </c:choose>
                    <a href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message
                            key="header.dropdown.logout"/></a>
                </div>
            </div>
        </c:if>

        <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=ru_RU"><img
                src="${pageContext.request.contextPath}/img/ru.png" alt="ru"></a>
        <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=en_EN"><img
                src="${pageContext.request.contextPath}/img/en.png" alt="en"></a>
        <hr>
    </div>
</header>
