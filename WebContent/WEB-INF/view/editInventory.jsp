<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<title>在庫管理</title>
</head>
<body>
<div class="container-fluid">
<c:import url="parts/nav.jsp" />
<h1>在庫編集</h1>
<form action="" method="post">
  <table border="1">
   <tr>
     <th>仕入先名</th>
     <td>
      <select name="supplierId">
       <c:forEach items="${inventoryDistinctList1 }" var="inventory">
       <option value="${inventory.supplierId }"><c:out value="${inventory.supplierName }"/></option>
       </c:forEach>
      </select>
     </td>
   </tr>

   <tr>
     <th>在庫品名</th>
     <td>
      <select name="itemId">
       <c:forEach items="${inventoryDistinctList2 }" var="inventory">
       <option value="${inventory.itemId }"><c:out value="${inventory.itemName }"/></option>
       </c:forEach>
      </select>
     </td>
   </tr>

   <tr>
     <th>価格</th>
     <td>
       <c:if test="${not empty priceError }">
       <p><c:out value="※${priceError }"/></p>
       </c:if>
       <input type="text" name="price" value="<c:out value="${price }"/>"/>
     </td>
   </tr>

   <tr>
     <th>個数</th>
     <td>
       <c:if test="${not empty quantityError }">
       <p><c:out value="※${quantityError }"/></p>
       </c:if>
       <input type="text" name="quantity" value="<c:out value="${quantity }"/>"/>
     </td>
   </tr>

   <tr>
     <th>備考</th>
     <td>
     <input type="text" name="memo" value="<c:out value="${memo}"/>"/>
     </td>
    </tr>

  </table>
  <p>
   <input type="submit" value="保存"/>
  </p>
</form>
</div>
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>