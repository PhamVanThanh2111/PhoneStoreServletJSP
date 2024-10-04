<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Shopping Cart</title>
<style>
    body {
        font-family: Arial, sans-serif;
    }
    table {
        width: 80%;
        margin: auto;
        border-collapse: collapse;
    }
    table, th, td {
        border: 1px solid #ddd;
        padding: 8px;
    }
    th {
        background-color: #C4DAD2;
        text-align: left;
    }
    td {
        text-align: center;
    }
    .empty-cart {
        text-align: center;
        padding: 20px;
    }
    .action-buttons input[type="submit"] {
        margin-left: 10px;
        padding: 5px 10px;
    }
</style>
</head>
<body>

    <h1 align="center">Shopping Cart</h1>
    <p align="center">
        <a href="productList">Back to Product List</a>
    </p>

    <table>
        <tr>
            <th>Model Description</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Total</th>
            <th>Actions</th>
        </tr>

        <c:choose>
            <c:when test="${cart.lineItemCount == 0}">
                <tr>
                    <td colspan="5" class="empty-cart">Your cart is currently empty.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="counter">
                    <tr>
					    <form method="POST" action="CartController">
					        <td>${cartItem.strPartNumber}</td>
					        <td>
					            <input type="hidden" name="itemIndex" value="${counter.index}">
					            <input type="text" name="quantity" value="${cartItem.iQuantity}" size="2">
					        </td>
					        <td>${cartItem.dblUnitCost}</td>
					        <td>${cartItem.dblTotalCost}</td>
					        <td>
					            <input type="submit" name="action" value="Update">
					            <input type="submit" name="action" value="Delete">
					        </td>
					    </form>
					</tr>
                </c:forEach>
                <tr>
                    <td colspan="3" align="right"><strong>Subtotal:</strong></td>
                    <td colspan="2">$${cart.orderTotal}</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>

</body>
</html>
