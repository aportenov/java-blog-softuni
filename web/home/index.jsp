<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container body-content">
        <jsp:include page="${pageContext.request.contextPath}/error.jsp"/>
        <div class="row">
            <c:set var="articles" value="${articles}"/>
            <c:forEach items="${articles}" var="article">
                <div class="col-md-6">
                    <article>
                        <header>
                            <h2>${article.title}</h2>
                        </header>
                        <p>${article.getShortContent()}</p>
                        <small class="author">${article.user.fullName}</small>
                        <p>
                            <c:forEach items="${article.tags}" var="tag">
                                <a class="btn btn-default btn-xs" href="/tag/${tag.name}">${tag.name}</a>
                            </c:forEach>
                        </p>


                        <footer>
                            <div class="pull-right">
                                <a class="btn btn-default btn-xs"
                                   href="/article/${article.id}">Read more &raquo;</a>
                            </div>
                        </footer>
                    </article>
                </div>
            </c:forEach>
        </div>
    </div>
</main>