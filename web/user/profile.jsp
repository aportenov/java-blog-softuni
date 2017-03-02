<%--TODO: Fill the correct input types and names --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container body-content span=8 offset=2">
        <%--TODO: Import the error.jsp Page--%>
        <jsp:include page="${pageContext.request.contextPath}/error.jsp"/>

        <div class="well">

            <form class="form-horizontal" method="post">
                <fieldset>
                    <legend>Profile</legend>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-email">Email</label>
                        <div class="col-sm-4">
                            <input class="form-control" id="user-email" name="user-email" placeholder="${userEmail}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-fullname">Full Name</label>
                        <div class="col-sm-4 ">
                            <input class="form-control" id="user-fullname" name="user-fullname"
                                   placeholder="${username}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-password-first">Password</label>
                        <div class="col-sm-4">
                            <input class="form-control" id="user-password-first" name="user-password-first" placeholder="******"
                                   required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-password-second">Confirm Password</label>
                        <div class="col-sm-4">
                            <input class="form-control" id="user-password-second" name="user-password-second" placeholder="******"
                                   required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <input class="btn btn-success" a href="/profile" type="submit" value="Update" />
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</main>
