<%--TODO: Fill the correct input types and names --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<main>
    <div class="container body-content span=8 offset=2">
        <%--TODO: Import the error.jsp Page--%>
        <jsp:include page="${pageContext.request.contextPath}/error.jsp"/>

        <div class="well">

            <form class="form-horizontal"  <%--TODO: Create the correct type of form --%> method="post" >
                <fieldset>
                    <legend>Log In</legend>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-email">Email</label>
                        <div class="col-sm-4">
                            <input class="form-control" id="user-email" name="user-email" placeholder="Email" required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="user-password">Password</label>
                        <div class="col-sm-4">
                            <input class="form-control" id="user-password" name="user-password" placeholder="Password"
                                   required="required"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-4">
                            <a class="btn btn-default"   <%--TODO: It should redirect to the home page --%>  href="/" >Cancel</a>
                            <input  class="btn btn-success" type="submit" value="Log In"/>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</main>
