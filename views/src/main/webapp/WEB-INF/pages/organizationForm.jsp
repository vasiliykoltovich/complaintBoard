<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Add Organization</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/it.ico" type="image/ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/complainBoardVerifier.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css"/>
</head>
<body>
<c:import url="header.jsp"/>

<div class="container">
    <div id="user-message" class="alert alert-error" style="display: none">
        <button type="button" class="close"
                onClick="hideElement('user-message');">
            <i class="icon-off"></i>
        </button>
        <strong>Fields are not filled</strong>
    </div>

    <div class="page-header">
        <p class="lead">New Organization</p>
    </div>

    <div class="row">

        <spring:form id="addOrganization" class="form-vertical"
                     action="${pageContext.request.contextPath}/addOrganization" method="post"
                     commandName="organization" modelAttribute="organization" enctype="multipart/form-data">

            <div class="span5">
                <fieldset>

                    <div class="control-group">
                        <div class="control-group">
                            <label class="control-label" for="name" id="nameL">Organization Name</label>
                            <div class="controls">
                                <spring:input type="text" name="name" id="name" class="input-small" path="name"/>
                            </div>
                        </div>


                        <div class="control-group">
                            <div class="controls">

                                <button type="button" id="submitButton"
                                        class="btn btn-large btn-info"
                                        onClick="document.getElementById('addOrganization').submit();">
                                    Add New Organization <i class="icon-ok icon-white"></i>
                                </button>

                                <input type="reset" id="clearButton"
                                       class="btn btn-large btn-info" value="Cancel">
                                <i class="icon-ok icon-white"></i>
                            </div>
                        </div>

                </fieldset>
            </div>
        </spring:form>
    </div>
</div>


<div class="row-fluid">
    <c:import url="footer.jsp"/>
</div>
</body>
</html>


