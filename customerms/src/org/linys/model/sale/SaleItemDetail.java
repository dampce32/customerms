package org.linys.model.sale;

import org.linys.model.BaseModel;

/**
 * @description:消费项目明细
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-4
 * @author:以宋
 * @vesion:1.0
 */
public class SaleItemDetail extends BaseModel{
	/**
	 * 消费项目明细Id
	 */
    private Integer saleItemDetailId;
    /**
	 * 消费Id
	 */
    private Integer saleId;
    /**
	 * 消费项目Id
	 */
    private Integer saleItemId;
    /**
	 * 技师Id
	 */
    private Integer userId;
    /**
	 * 消费金额
	 */
    private Float amount;
    /**
	 * 是否打折
	 */
    private Integer isDiscount;
    /**
	 * 消费项目名称
	 */
    private String saleItemName;
    /**
	 * 用户名称
	 */
    private String userName;

    public Integer getSaleItemDetailId() {
        return saleItemDetailId;
    }

    public void setSaleItemDetailId(Integer saleItemDetailId) {
        this.saleItemDetailId = saleItemDetailId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getSaleItemId() {
        return saleItemId;
    }

    public void setSaleItemId(Integer saleItemId) {
        this.saleItemId = saleItemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getIsDiscount() {
        return isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

	public String getSaleItemName() {
		return saleItemName;
	}

	public void setSaleItemName(String saleItemName) {
		this.saleItemName = saleItemName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}