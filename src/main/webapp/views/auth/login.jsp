<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Home Page</title>
    <jsp:include page="/fragment/css.jsp"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 500px;
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
        }

        .btn-success {
            background-color: #4e54c8;
            border: none;
        }

        .btn-warning {
            background-color: #ffc107;
            color: #333;
            border: none;
            margin-top: 10px;
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
<div class="container mt-5">
    <h1>Login</h1>
    <c:if test="${email_message != null}">
        <span class="text-warning">${email_message}</span>
    </c:if>
    <c:if test="${status_error != null}">
        <span class="text-danger">${status_error}</span>
    </c:if>
    <form action="/auth/login" method="post">
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email">
            <c:if test="${email_error != null}">
                <span class="text-danger">${email_error}</span>
            </c:if>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your password">
            <c:if test="${password_error != null}">
                <span class="text-danger">${password_error}</span>
            </c:if>
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
            <label class="form-check-label" for="rememberMe">Remember Me</label>
        </div>
        <button type="submit" class="btn btn-success">Login</button>
        <a href="/auth/register" class="btn btn-warning">Register</a>
    </form>
</div>
<jsp:include page="/fragment/js.jsp"/>
</body>
</html>