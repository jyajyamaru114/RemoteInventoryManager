<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylseet" />
<title>在庫管理</title>
</head>
<body>
<div class="container-fluid">
<c:import url="parts/nav.jsp" />
<h1>在庫品一覧</h1>
<table class="table table-bordered table-striped">
  <tr>
    <th>ID</th>
    <th>在庫品名</th>
    <th>編集</th>
    <th>削除</th>
  </tr>
  <c:forEach items="${itemList }" var="item">
  <tr>
    <td><c:out value="${item.id }"/></td>
    <td><c:out value="${item.itemName }"/></td>
    <td><a href="editItem?id=<c:out value="${suppliers.id }" />">編集</a>
    </td>
    <td><a href="deleteItem?id=<c:out value="${suppliers.id }" />">削除</a>
    </td>
  </tr>
  </c:forEach>
</table>

<p>
 <a href="addItem" class="btn btn-primary">新規在庫品の登録</a>
</p>

</div>
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>