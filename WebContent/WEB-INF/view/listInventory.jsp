<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>在庫管理</title>
</head>
<body>
<h1>在庫一覧</h1>
<p><a href="logout">ログアウト</a></p>
<table border="1">
  <tr>
    <th>ID</th>
    <th>仕入先名</th>
    <th>在庫品名</th>
    <th>価格</th>
    <th>個数</th>
    <th>備考</th>
    <th>納品日</th>
    <th>編集日</th>
    <th>編集</th>
    <th>削除</th>
  </tr>
  <c:forEach items="${inventoryList }" var="inventory">
  <tr>
    <td><c:out value="${inventory.id }"/></td>
    <td><c:out value="${inventory.supplierName }"/></td>
    <td><c:out value="${inventory.itemName }"/></td>
    <td><c:out value="${inventory.price }"/></td>
    <td><c:out value="${inventory.quantity }"/></td>
    <td><c:out value="${inventory.memo }"/></td>
    <td><c:out value="${inventory.created }"/></td>
    <td><c:out value="${inventory.update }"/></td>
    <td><a href="editInventory?id=<c:out value="${inventory.id }" />">編集</a>
    </td>
    <td><a href="deleteInventory?id=<c:out value="${inventory.id }" />">削除</a>
    </td>
  </tr>
  </c:forEach>
</table>
<p>
 <a href="addInventory">在庫の追加</a>
</p>
</body>
</html>