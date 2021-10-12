<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <c:if test="${meal.id == null}">
        <p>
        <h2>Create</h2></p>
    </c:if>
    <c:if test="${meal.id != null}">
        <p>
        <h2>Update</h2></p>
    </c:if>
    <h2></h2>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        <dl>
            <dt>DateTime</dt>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime" required></dd>
        </dl>
        <dl>
            <dt>Description</dt>
            <dd><input type="text" value="${meal.description}" name="description" required></dd>
        </dl>
        <dl>
            <dt>Calories</dt>
            <dd><input type="number" value="${meal.calories}" name="calories" required></dd>
        </dl>
        <button type="submit">Save</button>
    </form>
</section>
</body>
</html>