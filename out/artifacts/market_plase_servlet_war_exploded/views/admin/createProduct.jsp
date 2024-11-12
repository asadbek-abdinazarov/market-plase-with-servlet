<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Product</title>
    <jsp:include page="/fragment/css.jsp"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background: linear-gradient(to right, #ffffff, #444ad3);
            color: #333;
        }

        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.2);
            animation: fadeIn 1s ease;
        }

        h1 {
            font-size: 2.5rem;
            font-weight: bold;
            color: #4e54c8;
            text-align: center;
            margin-bottom: 20px;
            animation: slideIn 1s ease;
        }

        label {
            font-weight: bold;
            color: #4e54c8;
        }

        .form-control {
            border-radius: 10px;
            border: 2px solid #ddd;
            padding: 10px;
            transition: all 0.3s ease;
            box-shadow: inset 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-control:focus {
            border-color: #8f94fb;
            box-shadow: 0 0 10px rgba(79, 92, 203, 0.5);
            outline: none;
        }

        select {
            width: 100%;
            padding: 10px;
            border-radius: 10px;
            border: 2px solid #ddd;
            transition: all 0.3s ease;
            background-color: #f8f9fa;
            box-shadow: inset 0px 4px 8px rgba(0, 0, 0, 0.1);
        }

        select:focus {
            border-color: #8f94fb;
            box-shadow: 0 0 10px rgba(79, 92, 203, 0.5);
            outline: none;
        }

        .btn {
            border: none;
            border-radius: 10px;
            padding: 12px 25px;
            font-size: 1rem;
            font-weight: bold;
            text-transform: uppercase;
            cursor: pointer;
            transition: all 0.3s ease;
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.2);
        }

        .btn-success {
            background-color: #4CAF50;
            color: white;
        }

        .btn-success:hover {
            background-color: #45a049;
            transform: scale(1.05);
            box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.2);
        }

        .btn-warning {
            background-color: #ff9800;
            color: white;
        }

        .btn-warning:hover {
            background-color: #e68a00;
            transform: scale(1.05);
            box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.2);
        }

        /* Animations */
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: scale(0.95);
            }
            to {
                opacity: 1;
                transform: scale(1);
            }
        }

        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

    </style>
</head>
<body>
<jsp:include page="/fragment/navbar.jsp"/>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 mx-auto">
            <h1>Create Product</h1>
            <form action="/admin/create-product" method="post">
                <div class="mb-3">
                    <label for="prodName" class="form-label">Product name</label>
                    <input type="text" class="form-control" id="prodName" name="prodName">
                </div>
                <div class="mb-3">
                    <label for="prodDesc" class="form-label">Product description</label>
                    <input type="text" class="form-control" id="prodDesc" name="prodDesc">
                </div>
                <div class="mb-3">
                    <label for="prodPrice" class="form-label">Product price in UZS</label>
                    <input type="number" class="form-control" id="prodPrice" name="prodPrice">
                </div>
                <div class="mb-3">
                    <label for="prodAmount" class="form-label">Product amount</label>
                    <input type="number" class="form-control" id="prodAmount" name="prodAmount">
                </div>
                <br>
                <label for="consumer-products">Consumer Products:</label>
                <select id="consumer-products" name="consumer-products">
                    <option value="vegetables">Vegetables Products</option>
                    <option value="fruits">Fruits Products</option>
                    <option value="specialty">Specialty Products</option>
                    <option value="shopping">Shopping Products</option>
                    <option value="unsought">Unsought Products</option>
                    <option value="capital-goods">Capital Goods</option>
                    <option value="raw-materials">Raw Materials</option>
                    <option value="component-parts">Component Parts</option>
                    <option value="major-equipment">Major Equipment</option>
                    <option value="accessory-equipment">Accessory Equipment</option>
                    <option value="operating-supplies">Operating Supplies</option>
                </select>
                <br><br>
                <label for="product-unit">Product unit:</label>
                <select id="product-unit" name="product-unit">
                    <option value="kg">Kilogram</option>
                    <option value="l">Litre</option>
                    <option value="g">Gram</option>
                    <option value="lb">Pound</option>
                    <option value="m">Meter</option>
                    <option value="cm">Centimeter</option>
                </select>
                <br><br>
                <button type="submit" class="btn btn-success">Submit</button>
                <a href="/home" class="btn btn-warning">Home</a>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/fragment/js.jsp"/>
</body>
</html>