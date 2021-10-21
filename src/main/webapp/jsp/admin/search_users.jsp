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
    <title><fmt:message key="users.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="wrapper">
    <c:import url="/jsp/fragment/header.jsp"/>
    <main class="main">
        <div class="search-container">
            <div class="search-block">
                <form action="${pageContext.request.contextPath}/controller" method="get">
                    <input type="hidden" name="command" value="search_users">
                    <input type="text" class="search-line" name="search_line" required maxlength="100">
                    <button type="submit" class="simple-btn"><fmt:message key="search.button"/></button>
                </form>
            </div>
            <span class="success-message">${successBlockUserMessage}</span>
            <span class="success-message">${successUnblockUserMessage}</span>
            <div class="main_table">
                <div class="table_body">
                    <c:forEach var="element" items="${users}">
                        <div class="table_row">
                            <c:choose>
                                <c:when test="${element.role eq 'TUTOR'}">
                                    <div class="table_item"><a
                                            href="${pageContext.request.contextPath}/controller?command=tutor_profile_page&tutor_id=${tutorsMap[element].tutorId}"><ctg:user-photo
                                            photo="${element.photo}" height="50" width="50"/></a></div>
                                    <div class="table_item"><a
                                            href="${pageContext.request.contextPath}/controller?command=tutor_profile_page&tutor_id=${tutorsMap[element].tutorId}">${element.lastName} ${element.firstName}</a>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="table_item"><ctg:user-photo photo="${element.photo}" height="50"
                                                                            width="50"/></div>
                                    <div class="table_item">${element.lastName} ${element.firstName}</div>
                                </c:otherwise>
                            </c:choose>

                            <div class="table_item">${element.email}</div>
                            <div class="table_item">${element.role}</div>
                            <div class="table_item">${element.status}</div>
                            <div class="table_item">
                                <c:if test="${element.role ne 'ADMIN'}">
                                    <c:choose>
                                        <c:when test="${element.status eq 'ACTIVATED'}">
                                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                                <input type="hidden" name="command" value="block_user">
                                                <input type="hidden" name="user_id" value="${element.userId}">
                                                <button type="submit" class="block_btn"><fmt:message
                                                        key="admin.block.btn"/></button>
                                            </form>
                                            <c:if test="${element.role eq 'USER' && sessionScope.user.email eq 'admin@admin.com'}">
                                                <form action="${pageContext.request.contextPath}/controller"
                                                      method="post">
                                                    <input type="hidden" name="command" value="add_admin">
                                                    <input type="hidden" name="user_id" value="${element.userId}">
                                                    <button type="submit" class="unblock_btn"><fmt:message
                                                            key="admin.add-admin"/></button>
                                                </form>
                                            </c:if>
                                        </c:when>
                                        <c:when test="${element.status eq 'BLOCKED'}">
                                            <form action="${pageContext.request.contextPath}/controller" method="post">
                                                <input type="hidden" name="command" value="unblock_user">
                                                <input type="hidden" name="user_id" value="${element.userId}">
                                                <button type="submit" class="unblock_btn"><fmt:message
                                                        key="admin.unblock.btn"/></button>
                                            </form>
                                        </c:when>
                                    </c:choose>
                                </c:if>

                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>
    <c:import url="/jsp/fragment/footer.jsp"/>
</div>
</body>
</html>
