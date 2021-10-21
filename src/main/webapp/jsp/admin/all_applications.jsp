<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="main.applications"/></title>
</head>
<body>
<div class="wrapper">
    <c:import url="/jsp/fragment/header.jsp"/>
    <main class="main">
        <c:choose>
            <c:when test="${empty applications}">
                <span class="no-apps"><fmt:message key="application.noapps"/></span>
            </c:when>
            <c:otherwise>
                <div class="applications__container">
                    <c:forEach var="element" items="${requestScope.applications}">
                        <div class="application__item">
                            <div class="application__item__left">
                                <ctg:user-photo photo="${element.photo}" height="100" width="100"/>
                            </div>
                            <div class="application__item___right">
                                <div class="name">
                                    <a href="${pageContext.request.contextPath}/controller?command=application_page&tutor_id=${element.tutorId}">
                                        <span>${element.firstName} ${element.lastName}</span>
                                    </a>
                                </div>
                                <div class="phone">
                                    <span class="phone">${element.phone}</span>
                                </div>
                                <div class="email">
                                    <span class="email">${element.email}</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="pages-block">
                        <c:if test="${pageNumber != 1}">
                            <a href="${pageContext.request.contextPath}/controller?command=all_applications_page&page_number=${pageNumber - 1}">предыдущая</a>
                        </c:if>
                            ${pageNumber}из${pageCount}
                        <c:if test="${pageNumber != pageCount}">
                            <a href="${pageContext.request.contextPath}/controller?command=all_applications_page&page_number=${pageNumber + 1}">следующая</a>
                        </c:if>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </main>
    <c:import url="/jsp/fragment/footer.jsp"/>
</div>
</body>
</html>