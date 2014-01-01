<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="../style/common.css">
	<link rel="stylesheet" type="text/css" href="../style/v1/easyui/easyui.css">
	<link rel="stylesheet" type="text/css" href="../style/v1/icon.css">
	<link rel="stylesheet" type="text/css" href="../style/v1/common.css">
	<link rel="stylesheet" type="text/css" href="../style/v1/index.css">
	<script type="text/javascript" src="../js/common/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="../js/common/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../js/common/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../js/index/index.js"></script>
  </head>
  
  <body>
    <h1>登陆</h1>
    <a href="main.do" class="easyui-linkbutton" iconCls="icon-reload" id="sale">进入欢迎界面</a>
  </body>
</html>
