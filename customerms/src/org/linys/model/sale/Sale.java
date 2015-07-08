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
	 * 不打折金额
	 */
    private Float notIntoDiscountAmount;
    /**
	 * 打折金额
	 */
    private Float intoDiscountAmount;
    /**
     * 付款类型
     */
    private String payType;
	/**
	 * 折扣
	 */
    private Float discount;
    /**
	 * 消费金额
	 */
    private Float amount;
    /**
     * 消费余额
     */
    private Float balance;
    /**
     * 现金付款
     */
    private Float payByCash;
    /**
     * 会员卡付款
     */
    private Float payByCard;
	/**
     * 会员名称
     */
    private String customerName;
    /**
     * 会员卡余额
     */
    private Float customerAmount;
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
    
    public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Float getNotIntoDiscountAmount() {
		return notIntoDiscountAmount;
	}

	public void setNotIntoDiscountAmount(Float notIntoDiscountAmount) {
		this.notIntoDiscountAmount = notIntoDiscountAmount;
	}

	public Float getIntoDiscountAmount() {
		return intoDiscountAmount;
	}

	public void setIntoDiscountAmount(Float intoDiscountAmount) {
		this.intoDiscountAmount = intoDiscountAmount;
	}

	public Float getCustomerAmount() {
		return customerAmount;
	}

	public void setCustomerAmount(Float customerAmount) {
		this.customerAmount = customerAmount;
	}
	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public Float getPayByCash() {
		return payByCash;
	}

	public void setPayByCash(Float payByCash) {
		this.payByCash = payByCash;
	}

	public Float getPayByCard() {
		return payByCard;
	}

	public void setPayByCard(Float payByCard) {
		this.payByCard = payByCard;
	}
}