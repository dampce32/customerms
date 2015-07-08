package org.linys.model.customer;

import org.linys.model.BaseModel;

/**
 * 购买计次卡
 * @author wxy530
 *
 */
public class CustomerBuyCountCard extends BaseModel{

	/**
	 * 购买计次卡Id
	 */
	private Integer customerBuyCountCardId;
	
	/**
	 * 会员Id 
	 */
	private Integer customerId;
	
	/**
	 * 计次卡Id
	 */
	private Integer countCardId;
	
	/**
	 * 计次卡已消费次数
	 */
	private Integer alreadySaleCount;
	
	/**
	 * 购买计次卡日期 
	 */
	private String buyCountCardDate;
	
	/**
	 * 档次
	 */
    private Double amount;
    /**
	 * 购买消费次数
	 */
    private Integer canSaleCount;
    
    /**
     *  计次卡名称
     */
    private String countCardTypeName;
    
    /**
     * 会员号
     */
    private String customerCode;
    
    /**
     * 会员名称
     */
    private String customerName;
    
    /**
     * 可消费次数
     */
    private Integer canCount;
	
	
	public Integer getCustomerBuyCountCardId() {
		return customerBuyCountCardId;
	}

	public void setCustomerBuyCountCardId(Integer customerBuyCountCardId) {
		this.customerBuyCountCardId = customerBuyCountCardId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getCountCardId() {
		return countCardId;
	}

	public void setCountCardId(Integer countCardId) {
		this.countCardId = countCardId;
	}

	public String getBuyCountCardDate() {
		return buyCountCardDate;
	}

	public void setBuyCountCardDate(String buyCountCardDate) {
		this.buyCountCardDate = buyCountCardDate;
	}

	public Integer getAlreadySaleCount() {
		return alreadySaleCount;
	}

	public void setAlreadySaleCount(Integer alreadySaleCount) {
		this.alreadySaleCount = alreadySaleCount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getCanSaleCount() {
		return canSaleCount;
	}

	public void setCanSaleCount(Integer canSaleCount) {
		this.canSaleCount = canSaleCount;
	}

	public String getCountCardTypeName() {
		return countCardTypeName;
	}

	public void setCountCardTypeName(String countCardTypeName) {
		this.countCardTypeName = countCardTypeName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCanCount() {
		return canCount;
	}

	public void setCanCount(Integer canCount) {
		this.canCount = canCount;
	}

	
}
