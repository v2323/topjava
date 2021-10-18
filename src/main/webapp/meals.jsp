<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Meals</h2>
    <a href="meals?action=create">Add Meal</a>
    <a href="meals?action=all&userId=1">Зайти как Admin</a>
    <a href="meals?action=all&userId=2">Зайти как User</a>
    <a href="meals?action=all&userId=3">Зайти как User2</a>

    <br><br>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th>UserId</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>${meal.userId}</td>
                <td><a href="meals?action=update&id=${meal.id}&userId=${meal.userId}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}&userId=${meal.userId}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<form method="get" action="meals">
    <dl>
        <dt>Start:</dt>
        <dd><input type="datetime-local" value="${meal.dateTime}" name="startDateTime" required></dd>
    </dl>
    <dl>
        <dt>End:</dt>
        <dd><input type="datetime-local" value="${meal.dateTime}" name="endDateTime" required></dd>
    </dl>
    <a href="meals?action=allBetweenHalfOpen&userId=2">Фильтр</a>
</form>
</body>
</html>