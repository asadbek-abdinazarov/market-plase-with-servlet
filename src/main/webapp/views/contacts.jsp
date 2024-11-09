<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
  <title>Contact Information</title>
  <jsp:include page="/fragment/css.jsp"/>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }
    .contact-container {
      max-width: 800px;
      margin: 50px auto;
      padding: 20px;
      background-color: white;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }
    .contact-container h2 {
      text-align: center;
      color: #333;
    }
    .contact-info {
      display: flex;
      flex-direction: column;
      gap: 15px;
      font-size: 1.1em;
    }
    .contact-info div {
      background-color: #f9f9f9;
      padding: 12px;
      border-radius: 5px;
      display: flex;
      justify-content: space-between;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
    }
    .contact-info div span {
      font-weight: bold;
    }
  </style>
</head>
<body>

<jsp:include page="/fragment/navbar.jsp"/>

<div class="contact-container">
  <h2>Contact Information</h2>
  <div class="contact-info">
    <div><span>Email:</span> <a href="mailto:JavachiBiz@gmail.com">JavachiBiz@gmail.com</a></div>
    <div><span>Phone:</span> <a href="tel:+998981234567">+998981234567</a></div>
    <div><span>Location:</span> Tashkent, Mirzo Ulug'bek, Uzbekistan</div>
    <div><span>Zip Code:</span> 100069</div>
  </div>
</div>

<jsp:include page="/fragment/js.jsp"/>

</body>
</html>