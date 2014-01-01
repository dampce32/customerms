package org.linys.model.system;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:权限
 * @copyright:福州骏华信息有限公司 (c)2013
 * @created:2013-12-31
 * @author:以宋
 * @vesion:1.0
 */
public class Right {
	/**
	 * 权限Id
	 */
    private Integer rightId;
    /**
	 * 父权限Id
	 */
    private Integer parentRightId;
    /**
	 * 权限主键编号
	 */
    private String rightPKCode;
	/**
	 * 权限编号
	 */
    private String rightCode;
    /**
	 * 权限名称
	 */
    private String rightName;
    /**
	 * 权限Url
	 */
    private String rightUrl;
    /**
	 * 顺序
	 */
    private Integer array;
    /**
	 * 是否为叶子节点
	 */
    private Integer isLeaf;
    /**
	 * 权限类型
	 */
    private Integer rightKind;
    /**
	 * 图标
	 */
    private String iconCls;
    /**
	 * 状态
	 */
    private Integer status;
    
    private List<Right> childrenRights = new ArrayList<Right>();

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }

    public Integer getParentRightId() {
        return parentRightId;
    }

    public void setParentRightId(Integer parentRightId) {
        this.parentRightId = parentRightId;
    }

    public String getRightCode() {
        return rightCode;
    }

    public void setRightCode(String rightCode) {
        this.rightCode = rightCode;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }

    public Integer getArray() {
        return array;
    }

    public void setArray(Integer array) {
        this.array = array;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Integer getRightKind() {
        return rightKind;
    }

    public void setRightKind(Integer rightKind) {
        this.rightKind = rightKind;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getRightPKCode() {
		return rightPKCode;
	}

	public void setRightPKCode(String rightPKCode) {
		this.rightPKCode = rightPKCode;
	}

	public List<Right> getChildrenRights() {
		return childrenRights;
	}

	public void setChildrenRights(List<Right> childrenRights) {
		this.childrenRights = childrenRights;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}