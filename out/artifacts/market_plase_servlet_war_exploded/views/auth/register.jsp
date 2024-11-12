<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <jsp:include page="/fragment/css.jsp"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin-top: 80px;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
        }
        h1 {
            color: #4e54c8;
            font-weight: bold;
            text-align: center;
            margin-bottom: 30px;
        }
        .form-label {
            color: #333;
            font-weight: bold;
        }
        .form-control {
            box-shadow: none;
            border-radius: 5px;
        }
        .btn {
            width: 100%;
            padding: 10px;
            font-weight: bold;
            margin-top: 10px;
        }
        .btn-primary {
            background-color: #4e54c8;
            border: none;
        }
        .btn-warning {
            background-color: #ffc107;
            color: #333;
            border: none;
        }
        .btn:hover {
            opacity: 0.9;
        }
        .text-danger {
            font-size: 0.9em;
        }
    </style>
</head>
<body>
<jsp:include page="/fragment/navbar.jsp"/>

<div class="container">
    <h1>Register</h1>
    <form action="/auth/register" method="post">
        <div class="mb-3">
            <label for="f_name" class="form-label">First name</label>
            <input type="text" class="form-control" id="f_name" name="firstname" placeholder="Enter your first name">
        </div>
        <div class="mb-3">
            <label for="l_name" class="form-label">Last name</label>
            <input type="text" class="form-control" id="l_name" name="lastname" placeholder="Enter your last name">
        </div>
        <div class="mb-3">
            <label for="p_email" class="form-label">Email address</label>
            <input type="email" class="form-control" id="p_email" name="email" placeholder="Enter your email">
            <c:if test="${email_error != null}">
                <span class="text-danger">${email_error}</span>
            </c:if>
        </div>
        <div class="mb-3">
            <label for="p_password" class="form-label">Password</label>
            <input type="password" class="form-control" id="p_password" name="password" placeholder="Create a password">
            <c:if test="${password_error != null}">
                <span class="text-danger">${password_error}</span>
            </c:if>
        </div>
        <div class="mb-3">
            <label for="p_2_password" class="form-label">Confirm password</label>
            <input type="password" class="form-control" id="p_2_password" name="confirm_password" placeholder="Confirm your password">
            <c:if test="${confirm_password_error != null}">
                <span class="text-danger">${confirm_password_error}</span>
            </c:if>
        </div>
        <button type="submit" class="btn btn-primary">Register</button>
        <a href="/auth/login" class="btn btn-warning">Login</a>
    </form>
</div>

<jsp:include page="/fragment/js.jsp"/>
</body>
</html>