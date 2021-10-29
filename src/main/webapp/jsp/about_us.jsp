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
    <title><fmt:message key="main.aboutus"/></title>
</head>
<body>
<div class="wrapper">
    <c:import url="fragment/header.jsp"/>
    <main class="main">
        <div class="about-us-block">
            <div class="left_part">
                <img src="${pageContext.request.contextPath}/img/about-us.jpg" height="200" width="200">
            </div>
            <div class="right_part">
                <h2><fmt:message key="aboutus.name"/></h2>
                <div class="about-us-subtext"><fmt:message key="aboutus.text"/></div>
                <div class="about-us-subtext"><b><fmt:message key="aboutus.contacts"/></b></div>
                <ul>
                    <li><span class="about-us-contact">Telegram: @posle_VUZa_na_zavod</span> </li>
                    <li><span class="about-us-contact">Email: antoshka-51762@yandex.by</span> </li>
                </ul>
            </div>
        </div>
    </main>
    <c:import url="fragment/footer.jsp"/>
</div>
</body>
</html>
