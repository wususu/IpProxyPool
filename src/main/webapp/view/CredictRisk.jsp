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
<style type="text/css">
	.result {
    font-size: x-large;
}
.company-credict-risk {
    color: red;
</style>
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
	<div class="company-id" style="display: none"></div>
	<div id="company-name"></div>
	<div id="company-assess"></div>
</div>
</div>

<form class="finance-form"  method="post" >

  <h4 class="ui dividing header">Financial Information</h4>

    
      <div class="seven wide field">
          <label>存货周转率</label>
      	<input type="number"  name="inventoryTurnover" id="inventoryTurnover" maxlength="20" placeholder="Inventory Turnover" step="0.001"/>
      </div>  
  
      <div class="seven wide field">
          <label>净资产收益变化率</label>
      	<input type="number"  name="netAssetsIncome" id="netAssetsIncome" maxlength="20" placeholder="Net Assets Income"  step="0.001"/>
      </div>
  

  
      <div class="seven wide field">
          <label>净利润</label>
      	<input type="number"  name="netProfit" id="netProfit" maxlength="20" placeholder="Net Profit" step="0.001"/>
      </div>
  
    <div class="seven wide field">
      <label> 净利润增长率 1</label>
      <input type="number" name="netProfitIncrease1" id="netProfitIncrease1" maxlength="20" placeholder="Net Profit Increase 1"step="0.001"/>
    </div>
  
  <div class="seven wide field">
      <label>净利润增长率 2</label>
      <input type="number" name="netProfitIncrease2" id="netProfitIncrease2" maxlength="20" placeholder="Net Profit Increase 2 #"step="0.001"/>
    </div>

  <div class="seven wide field">
      <label>总资产增长率</label>
      <input type="number" name="传otalAssetsIncrease" id="totalAssetsIncrease" maxlength="20" placeholder="Total Assets Increase"step="0.001"/>
    </div>

  <input type="submit" value="计算风险" class="caculater-button ui button s" tabindex="0">Submit</div>
</form>
<div class="result">
<p>企业信用风险系数:</p>
<div class="company-credict-risk"></div>
</div>
</div>

</body>
</html>