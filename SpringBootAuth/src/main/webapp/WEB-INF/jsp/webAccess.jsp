<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
   <head>
      <sec:authorize access="hasRole('ROLE_ADMIN')">
      <title>Admin Logged in - Home</title>
      </sec:authorize>
      <sec:authorize access="hasRole('ROLE_STAFF')">
      <title>Staff Logged in - Home</title>
      </sec:authorize>
      <sec:authorize access="hasRole('ROLE_USER')">
      <title>Normal User Logged in - Home</title>
      </sec:authorize>

      <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/assets/css/index.css" rel="stylesheet">
   </head>
   <body>
      <div class="row top-margin">
         <div class="col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-md-offset-3 col-md-6">
            <div class="panel panel-default">
               <div class="panel-body">
                  <ol class="breadcrumb">
                     <sec:authorize access="hasRole('ROLE_ADMIN')">
                     <li><a href="${pageContext.request.contextPath}/secure/adminPage">Admin Access</a></li>
                     </sec:authorize>
                     <sec:authorize access="hasRole('ROLE_STAFF')">
                     <li><a href="${pageContext.request.contextPath}/secure/staffPage">Staff Access</a></li>
                     </sec:authorize>
                     <sec:authorize access="hasRole('ROLE_USER')">
                     <li><a href="${pageContext.request.contextPath}/secure/userPage">User Access</a></li>
                     </sec:authorize>
                     <li><a href="${pageContext.request.contextPath}/logout"><i class="glyphicon glyphicon-log-out"></i>&nbsp;Log Out</a></li>
                  </ol>
                  <h3>You are seeing this ${pageInfo} because...</h3>
                  <p>You are logged in as ${userInfo}</p>
               </div>
            </div>
         </div>
      </div>
      
      <script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery/js/jquery.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
   </body>
</html>