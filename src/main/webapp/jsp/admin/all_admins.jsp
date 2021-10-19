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
    <title><fmt:message key="admin.all-admins.title"/></title>
</head>
<body>
<div class="wrapper">
    <c:import url="/jsp/fragment/header.jsp"/>
    <main class="main">
        <div class="main_table">
            <div class="table_body">
                <c:forEach var="element" items="${admins}">
                    <div class="table_row">
                        <div class="table_item"><ctg:user-photo photo="${element.photo}" height="50" width="50"/></div>
                        <div class="table_item">${element.lastName} ${element.firstName}</div>
                        <div class="table_item">${element.email}</div>
                        <div class="table_item">${element.role}</div>
                        <div class="table_item">${element.status}</div>
                        <div class="table_item">
                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="delete_admin">
                                <input type="hidden" name="user_id" value="${element.userId}">
                                <button type="submit" class="block_btn"><fmt:message key="admin.delete-admin"/></button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </main>
    <c:import url="/jsp/fragment/footer.jsp"/>
</div>
</body>
</html>
