<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
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
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
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
<table border="1" cellpadding="8" cellspacing="8">
    <form>
        <thead>
        <tr>
            <th>Start Time</th>
            <th>End Time</th>
        </tr>
        </thead>
        <tr>
            <input type="hidden" name="action" value="allBetweenHalfOpen">
            <td><input type="time" name="startTime"/></td>
            <td><input type="time" name="endTime"/></td>
        </tr>
        <button type="submit">Фильтр по времени</button>
    </form>
</table>
</form>
<table border="1" cellpadding="8" cellspacing="8">
    <form>
        <thead>
        <tr>
            <th>Start Date</th>
            <th>End Date</th>
        </tr>
        </thead>
        <tr>
            <input type="hidden" name="action" value="allBetweenDates">
            <td><input type="date" name="startDate"/></td>
            <td><input type="date" name="endDate"/></td>
        </tr>
        <button type="submit">Фильтр по дате</button>
    </form>
</table>
</form>
</body>
</html>