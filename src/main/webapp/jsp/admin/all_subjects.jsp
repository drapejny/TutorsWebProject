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
<div class="wrapper">
    <c:import url="/jsp/fragment/header.jsp"/>
    <main class="main">
        <div class="subjects-block">
            <c:forEach var="element" items="${applicationScope.subjects}" varStatus="status">
                <div class="subjects-block_item">
                    <span class="subject-name">${element.subjectName}</span>
                    <a href="${pageContext.request.contextPath}/controller?command=delete_subject&subject_id=${element.subjectId}">
                        <fmt:message key="subjects.delete"/>
                    </a>
                </div>
            </c:forEach>
            <span class="fail-message">${errorWrongDataMessage}</span>
            <span class="fail-message">${errorAddSubjectMessage}</span>
            <span class="success-message">${successAddSubjectMessage}</span>
            <span class="success-message">${successDeleteSubjectMessage}</span>
            <span class="fail-message">${errorDeleteSubjectMessage}</span>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="add_subject"><br>
                <input type="text" name="subject_name" required pattern="^[а-яА-Я ]{1,64}$" maxlength="64">
                <button type="submit" class="simple-btn"><fmt:message key="subjects.add"/></button>
                <br>
            </form>
        </div>
    </main>
    <c:import url="/jsp/fragment/footer.jsp"/>
</div>
</body>
</html>
