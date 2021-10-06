<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="customtags" prefix="ctg"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tutor/tutor_profile.css">

<html>
<head>
    <title><fmt:message key="profile.title"/></title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<ctg:user-photo photo="${requestScope.tutor.photo}" height="200" width="200"/><br>
<h3>${requestScope.tutor.firstName} ${requestScope.tutor.lastName}</h3>
<c:forEach var="element" items="${subjects}">
    ${element.subjectName}<br>
</c:forEach>
<br>
<c:if test="${tutor.userId eq user.userId}">
    <fmt:message key="profile.email"/>${requestScope.tutor.email}<br>
</c:if>
<fmt:message key="profile.phone"/>
<c:choose>
    <c:when test="${empty user}">
        <a href="${pageContext.request.contextPath}/controller?command=login"><fmt:message key="header.login"/></a>
    </c:when>
    <c:when test="${tutor.isActive}">
        ${tutor.phone}
    </c:when>
    <c:otherwise>
        <fmt:message key="profile.noPlaces"/>
    </c:otherwise>
</c:choose><br>
<fmt:message key="profile.city"/>${requestScope.tutor.city}<br>
<fmt:message key="profile.education"/>${requestScope.tutor.education}<br>
<fmt:message key="profile.info"/>${requestScope.tutor.info}<br>
<fmt:message key="profile.price"/>${requestScope.tutor.pricePerHour}<br>
<fmt:message key="profile.rating"/>
<c:set var="sumRating" value="${0}"/>
<c:forEach var="element" items="${feedbacks}">
    <c:set var="sumRating" value="${sumRating + element.rating}"/>
</c:forEach>
<c:set var="averageRating"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${sumRating / fn:length(feedbacks)}"/></c:set>
<c:if test="${fn:length(feedbacks) eq 0}">
    <c:set var="averageRating" value="${0}"/>
</c:if>

${averageRating}

<c:choose>
    <c:when test="${user.role eq 'USER'}">
        <c:set var="contains" value="false"/>
        <c:set var="yourFeedback"/>
        <c:forEach var="element" items="${feedbacks}">
            <c:if test="${element.userId eq sessionScope.user.userId}">
                <c:set var="contains" value="true"/>
                <c:set var="yourFeedback" value="${element}"/>
            </c:if>
        </c:forEach>
        <c:choose>
            <c:when test="${contains eq true}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="edit_feedback">
                    <input type="hidden" name="feedback_id" value="${yourFeedback.feedbackId}">
                    <input type="hidden" name="tutor_id" value="${tutor.tutorId}">
                    <div class="rating">
                        <div class="rating__items">
                            <c:forEach var="i" begin="1" end="5">
                                <input id="rating__${6 - i}" type="radio" class="rating__item" <c:if test="${yourFeedback.rating eq (6 - i)}">checked</c:if> name="rating" value="${6 - i}">
                                <label for="rating__${6 - i}" class="rating__label"></label>
                            </c:forEach>
                        </div>
                    </div>
                    <textarea name="text">${yourFeedback.text}</textarea>
                    <input type="submit" value="<fmt:message key="profile.edit"/>">
                    <a href="${pageContext.request.contextPath}/controller?command=delete_feedback&feedback_id=${yourFeedback.feedbackId}&tutor_id=${tutor.tutorId}"><fmt:message key="profile.delete"/></a>
                    <br>
                </form>
            </c:when>
            <c:when test="${contains eq false}">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="add_feedback">
                    <input type="hidden" name="tutor_id" value="${tutor.tutorId}">
                    <div class="rating">
                        <div class="rating__items">
                            <c:forEach var="i" begin="1" end="5">
                                <input id="rating__${6 - i}" type="radio" class="rating__item" name="rating" value="${6 - i}">
                                <label for="rating__${6 - i}" class="rating__label"></label>
                            </c:forEach>
                        </div>
                    </div>
                    <textarea name="text"></textarea>
                    <input type="submit" value="<fmt:message key="profile.addFeedback"/>">
                    <br>
                </form>
            </c:when>
        </c:choose>
    </c:when>
    <c:when test="${user.role eq 'TUTOR'}">
        <br>
        <a href="${pageContext.request.contextPath}/controller?command=edit_tutor_profile_page"><fmt:message key="edit.profile.button"/></a>
        <br>
    </c:when>
</c:choose>

<p><fmt:message key="profile.feedbacks"/>${fn:length(feedbacks)}</p>

<c:forEach var="element" items="${feedbacks}">
    <ctg:user-photo photo="${users[element].photo}" height="20" width="20"/>
    <p>${users[element].firstName} ${users[element].lastName}</p>
    ${element.date}<br>
    ${element.text}<br>
    <div class="rating-mini">
        <c:forEach var="i" begin="1" end="5">
            <c:choose>
                <c:when test="${i <= element.rating}">
                    <span class="active"></span>
                </c:when>
                <c:otherwise>
                    <span></span>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </div>
    <c:if test="${user.role eq 'ADMIN' || user.userId eq tutor.userId}">
        <a href="${pageContext.request.contextPath}/controller?command=delete_feedback&tutor_id=${tutor.tutorId}&feedback_id=${element.feedbackId}"><fmt:message key="profile.delete"/></a>
    </c:if>
    <hr>
</c:forEach>




</body>
</html>
