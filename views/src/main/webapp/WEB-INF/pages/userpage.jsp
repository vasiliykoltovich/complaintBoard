<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User information</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/it.ico" type="image/ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/complainBoardVerifier.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css"/>
</head>
<body>
<c:import url="header.jsp"/>


<div class="row-fluid">
    <div class="container">
        <div id="user-message" class="alert alert-error" ${not empty message ? '' : 'style="display:none"'}>
            <button type="button" class="close" onClick="hideElement('user-message');"><i class="icon-off"></i></button>
            <strong>Fields are not filled</strong>
        </div>
    </div>
</div>

<div class="row-fluid" style="height:400px">
    <div class="container">
        <p class="lead">User Information</p>
        <hr>
        <spring:form name="userModify" id="userModify" class="form-horizontal"
                     action="${pageContext.request.contextPath}/updateUser/${user.login}" method="put"
                     modelAttribute="user" commandName="user">

            <div class="control-group">
                <label class="control-label" for="name" id="nameL">User Login</label>
                <div class="controls">

                    <spring:input type="text" name="name" id="name" class="input-small" value="${user.login}"
                                  path="login"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="role" id="roleL">User Role</label>
                <div class="controls">


                    <spring:select path="roleType">
                        <spring:option value="${user.roleType}"> --SELECT--</spring:option>
                        <spring:options items="${roles}"/>

                    </spring:select>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="name" id="nameL">User Password</label>
                <div class="controls">

                    <spring:password path="password" value="${user.password}" id="name" class="input-small"
                                     showPassword="true"/>
                </div>

            </div>


            <div class="control-group">
                <div class="controls">
                    <button type="button" class="btn btn-primary btn-info"
                            onClick="document.getElementById('userModify').submit()"
                        ${sessionScope.currentUser.roleType!='ANONYMOUS' ? '' : 'disabled'}
                    >Modify User Data <i class="icon-edit icon-white"></i></button>


                    <button type="button" class="btn btn-primary btn-info"
                            onClick="document.getElementById('userDelete').submit();"
                        ${sessionScope.currentUser.roleType!='ANONYMOUS' ? '' : 'disabled'}
                    >Delete User <i class="icon-edit icon-white"></i>
                    </button>


                </div>


            </div>


        </spring:form>
        <spring:form name="userDelete" id="userDelete" class="form-horizontal"
                     action="${pageContext.request.contextPath}/deleteUser/${user.id}" method="delete">
        </spring:form>

    </div>
</div>


<div class="row-fluid">
    <c:import url="footer.jsp"/>
</div>
</body>
