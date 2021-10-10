<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="search.tittle"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/search.css" />
</head>
<body>
<c:import url="fragment/header.jsp"/>
<div class="search-block">
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="search">
        <select name="subject">
            <c:forEach var="element" items="${applicationScope.subjects}">
                <option value="${element.subjectId}" <c:if test="${subjectId == element.subjectId}">
                    selected
                </c:if>>${element.subjectName}</option>
            </c:forEach>
        </select>
        <select name="city">
            <c:forEach var="element" items="${applicationScope.cities}">
                <option value="${element}" <c:if test="${city == element}">
                    selected
                </c:if>>${element}</option>
            </c:forEach>
        </select>
        <input type="number" id="amount1" name="min_price" required min="1" max="999" value="${minPrice}"  >
        <input type="number" id="amount2" name="max_price" required min="1" max="999" value="${maxPrice}"  >
        <input type="submit" value="<fmt:message key="search.button"/>"><br>
    </form>
</div>

<c:if test="${empty requestScope.tutors && requestScope.tutors != null}">
    <span class="not-found"><fmt:message key="search.notfound"/></span>
</c:if>

<c:if test="${not empty requestScope.tutors}">
    <div class="sort-block">
        <a href="${pageContext.request.contextPath}/controller?command=search&sort=price_asc&page_number=1&subject=${subjectId}&city=${city}&min_price=${minPrice}&max_price=${maxPrice}"><fmt:message key="search.sort.low_high"/></a>
        <a href="${pageContext.request.contextPath}/controller?command=search&sort=price_desc&page_number=1&subject=${subjectId}&city=${city}&min_price=${minPrice}&max_price=${maxPrice}"><fmt:message key="search.sort.high_low"/></a>
    </div>
    <c:forEach var="element" items="${requestScope.tutors}">
        <ctg:user-photo photo="${element.photo}" height="100" width="100"/>
        <a href="${pageContext.request.contextPath}/controller?command=tutor_profile_page&tutor_id=${element.tutorId}">${element.firstName} ${element.lastName}</a>
        ${element.pricePerHour}
        <hr>
    </c:forEach>
    <div class="pages-block">
        <c:if test="${pageNumber != 1}">
            <a href="${pageContext.request.contextPath}/controller?command=search&page_number=${pageNumber - 1}&sort=${sort}&subject=${subjectId}&city=${city}&min_price=${minPrice}&max_price=${maxPrice}"><fmt:message key="search.prev"/></a>
        </c:if>
        <span class="pages-text">${pageNumber} <fmt:message key="search.of"/> ${pageCount}</span>
        <c:if test="${pageNumber != pageCount}">
            <a href="${pageContext.request.contextPath}/controller?command=search&page_number=${pageNumber + 1}&sort=${sort}&subject=${subjectId}&city=${city}&min_price=${minPrice}&max_price=${maxPrice}"><fmt:message key="search.next"/></a>
        </c:if>
    </div>
</c:if>
<br>


</body>
</html>
