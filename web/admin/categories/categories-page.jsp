<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories Admin Page</title>
</head>
<body>
<main>
    <div class="container body-content span=8 offset=2">
        <div class="well">
            <h1>All Categories - <a class="btn btn-warning" href="/admin/categories/add">Create New</a></h1>
            <table class="table">
                <thead>
                <th>#</th>
                <th>Name</th>
                <th>Actions</th>
                </thead>
                <tbody>
                <c:set var="categories" value="${categories}" />
                <c:set var="seq" value="0" />
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <c:set var="seq" value="${seq + 1}" />
                        <td>${seq}</td>
                        <td>${category.name}</td>
                        <td>
                            <a href="/admin/categories/edit/${category.id}">Edit</a>
                            <a href="/admin/categories/delete/${category.id}">Delete</a>
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