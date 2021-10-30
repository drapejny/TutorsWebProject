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
                    <input type="hidden" name="page_number" value="1">
                    <input type="text" class="search-line" name="search_line" maxlength="100">
                    <button type="submit" class="simple-btn"><fmt:message key="search.button"/></button>
                </form>
            </div>
            <div style="text-align: center">
                <span class="success-message">${successBlockUserMessage}</span>
                <span class="success-message">${successUnblockUserMessage}</span>
            </div>
            <c:choose>
                <c:when test="${empty users && users != null}">
                    <div class="no-apps">
                        <span><fmt:message key="search.notfound"/></span>
                    </div>
                </c:when>
                <c:when test="${not empty users}">
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
                                                    href="${pageContext.request.contextPath}/controller?command=tutor_profile_page&tutor_id=${tutorsMap[element].tutorId}">${element.firstName} ${element.lastName} </a>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="table_item"><ctg:user-photo photo="${element.photo}" height="50"
                                                                                    width="50"/></div>
                                            <div class="table_item">${element.firstName} ${element.lastName}</div>
                                        </c:otherwise>
                                    </c:choose>

                                    <div class="table_item">${element.email}</div>
                                    <div class="table_item">${element.role}</div>
                                    <div class="table_item">${element.status}</div>
                                    <div class="table_item">
                                        <c:if test="${element.role ne 'ADMIN'}">
                                            <c:choose>
                                                <c:when test="${element.status eq 'ACTIVATED'}">
                                                    <form action="${pageContext.request.contextPath}/controller"
                                                          method="post">
                                                        <input type="hidden" name="command" value="block_user">
                                                        <input type="hidden" name="user_id" value="${element.userId}">
                                                        <button type="submit" class="block_btn"><fmt:message
                                                                key="admin.block.btn"/></button>
                                                    </form>
                                                    <c:if test="${element.role eq 'USER' && sessionScope.user.email eq 'admin@admin.com'}">
                                                        <form action="${pageContext.request.contextPath}/controller"
                                                              method="post">
                                                            <input type="hidden" name="command" value="add_admin">
                                                            <input type="hidden" name="user_id"
                                                                   value="${element.userId}">
                                                            <button type="submit" class="unblock_btn"><fmt:message
                                                                    key="admin.add-admin"/></button>
                                                        </form>
                                                    </c:if>
                                                </c:when>
                                                <c:when test="${element.status eq 'BLOCKED'}">
                                                    <form action="${pageContext.request.contextPath}/controller"
                                                          method="post">
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
                            <div class="pages-block">
                                <c:if test="${pageNumber != 1}">
                                    <a href="${pageContext.request.contextPath}/controller?command=search_users&page_number=${pageNumber - 1}&search_line=${requestScope.searchLine}"><fmt:message key="search.prev"/></a>
                                </c:if>
                                    ${pageNumber} <fmt:message key="search.of"/> ${pageCount}
                                <c:if test="${pageNumber != pageCount}">
                                    <a href="${pageContext.request.contextPath}/controller?command=search_users&page_number=${pageNumber + 1}&search_line=${requestScope.searchLine}"><fmt:message key="search.next"/></a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </main>
    <c:import url="/jsp/fragment/footer.jsp"/>
</div>
</body>
</html>
