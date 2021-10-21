<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/tutor/tutor_profile.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/application.css">

<html>
<head>
    <title><fmt:message key="profile.title"/></title>
</head>
<body>
<div class="wrapper">
    <c:import url="/jsp/fragment/header.jsp"/>
    <main class="main">
        <div class="tutor_profile">
            <div class="left_part">
                <ctg:user-photo photo="${requestScope.tutor.photo}" height="200" width="200"/><br>
                <c:if test="${user.role eq 'TUTOR' && user.tutorId eq requestScope.tutor.tutorId}">
                    <a href="${pageContext.request.contextPath}/controller?command=edit_tutor_profile_page"><fmt:message
                            key="edit.profile.button"/></a>
                </c:if>
            </div>
            <div class="right_part">
                <div class="name">${requestScope.tutor.firstName} ${requestScope.tutor.lastName}</div>
                <c:if test="${tutor.userId eq user.userId}">
                    <div class="email">${requestScope.tutor.email}</div>
                </c:if>
                <div class="phone">${application.phone}</div>
                <div class="phone">
                    <c:choose>
                        <c:when test="${user.role eq 'GUEST'}">
                            <fmt:message key="profile.phone"/>
                            <a href="${pageContext.request.contextPath}/controller?command=login_page"><fmt:message
                                    key="header.login"/></a>
                        </c:when>
                        <c:when test="${tutor.isActive}">
                            ${tutor.phone}
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="profile.noPlaces"/>
                        </c:otherwise>
                    </c:choose><br>
                </div>
                <ul>
                    <c:forEach var="element" items="${subjects}">
                        <li>${element.subjectName}</li>
                    </c:forEach>
                </ul>
                <div class="city">${requestScope.tutor.city}</div>
                <div class="education_title"><fmt:message key="profile.education"/></div>
                <div class="education">
                    <span><c:out value="${requestScope.tutor.education}"/></span>
                </div>
                <div class="info_title"><fmt:message key="profile.info"/></div>
                <div class="info">
                    <span><c:out value="${requestScope.tutor.info}"/></span>
                </div>
                <div class="price_per_hour"><fmt:message key="profile.price"/><br>
                    <span>${requestScope.tutor.pricePerHour} BYN</span></div>
                <div class="rating_title"><fmt:message key="profile.rating"/></div>
                <c:set var="sumRating" value="${0}"/>
                <c:forEach var="element" items="${feedbacks}">
                    <c:set var="sumRating" value="${sumRating + element.rating}"/>
                </c:forEach>
                <c:set var="averageRating"><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"
                                                             value="${sumRating / fn:length(feedbacks)}"/></c:set>
                <c:if test="${fn:length(feedbacks) eq 0}">
                    <c:set var="averageRating" value="${0}"/>
                </c:if>
                <c:choose>
                    <c:when test="${averageRating == '0'}">
                        <div class="rating_text">---</div>
                    </c:when>
                    <c:otherwise>
                        <div class="rating_text">${averageRating}</div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="comment-block">
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
                                            <input id="rating__${6 - i}" type="radio" required class="rating__item"
                                                   <c:if test="${yourFeedback.rating eq (6 - i)}">checked</c:if>
                                                   name="rating" value="${6 - i}">
                                            <label for="rating__${6 - i}" class="rating__label"></label>
                                        </c:forEach>
                                    </div>
                                </div>
                                <textarea name="text" required rows="5" cols="60" maxlength="300"><c:out
                                        value="${yourFeedback.text}"/></textarea>
                                <button type="submit" class="simple-btn"><fmt:message key="profile.edit"/></button>
                            </form>
                            <form class="red-btn-form" action="${pageContext.request.contextPath}/controller"
                                  method="post">
                                <input type="hidden" name="command" value="delete_feedback">
                                <input type="hidden" name="feedback_id" value="${yourFeedback.feedbackId}">
                                <input type="hidden" name="tutor_id" value="${tutor.tutorId}">
                                <button type="submit" class="red-btn"><fmt:message key="profile.delete"/></button>
                            </form>
                        </c:when>
                        <c:when test="${contains eq false}">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="add_feedback">
                                <input type="hidden" name="tutor_id" value="${tutor.tutorId}">
                                <div class="rating">
                                    <div class="rating__items">
                                        <c:forEach var="i" begin="1" end="5">
                                            <input id="rating__${6 - i}" type="radio" required class="rating__item"
                                                   name="rating"
                                                   value="${6 - i}">
                                            <label for="rating__${6 - i}" class="rating__label"></label>
                                        </c:forEach>
                                    </div>
                                </div>
                                <textarea name="text" required maxlength="300"></textarea>
                                <button type="submit" class="simple-btn"><fmt:message
                                        key="profile.addFeedback"/></button>
                                <br>
                            </form>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
        </div>
        <div class="feedbacks-block">
            <c:forEach var="element" items="${feedbacks}">
                <div class="feedbacks-block__item">
                    <div class="feedbacks-block__item__left">
                        <ctg:user-photo photo="${users[element].photo}" height="40" width="40"/>
                    </div>
                    <div class="feedbacks-block__item__right">
                        <div>
                            <span class="feedback-name">${users[element].firstName} ${users[element].lastName}</span>
                            <span class="feedback-date">${element.date}</span>
                        </div>
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
                        <div class="feedback-textarea">
                            <textarea readonly rows="5" cols="60"><c:out value="${element.text}"/></textarea>
                        </div>
                        <c:if test="${user.role eq 'ADMIN' || user.userId eq tutor.userId}">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="delete_feedback">
                                <input type="hidden" name="tutor_id" value="${tutor.tutorId}">
                                <input type="hidden" name="feedback_id" value="${element.feedbackId}">
                                <button type="submit" class="red-btn"><fmt:message key="profile.delete"/></button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
    <c:import url="/jsp/fragment/footer.jsp"/>
</div>
</body>
</html>
