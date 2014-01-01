window.onerror = function(){   
    return true;   
};
$(function(){
	setInterval('AutoScroll("#scrollmessage")',2000);
	setInterval(function(){$('#time').html(currentTime);},1000);
	$("#KinSlideshow").KinSlideshow({
			moveStyle:"down",
			intervalTime:8,
			mouseEvent:"mouseover",
			titleFont:{TitleFont_size:14,TitleFont_color:"#FF0000"}
	});
	var selectUrl = null;
	$("#friendLink").change(function(){
		var url=$(this).children('option:selected').attr('url');//这就是selected的值 
		if(url!=null && url!='' && url!=selectUrl){
			selectUrl = url;
			window.open(selectUrl);
		}else{
			selectUrl = null;
		}
	});
	//异步取得用户是否登录系统，如果登录系统，则显示欢迎用户登录，如果没有登录，则显示用户登录界面
	$.ajax({
		url: "initLogin.do",
		dataType: "json",
		async: true,
		success: function(result) {
			if(result.data.teacherId==null){
				$('#welcomeUser').hide();
				$('#loginUser').show();
			}else{
				$('#teacherName').html(result.data.teacherName);
				$('#welcomeUser').show();
				$('#loginUser').hide();
			}
		}
	});
	var loginCaptchaImageRefresh = function(){
		$("#loginCaptcha").val('');
		$("#loginCaptchaImage").attr("src", "style/v1/images/login/img.jsp?date="+new Date());
	};
	// 点击刷新验证码图片
	$("#loginCaptchaImage").click( function() {
		loginCaptchaImageRefresh();
	});
	var loginValidate = function(){
		var userCode = $.trim($('#userCode').val());
		var userPwd = $.trim($('#userPwd').val());
		var loginCaptcha = $.trim($('#loginCaptcha').val());
		if(userCode==''){
			alert('请输入用户名');
			return false;
		}
		if(userPwd==''){
			alert('请输入密码');
			return false;
		}
		if(loginCaptcha==''){
			alert('请输入验证码');
			return false;
		}
		return true;
	};
	$("#loginBtn").click(function(){
		if(loginValidate()){
			//jquery form 表达提交
		   	$('#userLoginForm').ajaxSubmit({ 
				url: 'login/teacherLogin.do', 
				dataType:  'json',
				success: function(result) { 
					if(result.isSuccess){
						$('#teacherName').html(result.data.teacherName);
						$('#welcomeUser').show();
						$('#loginUser').hide();
						$('#loginDialog').dialog('close');
					}else{
						alert(result.message);
						loginCaptchaImageRefresh();
					}
				}
			});
		}
	});
	$('#logout').click(function(){
		$.post('system/logoutTeacher.do',function(result){
			if(result.isSuccess){
				loginCaptchaImageRefresh();
				$('#welcomeUser').hide();
				$('#userCode').val('');
				$('#userPwd').val('');
				$('#loginCaptcha').val('');
				$('#loginUser').show();
				$('#teacherName').html('');
			}
		},'json');
	});
	$('#unify_searchBtn').click(function(){
		var kw=$.trim($("#keyword").val());
	    if (!kw || kw=='请输入关键词...') {
	        alert('请输入搜索关键词!');
	        $("#keyword").focus();
	        return false;
	    }else{
	    	return true;
	    }
		return false;
	});
	//浏览器有滚动条时的操作
	$(window).scroll(function() { 
		//判断登录框是否打开
		if(!$("#loginDialog").dialog('options').closed){
			$("#loginDialog").dialog("center");
		}
	});
	//浏览器窗口大小改变时 
	$(window).resize(function() { 
		//判断登录框是否打开
		if(!$("#loginDialog").dialog('options').closed){
			$("#loginDialog").dialog("center");
		} 
	});
	//=============================登录=================================
	//-----------------------------显示---------------------------------
	$('#showLoginBtn').click(function(){
		$('#loginDialog').dialog('open');
		$("#loginDialog").dialog("center");
	});
	$('#loginDialog').dialog({  
	    title: '登录',  
	    width:400,
	    height:210,
	    closed: true,  
	    cache: false,  
	    modal: true
	}); 
});