package org.linys.model.system;
/**
 * @description:角色权限
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-1
 * @author:以宋
 * @vesion:1.0
 */
public class RoleRight {
	/**
	 * 角色权限Id
	 */
    private Integer roleRightId;
    /**
	 * 权限Id
	 */
    private Integer rightId;
    /**
	 * 角色Id
	 */
    private Integer roleId;
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

	public Integer getRoleRightId() {
        return roleRightId;
    }

    public void setRoleRightId(Integer roleRightId) {
        this.roleRightId = roleRightId;
    }

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}