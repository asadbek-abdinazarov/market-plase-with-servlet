<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <jsp:include page="/fragment/css.jsp"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .product-container {
            max-width: 1000px;
            margin: 50px auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .product-container h2 {
            text-align: center;
            color: #333;
        }
        .product-list {
            display: grid;
            grid-template-columns: 1fr 1fr 1fr;
            gap: 20px;
        }
        .product-card {
            background-color: #f9f9f9;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .product-card h3 {
            font-size: 1.2em;
            color: #333;
        }
        .product-card p {
            font-size: 1em;
            color: #555;
        }
        .product-card .price {
            font-weight: bold;
            font-size: 1.1em;
            color: #2c3e50;
        }
        .product-card .prod-date {
            font-size: 0.9em;
            color: #7f8c8d;
        }
        .product-link {
            text-decoration: none;
        }
    </style>
</head>
<body>

<jsp:include page="/fragment/navbar.jsp"/>

<div class="product-container">
    <h2>Product List</h2>
    <div class="product-list">
        <!-- Loop through the products and display them -->
        <c:forEach var="product" items="${products}">
            <a href="/add-to-backed/${product.getId()}" class="product-link">
                <div class="product-card">
                    <h3>${product.getProdName()}</h3>
                    <p><strong>Type:</strong> ${product.getProdType()}</p>
                    <p><strong>Description:</strong> ${product.getProdDesc()}</p>
                    <p class="price">${product.getProdPrice()} UZS</p>
                </div>
            </a>
        </c:forEach>
    </div>
</div>

<jsp:include page="/fragment/js.jsp"/>

</body>
</html>
