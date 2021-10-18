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
<c:forEach var="element" items="${applicationScope.subjects}" varStatus="status">
    ${element.subjectName} <a href="${pageContext.request.contextPath}/controller?command=delete_subject&subject_id=${element.subjectId}"><fmt:message key="subjects.delete"/></a>
    <hr>
</c:forEach>
<span class="fail-message">${errorWrongDataMessage}</span>
<span class="fail-message">${errorAddSubjectMessage}</span>
<span class="success-message">${successAddSubjectMessage}</span>
<span class="success-message">${successDeleteSubjectMessage}</span>
<span class="fail-message">${errorDeleteSubjectMessage}</span>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="add_subject"><br>
    <input type="text" name="subject_name" required pattern="^[А-я]{1,32}$" maxlength="32">
    <button type="submit" class="simple-btn"><fmt:message key="subjects.add"/></button><br>
</form>
</body>
</html>
