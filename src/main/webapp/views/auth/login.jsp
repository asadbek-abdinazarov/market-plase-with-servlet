<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Home Page</title>
    <jsp:include page="/fragment/css.jsp"/>
</head>
<body>
<jsp:include page="/fragment/navbar.jsp"/>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6">
            <h1>Login</h1>
            <form action="/auth/login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email">
                    <span class="text-danger">
                        <c:if test="${email_error != null}">
                            <span class="text-danger">${email_error}</span>
                        </c:if>
                    </span>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password">
                    <c:if test="${password_error != null}">
                        <span class="text-danger">${password_error}</span>
                    </c:if>
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="rememberMe">
                    <label class="form-check-label" for="rememberMe">Remember Me</label>
                </div>
                <button type="submit" class="btn btn-success">Login</button>
                <a href="/auth/register" class="btn btn-warning">Register</a>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/fragment/js.jsp"/>
</body>
</html>
