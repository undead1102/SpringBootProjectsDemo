<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <title>Authentication Failure</title>
      <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/assets/css/index.css" rel="stylesheet">
   </head>
   <body>
      <div class="row top-margin">
         <div class="col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-md-offset-3 col-md-6">
            <div class="panel panel-default">
               <div class="panel-body">
                  <h3>Access Denied</h3>
                  <p>You are not authorized to access this page.</p>
               </div>
            </div>
         </div>
      </div>
      
      <script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery/js/jquery.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
   </body>
</html>