<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylseet" />
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid">
<c:import url="parts/nav.jsp" />

<h1>在庫集計結果</h1>
<table class="table table-bordered table-striped">
<tr>
  <th>在庫品名</th>
  <th>在庫総数</th>
</tr>
<c:forEach items="${inventoryCountList}" var="count">
<tr>
  <td><c:out value="${count.itemName }"/></td>
  <td><c:out value="${count.countQuantity }"/></td>


</tr>
</c:forEach>
</table>
</div>
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>