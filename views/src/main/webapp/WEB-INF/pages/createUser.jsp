<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>New User information</title>
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
        <p class="lead">Add New User Information</p>
        <hr>
        <spring:form name="userCreate" id="userCreate" class="form-horizontal"
                     action="${pageContext.request.contextPath}/postUser" method="post" modelAttribute="user"
                     commandName="user">

            <div class="control-group">
                <label class="control-label" for="name" id="loginL">Login</label>
                <div class="controls">
                    <spring:input type="text" name="name" id="login" class="input-small" path="login"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="roleSelector" id="roleL">User Role</label>
                <div class="controls">

                    <spring:select path="roleType" id="roleSelector" onChange="selectOrganization();">
                        <spring:option value=""> --SELECT--</spring:option>
                        <spring:options items="${roles}"/>

                    </spring:select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="orgSelector" id="organizationL">Organization for user</label>
                <div class="controls">

                    <spring:select path="organization.id" id="orgSelector" >
                        <spring:option value=""> --SELECT--</spring:option>
                        <spring:options items="${organizations}" itemLabel="name" itemValue="id"/>

                    </spring:select>
                </div>
            </div>


            <div class="control-group">
                <label class="control-label" for="name" id="passwordL">Password</label>
                <div class="controls">
                    <spring:password path="password" id="password" class="input-small" showPassword="true"/>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <button type="button" class="btn btn-primary btn-info"
                            onClick="document.getElementById('userCreate').submit()"
                        ${sessionScope.currentUser.roleType!='ANONYMOUS' ? '' : 'disabled'}
                    >Add New User <i class="icon-edit icon-white"></i></button>

                    <input type="reset" id="clearButton"
                           class="btn btn-primary btn-info" value="Clear">
                    <i class="icon-edit icon-white"></i>

                </div>
            </div>

        </spring:form>

    </div>
</div>


<div class="row-fluid">
    <c:import url="footer.jsp"/>
</div>
</body>
