<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style>
        .excess_false {
            color: green;
        }

        .excess_true {
            color: red;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table border=1>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    </thead>
    <jsp:useBean id="meals" scope="request" type="java.util.List"/>
    <c:forEach items="${meals}" var="meal">
        <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr class="${meal.excess ? 'excess_true' : 'excess_false'}">
            <td><c:out value="${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            <td><a href="meals?action=edit&id=<c:out value="${meal.id}"/>">Edit</a></td>
        </tr>
    </c:forEach>
</table>
<a href="meals?action=create">Create Meal</a>

</body>
</html>