package org.linys.model.system;
/**
 * @description:系统使用者
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-2
 * @author:以宋
 * @vesion:1.0
 */
public class User {
	/**
	 * 登录用户Id
	 */
	public static final String LOGIN_USERID = "loginUserId";
	/**
	 * 登录用户名称
	 */
	public static final String LOGIN_USERNAME = "loginUserName";
	/**
	 * 用户Id
	 */
    private Integer userId;
    /**
	 * 用户编号（登录名）
	 */
    private String userCode;
    /**
	 * 用户名
	 */
    private String userName;
    /**
	 * 登录密码
	 */
    private String passwords;
    /**
     * 状态
     */
    private Integer status;

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
}