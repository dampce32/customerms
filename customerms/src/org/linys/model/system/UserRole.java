package org.linys.model.system;
/**
 * @description:用户角色
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-2
 * @author:以宋
 * @vesion:1.0
 */
public class UserRole {
	/**
	 * 用户角色Id
	 */
    private Integer userRoleId;
    /**
	 * 角色Id
	 */
    private Integer roleId;
    /**
	 * 用户Id
	 */
    private Integer userId;

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}