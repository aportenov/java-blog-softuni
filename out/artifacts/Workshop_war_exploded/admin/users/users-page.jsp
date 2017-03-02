<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<main>
    <div class="container body-content span=8 offset=2">
        <div class="well">
            <h1>All Users</h1>
            <table class="table">
                <thead>
                <th>#</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Actions</th>
                </thead>
                <tbody>
                <c:set var="users" value="${users}" />
                <c:set var="seq" value="0" />
                <c:forEach items="${users}" var="user">
                    <tr>
                        <c:set var="seq" value="${seq + 1}" />
                        <td>${seq}</td>
                        <td>${user.fullName}</td>
                        <td>${user.email}</td>
                        <td>
                            <a href="/admin/users/edit/${user.id}">Edit</a>
                            <a href="/admin/users/delete/${user.id}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</main>
</body>
</html>