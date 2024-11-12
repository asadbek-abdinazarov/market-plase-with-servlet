<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Basket Page</title>
    <jsp:include page="/fragment/css.jsp"/>
    <style>
        .product-container {
            max-width: 800px;
            margin: 20px auto;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 8px;
        }
        .product-list {
            list-style-type: none;
            padding: 0;
        }
        .product-item {
            display: flex;
            justify-content: space-between;
            padding: 15px;
            border-bottom: 1px solid #eee;
        }
        .product-item:last-child {
            border-bottom: none;
        }
        .product-info {
            flex-grow: 1;
        }
        .product-price {
            font-weight: bold;
            color: #4e54c8;
        }
    </style>
</head>
<body>
<jsp:include page="/fragment/navbar.jsp"/>

<h1>Your Basket</h1>

<div class="product-container">
    <ul class="product-list">
        <c:forEach var="product" items="${products}">
            <li class="product-item">
                <div class="product-info">
                    <h2>${product.getProdName()}</h2>
                    <p>Type: ${product.getProdType()}</p>
                    <p>Description: ${product.getProdDesc()}</p>
                    <p>Unit: ${product.getUnit()}</p>
                    <p>Amount: ${product.getAmount()}</p>
                </div>
                <div class="product-price">
                    ${product.getProdPrice()} UZS
                </div>
            </li>
        </c:forEach>
    </ul>
</div>

<jsp:include page="/fragment/js.jsp"/>
</body>
</html>
