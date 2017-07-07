<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>企业风险外贸预警系统</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- VENDOR CSS -->
    <link rel="stylesheet" href="../assets/lib/Klorofil/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/lib/Klorofil/vendor/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../assets/lib/Klorofil/vendor/linearicons/style.css">
    <link rel="stylesheet" href="../assets/lib/Klorofil/vendor/chartist/css/chartist-custom.css">
    <!-- MAIN CSS -->
    <link rel="stylesheet" href="../assets/lib/Klorofil/css/main.css">
    <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
    <link rel="stylesheet" href="../assets/lib/Klorofil/css/demo.css">
    <!-- GOOGLE FONTS -->
    <!--<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">-->
    <link href="../assets/css/risk.css" rel="stylesheet">
    <!-- ICONS -->
    <link rel="apple-touch-icon" sizes="76x76" href="../assets/lib/Klorofil/img/apple-icon.png">
    <link rel="icon" type="image/png" sizes="96x96" href="../assets/lib/Klorofil/img/favicon.png">
</head>

<body>
<!-- WRAPPER -->
<div id="wrapper">
    <!-- NAVBAR -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="brand">
            <a href="index1.jsp">企业外贸风险预警系统</a>
        </div>
        <div class="container-fluid">
            <div id="navbar-menu">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="../assets/lib/Klorofil/img/user.png" class="img-circle" alt="Avatar"> <span>Samuel</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                        <ul class="dropdown-menu">
                            <li><a href="#"><i class="lnr lnr-envelope"></i> <span>Message</span></a></li>
                            <li><a href="#"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
                        </ul>
                    </li>
                    <!-- <li>
                        <a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
                    </li> -->
                </ul>
            </div>
        </div>
    </nav>
    <!-- END NAVBAR -->
    <!-- LEFT SIDEBAR -->
    <div id="sidebar-nav" class="sidebar">
        <div class="sidebar-scroll">
            <nav>
                <ul class="nav">
                    <li><a href="index1.jsp"><i class="lnr lnr-home"></i> <span>区域风险</span></a></li>
                    <li><a href="index2.jsp"><i class="lnr lnr-home"></i> <span>汇率风险</span></a></li>
                    <li><a href="index3.jsp"><i class="lnr lnr-home"></i> <span>内控风险</span></a></li>
                    <li><a href="index4.jsp" class="active"><i class="lnr lnr-home"></i> <span>信用风险</span></a></li>
                </ul>
            </nav>
        </div>
    </div>
    <!-- END LEFT SIDEBAR -->
    <!-- MAIN -->
    <div class="main">
        <!-- MAIN CONTENT -->
        <div class="main-content">
            <div class="container-fluid">
                <div id="tips" class="alert alert-success " role="alert"></div>
                <h3 class="page-title">信用风险</h3>
                <div id="toastr-demo">
                    <div class="panel-body">
                        <!-- INPUTS -->
                        <!--左面板-->
                        <!--输入数值-->
                        <div id="leftPal" class="col-md-offset-1 col-md-5">
                            <div class="row">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="search" id="search" placeholder="公司搜索">
                                    <span class="input-group-addon glyphicon glyphicon-search" aria-hidden="false"></span>
                                </div>
                                <ul id="items" class="list-group">

                                    <!--显示相关内容-->
                                </ul>
                            </div>
                            <div class="row">

                            </div>

                            <div class="row">
                                <table class="finance-form table table-condensed"  >

                                    <caption><h4 class="ui dividing header">Financial Information</h4></caption>

                                    <thead class="seven wide field">
                                    <th><lable>存货周转率</lable></th>
                                    <th>
                                        <input type="number"  name="inventoryTurnover" id="inventoryTurnover" maxlength="20" placeholder="Inventory Turnover" step="0.001"/>
                                    </th>
                                    </thead>

                                    <thead class="seven wide field">
                                    <th><label>净资产收益变化率</label></th>
                                    <th><input type="number"  name="netAssetsIncome" id="netAssetsIncome" maxlength="20" placeholder="Net Assets Income"  step="0.001"/></th>
                                    </thead>



                                    <thead class="seven wide field">
                                    <th><label>净利润</label></th>
                                    <th><input type="number"  name="netProfit" id="netProfit" maxlength="20" placeholder="Net Profit" step="0.001"/></th>
                                    </thead>

                                    <thead class="seven wide field">
                                    <th><label> 净利润增长率 1</label></th>
                                    <th><input type="number" name="netProfitIncrease1" id="netProfitIncrease1" maxlength="20" placeholder="Net Profit Increase 1"step="0.001"/></th>
                                    </thead>

                                    <thead class="seven wide field">
                                    <th><label>净利润增长率 2</label></th>
                                    <th><input type="number" name="netProfitIncrease2" id="netProfitIncrease2" maxlength="20" placeholder="Net Profit Increase 2 #"step="0.001"/></th>
                                    </thead>

                                    <thead class="seven wide field">
                                    <th><label>总资产增长率</label></th>
                                    <th><input type="number" name="totalAssetsIncrease" id="totalAssetsIncrease" maxlength="20" placeholder="Total Assets Increase"step="0.001"/></th>
                                    </thead>
                                    <thead>
                                    <th></th>
                                    <th><input type="submit" value="计算风险" class="caculater-button ui button s btn btn-primary" tabindex="0"></input></th>
                                    </thead>
                                </table>
                            </div>
                        </div>
                        <!--右面版-->
                        <!--显示结果-->
                        <div id="rightPal" class="col-md-offset-2 col-md-4">
                            <div id="companyRiskRate" class="row">
                                <h3 id="netValue"><strong>公司网络评价系数</strong></h3>
                                <small>越接近1网络评价系数越低</small>
                                <div id="knob" class="row">
                                    <input type="text" value="0" class="dial">
                                </div>
                            </div>

                            <div  id="assess" class="row">
                                <h3 id="companyValue"><strong>企业信用风险系数</strong></h3>
                                <div class="company-wapper">
                                    <div class="company-id" style="display: none"></div>
                                    <div id="company-name"></div>
                                    <div id="company-assess"><h3 class="perc">0.00</h3></div>
                                </div>
                            </div>

                        </div>
                    </div>

                </div>

            </div>
        </div>
        <!-- END MAIN CONTENT -->
    </div>
    <!-- END MAIN -->
    <div class="clearfix"></div>
    <footer>
        <div class="container-fluid">
            <p class="copyright"></p>
        </div>
    </footer>
</div>
<!-- END WRAPPER -->
<!-- Javascript -->
<script src="../assets/lib/Klorofil/vendor/jquery/jquery.min.js"></script>
<script src="../assets/lib/Klorofil/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="../assets/lib/Klorofil/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script src="../assets/lib/Klorofil/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
<script src="../assets/lib/Klorofil/vendor/chartist/js/chartist.min.js"></script>
<script type="text/javascript" src="../assets/js/lib/jquery.knob.min.js"></script>
<script src="../assets/js/ctl/enriskCtl.js"></script>
<script src="../assets/lib/Klorofil/scripts/klorofil-common.js"></script>
</body>
</html>