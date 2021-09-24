<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="main.applications"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/all_applications.css" />
    </head>
<body>
<c:import url="/jsp/fragment/header.jsp"/>
<div class="tutor_profile">
    <div class="left_part">
        <div class="img">
            <img
                    src="https://get.wallhere.com/photo/bridge-wood-mountain-peak-landscape-677584.jpg"
                    alt=""
            />
        </div>
    </div>
    <div class="right_part">
        <div class="name">Lorem, ipsum dolor.</div>
        <div class="phone"><a href="telto:">+375316181654165</a></div>
        <div class="email"><a href="#">asdhfaksdasdhlf@mail.ru</a></div>
        <div class="city"><span>City:</span> Alllax</div>
        <div class="education_title">Education</div>
        <ul class="education">
            <li class="ed_item">
                Lorem ipsum, dolor sit amet consectetur adipisicing elit. Vero,
                exercitationem?
            </li>
            <li class="ed_item">
                Lorem ipsum, dolor sit amet consectetur adipisicing elit. Vero,
                exercitationem?
            </li>
            <li class="ed_item">
                Lorem ipsum, dolor sit amet consectetur adipisicing elit. Vero,
                exercitationem?
            </li>
        </ul>
        <div class="info_title">Information</div>
        <div class="info">
            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Et sed
            laborum laudantium suscipit obcaecati ipsam temporibus commodi! Velit,
            fugiat illo?
        </div>
        <div class="price_per_hour">price: <span>50 $</span></div>
        <div class="controls">
            <button class="declain">declain</button>
            <button class="submit">submit</button>
        </div>
    </div>
</div>
</body>
</html>