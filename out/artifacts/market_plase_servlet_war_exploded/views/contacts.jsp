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
      padding: 30px;
      background-color: white;
      border-radius: 10px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
      transition: transform 0.3s ease;
    }
    .contact-container:hover {
      transform: scale(1.02);
    }
    .contact-container h2 {
      text-align: center;
      color: #4e54c8;
      font-weight: bold;
      margin-bottom: 30px;
      font-size: 1.8em;
    }
    .contact-info {
      display: flex;
      flex-direction: column;
      gap: 15px;
      font-size: 1.1em;
    }
    .contact-info div {
      background-color: #f9f9f9;
      padding: 15px;
      border-radius: 8px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
      transition: box-shadow 0.3s ease, transform 0.3s ease;
    }
    .contact-info div:hover {
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
      transform: translateY(-3px);
    }
    .contact-info div span {
      font-weight: bold;
      color: #333;
    }
    .contact-info a {
      color: #4e54c8;
      text-decoration: none;
      transition: color 0.3s ease;
    }
    .contact-info a:hover {
      color: #333;
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