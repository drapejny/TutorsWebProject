<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="main.subjects"/></title>
</head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<ul>
    <li><a href="#"><fmt:message key="main.users"/></a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=all_subjects_page"><fmt:message key="main.subjects"/></a></li>
    <li><a href="#"><fmt:message key="main.feedbacks"/></a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=all_applications_page"><fmt:message key="main.applications"/></a></li>
</ul>
<c:forEach var="element" items="${sessionScope.subjects}" varStatus="status">
    ${element.subjectName} <a href="${pageContext.request.contextPath}/controller?command=delete_subject&subject_id=${element.subjectId}"><fmt:message key="subjects.delete"/></a>
    <hr>
</c:forEach>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="add_subject"><br>
    <input type="text" name="subject_name"><input type="submit" value="<fmt:message key="subjects.add"/>"><br>
    ${errorWrongDataMessage}
</form>
</body>
</html>
