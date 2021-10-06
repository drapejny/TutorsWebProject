<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customtags" prefix="ctg" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="prop.pagecontent"/>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $( function() {
        $( "#slider-range" ).slider({
            range: true,
            min: 1,
            max: 999,
            values: [<c:choose><c:when test="${empty sessionScope.searchedMinPrice}">1</c:when><c:otherwise>${sessionScope.searchedMinPrice}</c:otherwise></c:choose>,
                <c:choose><c:when test="${empty sessionScope.searchedMaxPrice}">999</c:when><c:otherwise>${sessionScope.searchedMaxPrice}</c:otherwise></c:choose> ],
            slide: function( event, ui ) {
                $( "#amount1" ).val( ui.values[ 0 ]);
                $("#amount2").val(ui.values[1]);
            }
        });
        $( "#amount1" ).val($( "#slider-range" ).slider( "values", 0 ));
        $( "#amount2" ).val($( "#slider-range" ).slider( "values", 1 ));
    } );
</script>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="fragment/header.jsp"/>
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="search">
    <select name="subject">
        <c:forEach var="element" items="${applicationScope.subjects}">
            <option value="${element.subjectId}" <c:if test="${sessionScope.searchedSubjectId == element.subjectId}">
            selected
        </c:if>>${element.subjectName}</option>
        </c:forEach>
    </select>
    <select name="city">
        <c:forEach var="element" items="${requestScope.cities}">
            <option value="${element}" <c:if test="${sessionScope.searchedCity == element}">
                selected
            </c:if>>${element}</option>
        </c:forEach>
    </select>
        <input type="text" id="amount1" name="min_price" value="${sessionScope.searchedMinPrice}" readonly >
        <input type="text" id="amount2" name="max_price" value="${sessionScope.searchedMaxPrice}" readonly >
    <div id="slider-range"></div>
    <input type="submit" value="Поиск"><br>
</form>
<c:if test="${empty requestScope.tutors}">
    Не найдено
</c:if>

<c:if test="${not empty requestScope.tutors}">
    <a href="${pageContext.request.contextPath}/controller?command=search&sort=price_asc&page_number=1">по возрастанию цены</a>
    <br>
    <a href="${pageContext.request.contextPath}/controller?command=search&sort=price_desc&page_number=1">по убыванию цены</a>
    <c:forEach var="element" items="${requestScope.tutors}">
        <ctg:user-photo photo="${element.photo}" height="100" width="100"/>
        <a href="${pageContext.request.contextPath}/controller?command=tutor_profile_page&tutor_id=${element.tutorId}">${element.firstName} ${element.lastName}</a>
        <hr>
    </c:forEach>
    <c:if test="${searchedPageNumber != 1}">
        <a href="${pageContext.request.contextPath}/controller?command=search&page_number=${searchedPageNumber - 1}&sort=${sort}">предыдущая</a>
    </c:if>
    ${searchedPageNumber}из${searchedPagesCount}
    <c:if test="${searchedPageNumber != searchedPagesCount}">
        <a href="${pageContext.request.contextPath}/controller?command=search&page_number=${searchedPageNumber + 1}&sort=${sort}">следующая</a>
    </c:if>
</c:if>
<br>


</body>
</html>
