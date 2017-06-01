<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>ComplaintBoard</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/it.ico" type="image/ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/complainBoardVerifier.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css"/>
</head>
<body>
<c:import url="header.jsp"/>


<ul class="nav nav-tabs" id="product-table">
    <li class="active">
        <a data-toggle="tab" href="#table1">All Feedbacks...</a></li>
    <li>
        <a data-toggle="tab" href="#table2">Last 5 Feedbacks...</a>
    </li>
</ul>

<div class="tab-content">
    <div id="table1" class="tab-pane active">


        <div class="row-fluid">
            <div class="container">

                <c:forEach var="feedback" items="${feedbacks}">

                    <%-- begin="${not empty startIndex ? (startIndex) : 0}"

                          end="${not empty feedbacks ? endIndex : (number-1)}"> --%>
                    <pre class="prettyprint">
    <span class="text-info">${feedback.author.login} </span>
    <span class="pull-right text-info">${feedback.date}</span>

    <strong>Organization</strong> : ${feedback.organization.name}
    <strong>Rate</strong> : ${feedback.rate}
    <strong>Text</strong> : ${feedback.text}
    <strong>Menu</strong> : <button type="button" class="btn btn-default btn-xs"
                                    onclick="document.getElementById('deleteFeedback').submit()" ${sessionScope.currentUser.roleType!='ANONYMOUS' ? '' : 'disabled'}>Delete</button><spring:form
                            id="deleteFeedback" class="form-inline"
                            action="${pageContext.request.contextPath}/deleteFeedback/${feedback.id}" method="delete"/>
    </pre>
                </c:forEach>
            </div>
        </div>

    </div>


    <div id="table2" role="tabpanel" class="tab-pane">


        <div class="row-fluid">
            <div class="container">

                <c:forEach var="feedback5" items="${feedbacksLast5}">


                    <pre class="prettyprint">
    <span class="text-info">${feedback5.author.login} </span>
    <span class="pull-right text-info">${feedback5.date}</span>

    <strong>Organization</strong> : ${feedback5.organization.name}
    <strong>Rate</strong> : ${feedback5.rate}
    <strong>Text</strong> : ${feedback5.text}
    </pre>
                </c:forEach>
            </div>
        </div>


    </div><!-- end class tab pane -->


</div><!-- end class tab pane -->

<div class="row-fluid">
    <c:import url="footer.jsp"/>
</div>
</body>
</html>