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
<div class="container">

<h1>在庫管理システム ログイン</h1>

<c:if test="${not empty error }">
<p>ログインIDかパスワードが正しくありません。</p>
</c:if>

<form action="" method="post">
 <p>
  <input type="text" name="loginId" placeholder="ログインID" class="form-control" />
 </p>
 <p>
  <input type="password" name="loginPass" placeholder="パスワード" class="form-control" />
 </p>
 <p>
  <input type="submit" class="btn btn-primary btn-block" value="ログイン" />
 </p>
</form>
</div>
<script src="js/jquery-2.2.4.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>