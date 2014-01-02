package org.linys.filter;

import java.util.Map;

import org.linys.model.system.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @Description:用户登录拦截器
 * 在要访问用户可访问资源前，判断用户是否已登录
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-3-29
 * @Author lys
 */
public class UserLoginVerifyInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -86246303854807787L;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object > session =  invocation.getInvocationContext().getSession();
		
		Integer userLogin = (Integer) session.get(User.LOGIN_USERID);
		if (userLogin == null) {
			return "loginUser";
		}
		return invocation.invoke();
	}

}