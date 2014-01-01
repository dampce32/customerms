$(function(){
	var resetPwdTabs = $('#resetPwdTabs');
	
	var mobilePhoneTitle = $('#mobilePhoneTitle',resetPwdTabs);
	var mobilePhoneSendForm = $('#mobilePhoneSendForm',mobilePhoneTitle);
	var mobilePhoneValidateForm = $('#mobilePhoneValidateForm',mobilePhoneTitle);
	var mobilePhoneResetForm = $('#mobilePhoneResetForm',mobilePhoneTitle);
	
	var emailTitle = $('#emailTitle',resetPwdTabs);
	var emailSendForm = $('#emailSendForm',emailTitle);
	var emailValidateForm = $('#emailValidateForm',emailTitle);
	var emailResetForm = $('#emailResetForm',emailTitle);

//	var switchResult = syncCallService('getResetSwitchStatusValidateInfo.do');
//	if(switchResult.isSuccess){
//		var mailSwitch = switchResult.data.mailSwitch;
//		var msgSwitch = switchResult.data.msgSwitch;
//		if(msgSwitch==0&&mailSwitch==0){
//			$(resetPwdTabs).tabs('close',0);
//			$(resetPwdTabs).tabs('close',0);
//			$(resetPwdTabs).hide();
//			$(mobilePhoneTitle).hide();
//			$(emailTitle).hide();
//		}else if(msgSwitch==0&&mailSwitch==1){
			$(resetPwdTabs).tabs('close',0);
//		}else if(msgSwitch==1&&mailSwitch==0){
//			$(resetPwdTabs).tabs('close',1);
//		}
//	}
	
	$(mobilePhoneValidateForm).hide();
	$(mobilePhoneResetForm).hide();
	$(emailValidateForm).hide();
	$(emailResetForm).hide();
	
	$(':radio[name=role]:eq(0)',emailSendForm).attr('checked','checked'); 
	if(pageType=='1'){
		$('.role',emailSendForm).hide();
	}
	
	var imageRefreshMobliePhone = function(){
		$("#loginCaptcha",mobilePhoneTitle).val('');
		$("#loginCaptchaImage",mobilePhoneTitle).attr("src", "style/v1/images/login/img.jsp?date="+new Date());
	};
	var imageRefreshEmail = function(){
		$("#loginCaptcha",emailTitle).val('');
		$("#loginCaptchaImage",emailTitle).attr("src", "style/v1/images/login/img.jsp?date="+new Date());
	};
	
	var timeCount = 59;
	var changeInterval = null;
	var currentTitle = null;
	var changeBtnText = function(){
		if(timeCount==0){
			clearInterval(changeInterval);
			timeCount = 59;
			$('#sendAgainSpan',currentTitle).html(
				'<a href="javascript:void(0)">点击再次发送验证码</a>'
			);
			return;
		}
		$('#sendAgainSpan',currentTitle).html(timeCount+'秒钟后可重新发送');
		timeCount --;
	};
	
	$(resetPwdTabs).tabs({
		onSelect:function(title,index){
	  		if(title=='通过注册手机找回密码'){
	  			$(mobilePhoneValidateForm).hide();
	  			$(mobilePhoneResetForm).hide();
	  			$(mobilePhoneSendForm).show();
	  			imageRefreshMobliePhone();
	  		}
	  		if(title=='通过注册邮箱找回密码'){
	  			$(emailValidateForm).hide();
	  			$(emailResetForm).hide();
	  			$(emailSendForm).show();
	  			imageRefreshEmail();
		  	}
		}
	});
	
	//----------------------------短信-----------------------------
//	$("#loginCaptchaImage",mobilePhoneTitle).click( function() {
//		imageRefreshMobliePhone();
//	});
//	$('.input',mobilePhoneSendForm).focus(function(){
//		$('#mobilePhoneSendMsg',mobilePhoneTitle).html('');
//	});
//	$('#mobilePhoneSend',mobilePhoneTitle).click(function(){
//		$(mobilePhoneSendForm).form('submit',{  
//		    url:'login/mobilePhoneSendValidateInfo.do',  
//		    onSubmit: function(){
//		    	var mobilePhone = $('#mobilePhone',mobilePhoneSendForm).val();
//		    	if(''==mobilePhone){
//		    		$('#mobilePhoneSendMsg',mobilePhoneTitle).html('请填写注册手机号码');
//		    		return false;
//		    	}
//		    	var isAgain = $('#isAgain',mobilePhoneTitle).val();
//		    	if(''==isAgain){
//			    	var loginCaptcha =$('#loginCaptcha',mobilePhoneSendForm).val();
//			    	if(''==loginCaptcha){
//			    		$('#mobilePhoneSendMsg',mobilePhoneTitle).html('请填写验证码');
//			    		return false;
//			    	}
//		    	}
//		    	return true;
//		    },  
//		    success:function(data){
//		    	var result = eval('('+data+')');
//				if(result.isSuccess){
//					$(mobilePhoneSendForm).hide();
//					$(mobilePhoneValidateForm).show();
//				}else{
//					$('#mobilePhoneSendMsg',mobilePhoneTitle).html(result.message);
//					imageRefreshMobliePhone();
//				}
//		    }
//		});
//	});
//	$('#sendAgainSpan',mobilePhoneTitle).click(function(){
//		$('#isAgain',mobilePhoneTitle).val('1');
//		$('#mobilePhoneSend',mobilePhoneTitle).click();
//		currentTitle = mobilePhoneTitle;
//		changeInterval = setInterval(changeBtnText, 1000);
//	});
//	$('#mobilePhoneValidate',mobilePhoneTitle).click(function(){
//		$(mobilePhoneValidateForm).form('submit',{  
//		    url:'login/mobilePhoneConfirmValidateInfo.do',  
//		    onSubmit: function(){
//		    	var mobilePhoneCode =$('#mobilePhoneCode',mobilePhoneValidateForm).val();
//		    	if(''==mobilePhoneCode){
//		    		$('#mobilePhoneValidateMsg',mobilePhoneTitle).html('请填写验证码');
//		    		return false;
//		    	}
//		    	return true;
//		    },  
//		    success:function(data){  
//		    	var result = eval('('+data+')');
//				if(result.isSuccess){
//					$('#teacherId',mobilePhoneResetForm).val(result.data.teacherId);
//					$(mobilePhoneValidateForm).hide();
//					$(mobilePhoneResetForm).show();
//				}else{
//					$('#mobilePhoneValidateMsg',mobilePhoneTitle).html(result.message);
//				}
//		    }
//		});
//	});
//	$('#mobilePhoneReset',mobilePhoneTitle).click(function(){
//		$(mobilePhoneResetForm).form('submit',{  
//		    url:'login/resetPwdValidateInfo.do',  
//		    onSubmit: function(){
//		    	var userPwd = $('#userPwd',mobilePhoneResetForm).val();
//				if(''==userPwd){
//					$('#mobilePhoneResetMsg',mobilePhoneTitle).html('请填写密码');
//					return false;
//				}else if(/.*[\u4e00-\u9fa5]+.*$/.test(userPwd)){
//					$('#mobilePhoneResetMsg',mobilePhoneTitle).html('密码不能为中文');
//					return false;
//				}
//				var userPwd2 = $('#userPwd2',mobilePhoneResetForm).val();
//				if(''==userPwd2){
//					$('#mobilePhoneResetMsg',mobilePhoneTitle).html('请再次填写密码');
//					return false;
//				}
//				if(userPwd!=userPwd2){
//					$('#mobilePhoneResetMsg',mobilePhoneTitle).html('两次填写的密码不一致');
//					return false;
//				}
//		    	return true;
//		    },  
//		    success:function(data){  
//		    	var result = eval('('+data+')');
//				if(result.isSuccess){
//					window.location.href = '';
//				}else{
//					$('#mobilePhoneResetMsg',mobilePhoneTitle).html(result.message);
//				}
//		    }
//		});
//	});
	//----------------------------邮件-----------------------------
	$("#loginCaptchaImage",emailTitle).click( function() {
		imageRefreshEmail();
	});
	$('.input',emailSendForm).focus(function(){
		$('#emailSendMsg',emailTitle).html('');
	});
	$('#emailSend',emailTitle).click(function(){
		var email = $('#email',emailSendForm).val();
    	if(''==email){
    		$('#emailSendMsg',emailTitle).html('请填写注册邮箱地址');
    		return;
    	}
    	var isAgain = $('#isAgain',emailTitle).val();
    	if(''==isAgain){
	    	var loginCaptcha =$('#loginCaptcha',emailSendForm).val();
	    	if(''==loginCaptcha){
	    		$('#emailSendMsg',emailTitle).html('请填写验证码');
	    		return;
	    	}
    	}
		$(emailSendForm).ajaxSubmit({ 
			url:'login/emailSendValidateInfo.do',
			dataType:  'json',
			success: function(result) { 
				if(result.isSuccess){
					$('#showName',emailValidateForm).html(result.data.emailUserName);
					var email = $('#email',emailSendForm).val();
					var showEmail = email.substring(0,3)+'******'+email.substring(email.indexOf('@'));
					$('#showEmail',emailTitle).html(showEmail);
					$(emailSendForm).hide();
					$(emailValidateForm).show();
				}else{
					$('#emailSendMsg',emailTitle).html(result.message);
					imageRefreshEmail();
				}
			}
		});
	});
	$('#sendAgainSpan',emailTitle).click(function(){
		$('#isAgain',emailTitle).val('1');
		$('#emailSend',emailTitle).click();
		currentTitle = emailTitle;
		changeInterval = setInterval(changeBtnText, 1000);
	});
	$('#emailValidate',emailTitle).click(function(){
		var emailCode = $('#emailCode',emailValidateForm).val();
    	if(''==emailCode){
    		$('#emailValidateMsg',emailTitle).html('请填写验证码');
    		return;
    	}
		$(emailValidateForm).ajaxSubmit({ 
			url:'login/emailConfirmValidateInfo.do',
			dataType:  'json',
			success: function(result) { 
				if(result.isSuccess){
					$('#userId',emailResetForm).val(result.data.userId);
					$(emailValidateForm).hide();
					$(emailResetForm).show();
				}else{
					$('#emailValidateMsg',emailTitle).html(result.message);
				}
			}
		});
	});
	$('#emailReset',emailTitle).click(function(){
		var userPwd = $('#userPwd',emailResetForm).val();
		if(''==userPwd){
			$('#emailResetMsg',emailTitle).html('请填写密码');
			return;
		}else if(/.*[\u4e00-\u9fa5]+.*$/.test(userPwd)){
			$('#emailResetMsg',emailTitle).html('密码不能为中文');
			return;
		}
		var userPwd2 = $('#userPwd2',emailResetForm).val();
		if(''==userPwd2){
			$('#emailResetMsg',emailTitle).html('请再次填写密码');
			return;
		}
		if(userPwd!=userPwd2){
			$('#emailResetMsg',emailTitle).html('两次填写的密码不一致');
			return;
		}
		$('#role',emailResetForm).val($(':radio[id=role][checked=checked]',emailSendForm).val());
		$(emailValidateForm).ajaxSubmit({ 
			url:'login/resetPwdValidateInfo.do',
			dataType:  'json',
			success: function(result) { 
				if(result.isSuccess){
					if(pageType=='1'){
						window.location.href = 'index.do';
					}else if(pageType=='2'){
						window.location.href = 'login.html';
					}
				}else{
					$('#emailResetMsg',emailTitle).html(result.message);
				}
			}
		});
	});
});