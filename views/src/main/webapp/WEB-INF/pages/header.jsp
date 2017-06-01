<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>

<div class="navbar navbar-static-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse"
                    data-target=".nav-collapse">
                <span class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>

            <a class="brand" href="/app/">Complaint Board</a>
            <%--PRINCIPAL : <sec:authentication property="principal"/>--%>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="divider-vertical"></li>

                    <li><a href="#" data-toggle="modal" data-target="#myModal">Search
                        <i class="icon-search"></i>
                    </a></li>

                    <c:choose>
                        <c:when test="${not empty sessionScope.currentUser}">
                            <c:choose>
                                <%--Organization--%>
                                <c:when test="${sessionScope.currentUser.roleType == 'ORGANIZATION'}">

                                    <li>
                                        <a href="/app/manageOrganization/${user.organization.id}00">Manage Related Organization <i
                                                class="icon-edit"></i></a>
                                    </li>
                                    <%--</c:when> --%>

                                </c:when>
                                <%--Anonymus--%>
                                <c:when test="${sessionScope.currentUser.roleType ==  'ANONYMOUS'}">
                                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                                                            data-toggle="dropdown"><i class="icon-wrench"></i>Menu<span
                                            class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="/app/manageOrganizations">Organizations</a></li>
                                            <li><a href="/app/newFeedback">Create Feedback</a></li>
                                        </ul>
                                    </li>
                                </c:when>
                                <%--Admin--%>
                                <c:when test="${sessionScope.currentUser.roleType ==  'ADMIN'}">
                                    <li>
                                        <a href="/app/manageOrganizations">Manage Organizations <i
                                                class="icon-edit"></i></a>
                                    </li>



                                    <li>
                                        <a href="/app/manageUsers">Manage Users <i
                                                class="icon-edit"></i></a>
                                    </li>


                                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                                                            data-toggle="dropdown"><i class="icon-wrench"></i>Menu<span
                                            class="caret"></span></a>
                                        <ul class="dropdown-menu">
                                            <%--<li><a href="/app/manageOrganizations">Organizations</a></li>--%>
                                            <li><a href="/app/newFeedback">Create Feedback</a></li>
                                            <li><a href="/app/newUserForm">Create New User</a></li>
                                            <li><a href="/app/newOrganizationForm">Create New Organization</a></li>

                                        </ul>
                                    </li>

                                </c:when>

                            </c:choose>

                        </c:when>
                    </c:choose>


                </ul>

                <div class="nav pull-right">
                    <c:choose>

                        <c:when test="${not empty sessionScope.currentUser}">
                            <c:choose>
                                <c:when test="${sessionScope.currentUser.roleType == 'ANONYMOUS'}">
                                    <a class="btn btn-info" href="#" data-toggle="modal"
                                       data-target="#loginModal">Login</a>

                                </c:when>
                            </c:choose>

                            <c:choose>
                                <%--Organization--%>
                                <c:when test="${sessionScope.currentUser.roleType ==  'ORGANIZATION' || sessionScope.currentUser.roleType ==  'ADMIN'}">


                                    <a class="btn btn-link" href="userpage.jsp">
                                        <i class="icon-user"></i> ${sessionScope.currentUser.login} Role
                                        :${sessionScope.currentUser.roleType} </a>

                                    <a class="btn btn-success" href="/app/logout">Logout</a>

                                </c:when>
                            </c:choose>

                        </c:when>
                        <%--when anonymous--%>

                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>


<%--Login Modal form--%>

<div id="loginModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>

                <div class="wrapper">
                    <spring:form id="loginForm" role="form" class="form-inline" name="form-login" method="post"
                                 commandName="loginForm"
                                 action="/app/login" modelAttribute="user">
                        <h2 class="formsigninheading">Please login:</h2>
                        <spring:input type="text" class="form-control" path="login"/>
                        <spring:input type="password" class="form-control" path="password"/>

                        <button class="btn btn-default" type="submit" onClick="checkLoginForm()">Enter</button>
                        <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
                    </spring:form>
                </div>

            </div>
        </div>
    </div>
</div>


<%--Search modal from--%>
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <form role="form" class="form-inline" id="searchform" method="post" action="/app/getUser">
                    <div class="form-group">
                        <label for="word" id="wordL">Search word:</label>
                        <input type="text"
                               class="form-control" id="word" name="searchword">
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" class="searchBox" name="selector" id="selector" value="1">organization</label>
                        <label>
                            <input type="checkbox" class="searchBox" name="selector" id="selector"
                                   value="2">user</label>
                        <label>
                            <input type="checkbox" class="searchBox" name="selector" id="selector"
                                   value="3">rate</label>
                    </div>
                    <button type="button" class="btn btn-default" onClick="checkSearch();">Search</button>

                    <button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>
    </div>
</div>
