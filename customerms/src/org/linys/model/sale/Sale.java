package org.linys.model.sale;

import org.linys.model.BaseModel;
/**
 * @description:消费
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-4
 * @author:以宋
 * @vesion:1.0
 */
public class Sale extends BaseModel{
	/**
	 * 消费Id
	 */
    private Integer saleId;
    /**
	 * 会员Id
	 */
    private Integer customerId;
    /**
	 * 录入人Id
	 */
    private Integer userId;
    /**
	 * 消费日期
	 */
    private String saleDate;
    /**
	 * 消费原价
	 */
    private Float sourceAmount;
    /**
	 * 折扣
	 */
    private Float discount;
    /**
	 * 消费金额
	 */
    private Float amount;
    /**
     * 会员名称
     */
    private String customerName;
    /**
     * 录入名
     */
    private String userName;

    public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public Float getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(Float sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}