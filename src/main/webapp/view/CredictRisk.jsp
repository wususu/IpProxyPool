<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="../js/action.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
</head>
<body>
<div class="search-form">
<input type="text" name="search" id="search" placeholder="公司搜索">
<div id="items">
</div>
</div>

<div class="container">
<div id="assess">
<h4>公司网络评价系数:(越接近1网络评价系数越低)</h4>
<div class="company-wapper">
	<div id="company-name"></div>
	<div id="company-assess"></div>
</div>
</div>
</div>
</body>
</html>