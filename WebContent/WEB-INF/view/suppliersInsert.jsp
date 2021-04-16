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

<title>仕入先追加</title>
</head>
<body>
<div class="container-fluid">
<c:import url="parts/nav.jsp" />
<h1>仕入先追加</h1>
<form action="" method="post">
<table border="1">


<tr>
     <th>仕入先名</th>
     <td>
     <input type="text" name="supplierName" value="<c:out value="${supplierName}"/>"/>
     </td>
 </tr>


 </table>
<p>
  <input type="submit" value="保存"/>
</p>
</form>
</div>

</body>
</html>