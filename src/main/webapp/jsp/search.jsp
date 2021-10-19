<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="search.tittle"/></title>
</head>
<body>
<div class="wrapper">
    <c:import url="fragment/header.jsp"/>
    <main class="main">
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
                <input type="number" id="amount1" name="min_price" required min="1" max="999" value="${minPrice}">
                <input type="number" id="amount2" name="max_price" required min="1" max="999" value="${maxPrice}">
                <button type="submit" class="simple-btn"><fmt:message key="search.button"/></button>
            </form>
        </div>

        <c:if test="${empty requestScope.tutors && requestScope.tutors != null}">
            <span class="not-found"><fmt:message key="search.notfound"/></span>
        </c:if>

        <c:if test="${not empty requestScope.tutors}">
            <div class="sort-block">
                <span><fmt:message key="search.sort-by-price"/></span>
                <a href="${pageContext.request.contextPath}/controller?command=search&sort=price_asc&page_number=1&subject=${subjectId}&city=${city}&min_price=${minPrice}&max_price=${maxPrice}">
                    <img src="${pageContext.request.contextPath}/img/up-arrow.svg" width="20" height="20">
                </a>
                <a href="${pageContext.request.contextPath}/controller?command=search&sort=price_desc&page_number=1&subject=${subjectId}&city=${city}&min_price=${minPrice}&max_price=${maxPrice}">
                    <img src="${pageContext.request.contextPath}/img/down-arrow.svg" width="20" height="20">
                </a>
            </div>
            <c:forEach var="element" items="${requestScope.tutors}">
                <ctg:user-photo photo="${element.photo}" height="100" width="100"/>
                <a href="${pageContext.request.contextPath}/controller?command=tutor_profile_page&tutor_id=${element.tutorId}">${element.firstName} ${element.lastName}</a>
                ${element.pricePerHour}
                <hr>
            </c:forEach>
            <div class="pages-block">
                <c:if test="${pageNumber != 1}">
                    <a href="${pageContext.request.contextPath}/controller?command=search&page_number=${pageNumber - 1}&sort=${sort}&subject=${subjectId}&city=${city}&min_price=${minPrice}&max_price=${maxPrice}"><fmt:message
                            key="search.prev"/></a>
                </c:if>
                <span class="pages-text">${pageNumber} <fmt:message key="search.of"/> ${pageCount}</span>
                <c:if test="${pageNumber != pageCount}">
                    <a href="${pageContext.request.contextPath}/controller?command=search&page_number=${pageNumber + 1}&sort=${sort}&subject=${subjectId}&city=${city}&min_price=${minPrice}&max_price=${maxPrice}"><fmt:message
                            key="search.next"/></a>
                </c:if>
            </div>
        </c:if>
        <br>
    </main>
    <c:import url="fragment/footer.jsp"/>
</div>
</body>
</html>
