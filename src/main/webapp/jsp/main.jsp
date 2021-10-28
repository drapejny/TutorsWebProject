<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="main.title"/></title>
</head>
<body>
<div class="wrapper">
    <c:import url="fragment/header.jsp"/>
    <main class="main">
        <div class="main__container">
            <div class="main__left_part">
                <img src="${pageContext.request.contextPath}/img/main.jpg" alt="main" width="600" height="450">
            </div>
            <div class="main__right_part">
                <div style="margin-bottom: 50px">
                    <span class="main-tutorz-by-text"><b><fmt:message key="main.tutorz.by"/></b></span>
                    <span class="main-platform-text"><b><fmt:message key="main.platform.text"/></b></span>
                </div>
                <p class="main-platform-subtext"><fmt:message key="main.subtext1"/></p>
                <p class="main-platform-subtext"><fmt:message key="main.subtext2"/></p>
                <p class="main-platform-subtext"><fmt:message key="main.subtext3"/></p>
            </div>
        </div>
    </main>
    <c:import url="fragment/footer.jsp"/>
</div>
</body>
</html>