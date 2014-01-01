<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>">
	<title>会员管理系统</title>
	<link rel="stylesheet" type="text/css" href="style/v1/common.css">
	<link rel="stylesheet" type="text/css" href="js/easyui/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/easyui/icon.css">
	<link rel="stylesheet" type="text/css" href="style/v1/main.css">
	
	<script type="text/javascript" src="js/common/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="js/common/commons-ajaxclient.js"></script>
	<script type="text/javascript" src="js/common/serializeForm.js"></script>
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/common/jquery.xLazyLoader.js"></script>
	<script type="text/javascript" src="js/common/csit.core.js"></script>
	
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		var sessionId = '${pageContext.session.id}' ;	
		var basePath = '<%=basePath%>';
	</script>
</head>
<body class="easyui-layout" id="all-body" >
	<div region="north" id="index-north" border="false" style="height: 40px">
		<div  id="index-north-bd">
			<div id="index-north-bd-left" class="f-l" style="padding-top: 3px">
            	<span style="font-size: 25;padding-left: 22px">会员管理系统</span>
            </div>
            <div id="index-north-bd-right" class="f-r" style="float: right;">
            	 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"  id="modifyPwdBtn">修改密码</a>
                 <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true"  id="exitSystem">退出</a>
            </div>
	    </div>
	</div>
	<div region="west" split="true" title="--------导航菜单--------" icon="icon-none" id="index-left">
	  <ul id="rightTree">
	  	<li><a href="javascript:void(0)" name="system/rights.html">权限管理</a></li>
	  </ul>
	</div>
	<div region="center" style="overflow: hidden;"  border="false">
	      <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
	                <div title="首页" id="reminder">
	                	<div class="easyui-layout" data-options="fit:true">
							<div data-options="region:'center',title:'最新动态',border:false,split:true" >
							</div>
							<div data-options="region:'east',border:false,split:true,title:'系统提醒'" style="width:490px;">
							</div>
						</div>
					</div>
	        </div>
	</div>
	<div id="mm" class="easyui-menu" style="width:120px">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div>
		<div id="mm-tabcloseright">关闭当前右侧</div>
		<div id="mm-tabcloseleft">关闭当前左侧</div>
	</div>
	<div id="modifyPwdDialog">
		<form id="modifyPwdForm">
				<table border="0" cellpadding="0" cellspacing="0">
	     		<tr >
	     			<td class="tdFirstTitle">原密码：</td>
	     			<td class="tdFirstContent">
	     				<input type="password" name ="passwords" id="passwords">
	     			</td>
	     		</tr>
	     		<tr>
	     			<td class="tdTitle">新密码：</td>
	     			<td class="tdContent">
	     				<input type="password" name="newTeacherPwd" id="newTeacherPwd">
	     			</td>
	     		</tr>
	     		<tr>
	     			<td class="tdTitle">重新输入新密码：</td>
	     			<td class="tdContent">
	     				<input type="password" name="newTeacherPwd2" id="newTeacherPwd2">
	     			</td>
	     		</tr>
	     	</table>
		</form>
	</div>
</body>
</html>
