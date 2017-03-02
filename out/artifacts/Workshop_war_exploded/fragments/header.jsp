<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a href="/" <%--TODO: Your route here --%> class="navbar-brand">SOFTUNI BLOG</a>

                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                    <c:when test="${userId != null}">
                    <li>
                        <a href="/articles/create">Create Article</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <p class="text-left"><strong>${username}&nbsp;&nbsp;&#x26DB;</strong></p>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="navbar-login">
                                    <div class="row">
                                        <div class="col-lg-12"></div>
                                        <div class="col-lg-12">
                                            <p class="text-left html-editor-bold"><a href="/profile"><b>Profile&nbsp;&nbsp;&nbsp;&nbsp;</b>${userRole}
                                            </a></p>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <c:choose>
                                <c:when test="${admin == true}">
                                    <li>
                                        <div class="navbar-login">
                                            <div class="row">
                                                <div class="col-lg-12"></div>
                                                <div class="col-lg-12">
                                                    <a href="/admin/categories">Categories</a>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <div class="navbar-login">
                                            <div class="row">
                                                <div class="col-lg-12"></div>
                                                <div class="col-lg-12">
                                                    <a href="/admin/users">Users</a>
                                                </div>
                                            </div>
                                        </div>
                                    </li>
                                </c:when>
                            </c:choose>
                            <li class="divider"></li>
                            <li>
                                <div class="navbar-login navbar-login-session">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <p>
                                                <a href="/logout" class="btn btn-danger btn-block">Sign Out</a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
                </c:when>
                <c:otherwise>
                    <li>
                        <a href="/register">
                            REGISTER
                        </a>
                    </li>

                    <li>
                        <a href="/login">LOGIN</a>
                        LOGIN
                        </a>
                    </li>
                </c:otherwise>
                </c:choose>
                </ul>

            </div>
        </div>
    </div>
</header>