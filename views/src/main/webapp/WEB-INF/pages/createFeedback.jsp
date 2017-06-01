<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Add Feedback</title>
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
        <p class="lead">New Feedback</p>
    </div>

    <div class="row">

        <spring:form id="addFeedback" class="form-vertical"
                     action="${pageContext.request.contextPath}/postNewFeedback" method="post" commandName="feedback"
                     modelAttribute="feedback" enctype="multipart/form-data">

            <div class="span5">
                <fieldset>

                    <div class="control-group">
                        <label id="textL" class="control-label" for="text">Add
                            Feedback Message</label>

                        <div class="controls">
                            <spring:textarea class="input-xlarge span4" id="text"
                                             rows="11 "
                                             placeholder="Place your feedback text here.. "
                                             path="text"></spring:textarea>

                        </div>
                    </div>


                    <div class="control-group">
                        <div class="controls">

                            <button type="button" id="submitButton"
                                    class="btn btn-large btn-info" onClick="checkNewFeedBack();">
                                Add New Feedback <i class="icon-ok icon-white"></i>
                            </button>

                            <input type="reset" id="clearButton"
                                   class="btn btn-large btn-info" value="Cancel">
                            <i class="icon-ok icon-white"></i>
                        </div>
                    </div>

                </fieldset>
            </div>
            <div class="span3">
                <fieldset>

                    <div class="control-group">


                        <label id="organizationL" class="control-label" for="selectedOrganization">Select
                            organization Name</label>

                        <div class="controls">
                            <spring:label id="organizationLS" class="control-label"
                                          path="organization">Organizations</spring:label>
                            <spring:select path="organization.id">
                                <c:choose>
                                    <c:when test="${not empty organizations}">
                                        <spring:option value=""> --SELECT--</spring:option>

                                        <spring:options items="${organizations}" itemValue="id" itemLabel="name"/>
                                    </c:when>

                                    <c:when test="${not empty organization}">
                                        <spring:option
                                                value="${organization.id}"> ${organization.name}</spring:option>

                                    </c:when>
                                </c:choose>
                            </spring:select>


                        </div>
                    </div>


                    <div class="control-group">
                        <label id="rateL" class="control-label" for="selectedRate">Select
                            Rate</label>
                        <div class="controls">
                            <spring:label id="rateLS" class="control-label" path="rate">Rates</spring:label>
                            <spring:select path="rate">
                                <spring:option value=""> --SELECT--</spring:option>
                                <spring:options items="${rates}"/>
                            </spring:select>
                        </div>


                    </div>
                    <div class="control-group">
                        <spring:hidden path="author.id" value="${sessionScope.currentUser.id}"></spring:hidden>
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