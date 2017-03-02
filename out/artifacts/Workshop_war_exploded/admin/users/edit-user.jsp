<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Category</title>
</head>
<body>
<main>
    <div class="container body-content span=8 offset=2">

        <%--TODO: Import the error.jsp Page--%>
        <jsp:include page="${pageContext.request.contextPath}/error.jsp"/>

        <div class="well">
            <form class="form-horizontal" method="post">
                <fieldset>
                    <legend>Edit User - ${user.fullName}</legend>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-email">Email</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="email" name="user-email" id="user-email"
                                   value="${user.email}" readonly />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-fullname">Full Name</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" name="user-fullname" id="user-fullname"
                                   value="${user.fullName}" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-password-first">Password</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="password" name="user-password-first"
                                   id="user-password-first" placeholder="Password" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-password-second">Confirm Password</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="password" name="user-password-second"
                                   id="user-password-second" placeholder="Password" required="required"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-4 control-label">Authorities</label>
                        <div class="col-sm-4">
                            <div class="checkbox">
                                <label><input  type="checkbox" name="admin" value="ADMIN" ${adminCheck}/> Admin</label>
                            </div>
                            <div class="checkbox">
                                <label><input  type="checkbox" name="user" value="USER" ${userCheck}/> User</label>
                            </div>
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