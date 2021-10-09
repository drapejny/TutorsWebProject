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
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/search_user.css" />
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<div class="search-container">
    <div class="search-block">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="search_users">
            <input type="text" class="search-line" name="search_line">
            <input type="submit" class="search-button" value="<fmt:message key="search.button"/>">
        </form>
    </div>
    <div class="main_table">
        <div class="table_body">
            <c:forEach var="element" items="${users}">
                <div class="table_row">
                    <div class="table_item"><ctg:user-photo photo="${element.photo}" height="50" width="50"/></div>
                    <div class="table_item">${element.lastName} ${element.firstName}</div>
                    <div class="table_item">${element.email}</div>
                    <div class="table_item">${element.role}</div>
                    <div class="table_item">${element.status}</div>
                    <div class="table_item">
                        <button class="white_btn">забанить</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    </ul>
</div>

</body>
</html>
