<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.a {
	width: 160px;
	height: 200px;
	border: 1px solid black;
	padding: 5px;
	margin: 10px;
	float: left;
	text-align: center;
}

.hinh {
	width: 80px;
	height: 100px;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<p>
		<a href="ShoppingCart.jsp">View Cart</a>
	</p>
	<c:forEach items="${ds}" var="sp">
		<div class="a">
			<form name="modelDetail" method="POST"
				action="CartController">
				${sp.model}<br /> 
				<img src="./resources/images/${sp.imgURL}" class="hinh" /><br />
				Price: ${sp.price}<br /> 
				<input type="text" size="2" value="1" name="quantity"><br />
				<input type="submit" value="Add to Cart"> 
				<input type="hidden" name="modelNo" value="${sp.id}"> 
				<input type="hidden" name="price" value="${sp.price}"> 
				<input type="hidden" name="model" value="${sp.model}"> 
				<input type="hidden" name="description" value="${sp.description}">
				<input type="hidden" name="action" value="Add">
			</form>
		</div>
	</c:forEach>
</body>
</html>