<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category</title>
</head>
<body>
<main>
    <div class="container body-content span=8 offset=2">

        <%--&lt;%&ndash;TODO: Import the error.jsp Page&ndash;%&gt;--%>
        <%--<jsp:include page="${pageContext.request.contextPath}/error.jsp" />--%>

        <div class="well">
            <form class="form-horizontal" method="post" >
                <fieldset>
                    <legend>Edit Category</legend>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="name">Category Name</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" name="name" id="name" placeholder="Category Name"
                                   value="${category.name}" required="required"/>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <a class="btn btn-default" href="/admin/categories" <%--TODO: It should redirect to the home page --%>>Cancel</a>
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