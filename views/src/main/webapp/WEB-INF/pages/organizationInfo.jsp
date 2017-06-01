<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Organization Page</title>
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
        <p class="lead">Organization Information</p>
        <hr>
        <spring:form name="organizationModify" id="organizationModify" class="form-horizontal"
                     action="/app/updateOrganization/${organization.name}" method="put" modelAttribute="organization"
                     commandName="organization">
            <input type=hidden name="projectId" value="">

            <div class="control-group">
                <label class="control-label" for="name" id="nameL">Organization Name</label>
                <div class="controls">

                    <spring:input type="text" name="name" id="name" class="input-small" value="${organization.name}"
                                  path="name"/>
                </div>
            </div>


            <div class="control-group">
                <div class="controls">
                    <button type="button" class="btn btn-primary btn-info" onClick="editOrganization();"
                        ${sessionScope.currentUser.roleType!='ANONYMOUS' ? '' : 'disabled'} > Modify Organization Name
                        <i class="icon-edit icon-white"></i>
                    </button>

                    <button type="button" class="btn btn-primary btn-info"
                            onClick="document.getElementById('deleteOrganization').submit();"
                        ${sessionScope.currentUser.roleType!='ANONYMOUS' ? '' : 'disabled'} > Delete this organization
                        <i class="icon-edit icon-white"></i>
                    </button>


                    <button type="button" class="btn btn-primary btn-info"
                            onClick="document.getElementById('addFeedback').submit();"
                        <%--${sessionScope.currentUser.roleType!='ANONYMOUS' ? '' : 'disabled'} --%>
                    > Add Feedback to
                        Organization <i class="icon-edit icon-white"></i>
                    </button>

                </div>
            </div>
        </spring:form>

        <spring:form name="addFeedback" id="addFeedback" class="form-horizontal"
                     action="${pageContext.request.contextPath}/newFeedback/${organization.name}" method="get">
        </spring:form>

        <spring:form name="deleteOrganization" id="deleteOrganization" class="form-horizontal"
                     action="${pageContext.request.contextPath}/deleteOrganization/${organization.id}" method="delete">
        </spring:form>


        <div class="control-group">
            <label class="control-label">Related Feedbacks:</label>
        </div>
    </div>


    <c:choose>
        <c:when test="${not empty feedbacks}">

            <div class="row-fluid">
                <div class="container">

                    <c:forEach var="feedback" items="${feedbacks}">


                        <pre class="prettyprint">
                <span class="text-info">${feedback.author.login} </span>
                <span class="pull-right text-info">${feedback.date}</span>

               <strong>Organization</strong> : ${feedback.organization.name}
                  <strong>Rate</strong> : ${feedback.rate}
                    <strong>Text</strong> : ${feedback.text}
                                </pre>
                    </c:forEach>
                </div>
            </div>

        </c:when>
        <c:otherwise>
            <div class="row-fluid">
                <div class="container">
                    <div class="span6">
                        <p class="text-center">Feedbacks not Found</p>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>


<div class="row-fluid">
    <c:import url="footer.jsp"/>
</div>
</body>



