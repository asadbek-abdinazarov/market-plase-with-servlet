<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verification</title>
    <jsp:include page="/fragment/css.jsp"/>
</head>
<body>
<jsp:include page="/fragment/navbar.jsp"/>

<h1>Verification Page</h1>

<div style="margin: 50px;">
    <form action="/verifyPassword" method="post">
        <div>
            <label for="verify_password">Verify Email:</label>
            <input type="text" id="verify_password" name="verify_password" minlength="6" required
                   placeholder="At least 6 characters"
                   style="padding: 10px; font-size: 16px; margin: 10px 0; width: 300px;">
        </div>
        <button type="submit" style="padding: 10px 20px; font-size: 16px; cursor: pointer;">Submit</button>
    </form>
</div>

<jsp:include page="/fragment/js.jsp"/>
</body>
</html>
