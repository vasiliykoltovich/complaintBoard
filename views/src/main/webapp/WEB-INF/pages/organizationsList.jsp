<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Organizations List</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/it.ico" type="image/ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css"/>
</head>
<body>
<c:import url="header.jsp"/>

<div class="row-fluid">
    <div class="container">
        <spring:form name="organizations" id="organizationsTable" class="form-horizontal"
                     action="/app/newFeedback" method="get">
            <input type=hidden name="orgName" value="">
            <table class="table table-bordered">
                <caption class="lead">Organizations</caption>
                <thead>
                <tr>

                    <th>Name</th>
                    <th>Amount of Feedbacks</th>
                    <th>Average Rate Value</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="org" items="${organizations}">
                    <tr>

                        <td><a href="/app/organizationInfo/${org.name}">${org.name}</a></td>
                        <td>${count[org.name]}</td>
                        <td>${averageRates[org.name]}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </spring:form>
    </div>
</div>

<div class="row-fluid">
    <c:import url="footer.jsp"/>
</div>
</body>
</html>