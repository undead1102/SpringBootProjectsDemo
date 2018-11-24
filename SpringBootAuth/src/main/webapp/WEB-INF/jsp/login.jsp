<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <title>Login Page</title>
      <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
      <link href="${pageContext.request.contextPath}/assets/css/index.css" rel="stylesheet">
   </head>
   <body>
      <div class="row top-margin">
         <div class="col-xs-offset-1 col-xs-10 col-sm-offset-2 col-sm-8 col-md-offset-3 col-md-6">
            <div class="panel panel-default">
               <div class="panel-body">
                  <form id="login_form" action="${pageContext.request.contextPath}/login" method="post">
                     <div class="form-group">
                        <label for="username">User Name:</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="User Name...">
                     </div>
                     <div class="form-group">
                        <label for="username">Password:</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password...">
                     </div>            
                     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                     <div class="form-group">
                        <button class="btn btn-primary form-control" type="submit">Login</button>
                     </div>            
                  </form>
               </div>
            </div>
         </div>
      </div>
      
      <script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery/js/jquery.min.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
   </body>
</html>