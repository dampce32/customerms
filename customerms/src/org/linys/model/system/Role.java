package org.linys.model.system;
/**
 * @description:角色
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-1
 * @author:以宋
 * @vesion:1.0
 */
public class Role {
	/**
	 * 角色Id
	 */
    private Integer roleId;
    /**
	 * 角色编号
	 */
    private String roleCode;
    /**
	 * 角色名称
	 */
    private String roleName;
    /**
	 * 排序
	 */
    private Integer array;
    /**
	 * 状态
	 */
    private Integer status;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getArray() {
        return array;
    }

    public void setArray(Integer array) {
        this.array = array;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}