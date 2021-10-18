<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>User</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create user' : 'Edit user'}</h2>
    <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User" scope="request"/>
    <form method="post" action="users">
        <input type="hidden" name="id" value="${user.id}">
        <dl>
        <dt>UserName:</dt>
        <dd><input type="text" value="${user.name}" size=40 name="description" required></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="text" value="${user.email}" size=40 name="description" required></dd>
        </dl>
        <dl>
            <dt>Password:</dt>
            <dd><input type="text" value="${user.password}" size=40 name="description" required></dd>
        </dl>
        </dl>
<%--        <dl>--%>
<%--            <dt>Calories:</dt>--%>
<%--            <dd><input type="number" value="${user.caloriesPerDay}" name="calories" required></dd>--%>
<%--        </dl>--%>
        <dl>
            <dt>Roles:</dt>
            <dd><input type="text" value="${user.roles}" size=40 name="description" required></dd>
        </dl>
        </dl>
<%--        <dl>--%>
<%--            <dt>Date:</dt>--%>
<%--            <dd><input type="date" value="${user.registered}" name="date" required></dd>--%>
<%--        </dl>--%>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
