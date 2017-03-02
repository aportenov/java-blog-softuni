<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article</title>
</head>
<body>
<main>
    <div class="container body-content">
        <div class="row">
            <div class="col-md-12">
                <c:set var="article" value="${article}"/>
                <article>
                    <header>
                        <h2>${article.title}</h2>
                    </header>

                    <p>${article.content}</p>

                    <small class="author">${article.user.fullName}</small>


                    <footer>
                        <div class="pull-right">
                            <c:choose>
                                <c:when test="${article.user.id  == userId}">
                                    <a class="btn btn-success btn-xs" href="/article/edit/${article.id}">Edit</a>
                                    <a class="btn btn-danger btn-xs" href="/article/delete/${article.id}">Delete</a>
                                </c:when>
                            </c:choose>
                            <a class="btn btn-default btn-xs" href="/">back &raquo;</a>
                        </div>
                    </footer>

                </article>
            </div>
        </div>
    </div>
</main>
</body>
</html>