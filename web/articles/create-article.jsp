<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Article</title>
</head>
<body>

<main>
    <div class="container body-content span=8 offset=2">

        <%--&lt;%&ndash;TODO: Import the error.jsp Page&ndash;%&gt;--%>
        <jsp:include page="${pageContext.request.contextPath}/error.jsp"/>

        <div class="well">
            <%--TODO: Create the correct type of form --%>
            <form class="form-horizontal" method="post">
                <fieldset>
                    <legend>New Post</legend>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="post-title">Post Title</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" name="post-title" id="post-title"
                                   placeholder="Post Title" required="required"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="post-content">Content</label>
                        <div class="col-sm-4">
                            <textarea class="form-control" rows="4" type="text" name="post-content" id="post-content"
                                      placeholder="Post Title" required="required">
                            </textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="post-category">Category</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="post-category" id="post-category">
                                <c:set var="categories" value="${categories}"/>
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="post-tags">Tags</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" name="post-tags" id="post-tags" placeholder="Tags"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <a class="btn btn-default" href="/" <%--TODO: It should redirect to the home page --%>>Cancel</a>
                            <input type="submit" value="Submit" class="btn btn-primary"/>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</main>

</body>
</html>