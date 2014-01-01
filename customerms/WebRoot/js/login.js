$(function() {
	if ($.browser.msie && $.browser.version <=6){
		var fn = function(){
			window.close();
		};
		$.messager.alert('提示','对不起,您使用的浏览器版本过低,请至少使用IE7','info',fn);
		return;
	}
	var width = $(document.body).width();
	var height = $(document.body).height();
	
	var userinfo = $.cookie("loginInfoCMS");
	if(userinfo!=null&&""!=userinfo){
		var array = userinfo.split("§");
		$("#userCode").val(array[0]);
		$("#userPwd").val(array[1]);
		$("input[type=radio][id=role][value="+array[2]+"]").attr("checked",'checked');
		$("#mindpwd").attr('checked','true');
	}
	var loginCaptchaImageRefresh = function(){
		$("#loginCaptcha").val('');
		$("#loginCaptchaImage").attr("src", "style/v1/images/login/img.jsp?date="+new Date());
	};
	// 点击刷新验证码图片
	$("#loginCaptchaImage").click( function() {
		loginCaptchaImageRefresh();
	});
	$(document).keydown(function(e){ 
		var curKey = e.which; 
		if(curKey == 13){ 
			$('#loginBtn').click();
		} 
	});
	$("#loginBtn").click(function(){
		var url = null;
		var role = $(':radio[id=role][checked=checked]').val();
		if(role=='teacher'){
			url = 'login/teacherLogin.do';
		}else if(role=='student'){
			url = 'login/studentLogin.do';
		}
		$('#loginForm').form('submit', {
			url : url,
			onSubmit : function() {
				var userCode = $.trim($("#userCode").val());
				var userPwd = $.trim($("#userPwd").val());
				var loginCaptcha = $.trim($("#loginCaptcha").val());
				if(''==userCode){
					$.messager.alert('提示：','请输入登录名','warning');
					return false;
				}
				if(''==userPwd){
					$.messager.alert('提示：','请输入密码','warning');
					return false;
				}
				if(''==loginCaptcha){
					$.messager.alert('提示：','请输入验证码','warning');
					return false;
				}
				//记住密码
				 if($("#mindpwd").prop("checked")){
					 var loginInfo = $("#userCode").val()+"§"+$("#userPwd").val()+"§"+$(':radio[id=role][checked=checked]').val();
					 $.cookie("loginInfoCMS",loginInfo,{expires: 30,path:'/'});
				 }else{
					 $.cookie("loginInfoCMS",null,{path:'/'});
				 }
				 $("#loginForm").mask({maskMsg:'正在登录验证'});
				 return true;
			},
			success : function(data) {
				var result = eval('(' + data + ')');
				if(result.isSuccess){
					if(role=='teacher'){
						window.location='main.do';
					}else if(role=='student'){
						window.location='mainStu.do';
					}
				}else{
					$.messager.alert('提示：',result.message,'error');
					loginCaptchaImageRefresh();
				}
				$("#loginForm").mask('hide');
			}
		});
		return false;
	});
	
	//异步取得用户是否登录系统
	$.ajax({
		url: 'initLogin.do',
		dataType: 'json',
		async: true,
		success: function(result) {
			if(result.data.teacherId!=null){
				window.location='main.do';
			}
		}
	});
});
