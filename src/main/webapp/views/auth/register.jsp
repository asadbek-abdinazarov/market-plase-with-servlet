<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
  <jsp:include page="/fragment/css.jsp"/>
</head>
<body>
<jsp:include page="/fragment/navbar.jsp"/>

<h1>User register page</h1>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-3">
            <h1>Register</h1>
            <form action="/auth/register" method="post">
                <div class="mb-3">
                    <label for="f_name" class="form-label">First name</label>
                    <input type="text" class="form-control" id="f_name" name="firstname">
                </div>
                <div class="mb-3">
                    <label for="l_name" class="form-label">Last name</label>
                    <input type="text" class="form-control" id="l_name" name="lastname">
                </div>
                <div class="mb-3">
                    <label for="p_email" class="form-label">Email address</label>
                    <input type="email" class="form-control" id="p_email" name="email">
                </div>
                <div class="mb-3">
                    <label for="p_password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="p_password" name="password">
                </div>
                <div class="mb-3">
                    <label for="p_2_password" class="form-label">Confirm password</label>
                    <input type="password" class="form-control" id="p_2_password" name="confirm_password">
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
                <a href="/auth/login" class="btn btn-warning">Login</a>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/fragment/js.jsp"/>
</body>
</html>
