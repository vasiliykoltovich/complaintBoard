<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Error</title>
 <link rel="icon" href="${pageContext.request.contextPath}/img/it.ico" type="image/ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/complainBoardVerifier.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css"/>
</head>
<body>

	<div class="container">
       <div class="page-header">
          <p class="lead">Error</p>
       </div>
       <div id="user-message" class="alert alert-error">
           <button type="button" class="close" onClick="hideElement('user-message');"><i class="icon-off"></i></button>
       <strong>Error Message</strong>
       <hr>
       <pre class="prettyprint">${error}</pre>

       <hr>
       </div>
    </div>

<div class="row-fluid">
    <c:import url="footer.jsp"/>
    </div>
</body>
</html>