<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<header>
<nav class="navbar navbar-expand-md navbar-light bg-light">
 <a class="navbar-brand" href="#">Inventory Manager</a>
 <button type="button" class="navbar-toggler"
   data-toggle="collapse" data-target="#menu">
   <span class="navbar-toggler-icon"></span>
 </button>
 <div class="collapse navbar-collapse" id="menu">
   <ul class="navbar-nav">
    <li class="nav-item active"><a class="nav-link" href="listInventory">Home</a></li>
    <li class="nav-item"><a class="nav-link" href="logout">ログアウト</a></li>
   </ul>
 </div>
</nav>
</header>