<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr>
  <th>在庫品ID</th>
  <th>合計金額</th>
</tr>
<c:forEach items="${inventoryCountList}" var="count">
<tr>
  <td><c:out value="${count.itemId }"/></td>

</tr>
</c:forEach>
</table>
</body>
</html>