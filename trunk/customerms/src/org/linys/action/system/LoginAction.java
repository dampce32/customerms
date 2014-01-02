package org.linys.action.system;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.linys.action.BaseAction;
import org.linys.model.system.User;
import org.linys.service.system.UserService;
import org.linys.util.MD5Util;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
/**
 * @Description:用户登录
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-3-29
 * @Author lys
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = -1379893197712674493L;
	
	private String userCode;
	private String userPwd;
	@Resource
	private UserService userService;

	/**
	 * @Description: 用户端登录
	 * @Created Time: 2013-3-29 上午11:29:36
	 * @Author lys
	 */
	public void userLogin(){
		try {
			ServiceResult result = new ServiceResult(false);
			String captchaId = getStringSession("rand");
			String challengeResponse = getParameter("loginCaptcha");
			if(StringUtils.isEmpty(challengeResponse) || !captchaId.equals(challengeResponse)){
				result.setMessage("验证码错误");
				ajaxJson(result.toJSON());
				return;
			}
			//根据用户名和密码判断是否允许登录
			User loginUser = userService.login(userCode,MD5Util.getMD5(userPwd));
			if (null == loginUser) {
				result.setMessage("用户名或密码错误!!");
			}else{
				if(loginUser.getStatus().intValue()==0){
					result.setMessage("对不起，您的帐号已被禁用，请及时联系管理员");
					String ajaxString = result.toJSON();
					ajaxJson(ajaxString);
					return;
				}
				setSession(User.LOGIN_USERID,loginUser.getUserId());
				setSession(User.LOGIN_USERNAME,loginUser.getUserName());
				result.addData("userId", loginUser.getUserId());
				result.addData("userName", loginUser.getUserName());
				result.setIsSuccess(true);
			}
			String ajaxString = result.toJSON();
			ajaxJson(ajaxString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 初始化对外网站用户登录
	 * @Created: 2013-9-24 下午2:10:05
	 * @Author lys
	 */
	public void init(){
		try {
			ServiceResult result = new ServiceResult(false);
			result.addData("userId", getSession(User.LOGIN_USERID));
			result.addData("userName", getSession(User.LOGIN_USERNAME));
			result.setIsSuccess(true);
			String ajaxString = result.toJSON();
			ajaxJson(ajaxString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public String getUserPwd() {
		return userPwd;
	}


	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

}
