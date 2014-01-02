$(function() {
	var width = $(document.body).width();
	var height = $(document.body).height();
	var userinfo = $.cookie("loginInfoCustomerms");
	if(userinfo!=null&&""!=userinfo){
		var array = userinfo.split("§");
		$("#userCode").val(array[0]);
		$("#userPwd").val(array[1]);
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
		var url = 'login/userLogin.do';
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
					 var loginInfo = $("#userCode").val()+"§"+$("#userPwd").val();
					 $.cookie("loginInfoCustomerms",loginInfo,{expires: 30,path:'/'});
				 }else{
					 $.cookie("loginInfoCustomerms",null,{path:'/'});
				 }
				 $("#loginForm").mask({maskMsg:'正在登录验证'});
				 return true;
			},
			success : function(data) {
				var result = eval('(' + data + ')');
				if(result.isSuccess){
					window.location='main.do';
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
			if(result.data.userId!=null){
				window.location='main.do';
			}
		}
	});
});
