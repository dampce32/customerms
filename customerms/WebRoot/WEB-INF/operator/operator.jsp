<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


	<link rel="stylesheet" type="text/css" href="style/common.css">
	<link rel="stylesheet" type="text/css" href="style/v1/easyui/easyui.css">
	<link rel="stylesheet" type="text/css" href="style/v1/icon.css">
	<link rel="stylesheet" type="text/css" href="style/v1/common.css">
	
	<link rel="stylesheet" type="text/css" href="style/v1/main.css">
	<script type="text/javascript" src="js/common/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="js/common/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/common/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/operator/operator.js"></script>
	
  </head>
  
  <body>
	   <div class="page-hd">
	          <div class="page-hd-title">用户管理</div>          
			  <div class="page-hd-title-rcaption"></div>
	          <div id="toolbar">
	             <div id="toolbar-btn" class="f-l">
	                     <a href="#" class="easyui-linkbutton" iconCls="icon-add" id="add">新增</a>
	                     <a href="#" class="easyui-linkbutton" iconCls="icon-remove" id="detele">删除</a>
	             </div>
	          </div>
	    </div>
		
	    <div class="page-bd">
	        	<table id="operatorTable">
	    
	   			</table>
	   </div>
	   
	   <div id="addOperatorDialog" title="添加用户" style="width:450px;height:300px">
			<form id="addOperatorForm" method="post" style="pardding:3px">
           <table class="operatorTable">
            <tr>
              <td><input  type="hidden" name="ID"/></td>
            </tr>
            <tr>
              <td>登录名</td>
              <td><input class="easyui-validatebox" type="text" name="code" required="true"/></td>
            </tr>
           <tr>
              <td>姓名</td>
              <td><input class="easyui-validatebox" type="text" name="name" required="true"/></td>
            </tr>
         <tr class="password">
				<td>&nbsp;</td>
				<td>密码默认123456</td>
			</tr>
		<tr class="password">
			<td>密码</td>
			<td><input class="easyui-validatebox"  name="password" type="password" maxlength="32" style="width:150px;" value="123456" /></td>
		</tr>
		<tr>
			<td>备注</td>
			<td><textarea rows="3" cols="17" name="note"  ></textarea></td>
		</tr>
           </table>
          </form>
		</div>
		
  </body>
  <script type="text/javascript">
  var js = '<%=basePath %>';
</script>
</html>
