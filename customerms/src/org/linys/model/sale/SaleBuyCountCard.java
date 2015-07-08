package org.linys.model.sale;

import org.linys.model.BaseModel;
/**
 * 消费购买的计次卡
 * @author wxy530
 *
 */
public class SaleBuyCountCard extends BaseModel {

	/**
	 * 消费购买的计次卡Id
	 */
	private Integer saleBuyCountCardId;
	
	/**
	 * 录入人Id
	 */
	private Integer userId;
	
	/**
	 * 购买计次卡Id
	 */
	private Integer customerBuyCountCardId;
	
	/**
	 * 销售日期
	 */
	private String saleDate;
	
	/**
	 * 消费次数
	 */
	private Integer saleCount;
	
	/**
	 * 剩余次数
	 */
	private Integer canCount;
	
	/**
	 * 已用次数
	 */
	private Integer alreadySaleCount;
	
	/**
	 * 会员号
	 */
	private String customerCode;
	
	/**
	 * 会员名称
	 */
	private String customerName;
	
	/**
	 * 档次
	 */
	private Integer amount;
	
	/**
	 * 购买次数
	 */
    private Integer canSaleCount;
    
    /**
     * 计次卡名称
     */
    private String countCardTypeName;
    
    /**
     * 录入员
     */
    private String userName;

	public Integer getSaleBuyCountCardId() {
		return saleBuyCountCardId;
	}

	public void setSaleBuyCountCardId(Integer saleBuyCountCardId) {
		this.saleBuyCountCardId = saleBuyCountCardId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCustomerBuyCountCardId() {
		return customerBuyCountCardId;
	}

	public void setCustomerBuyCountCardId(Integer customerBuyCountCardId) {
		this.customerBuyCountCardId = customerBuyCountCardId;
	}

	public Integer getSaleCount() {
		return saleCount;
	}

	public void setSaleCount(Integer saleCount) {
		this.saleCount = saleCount;
	}

	public Integer getCanCount() {
		return canCount;
	}

	public void setCanCount(Integer canCount) {
		this.canCount = canCount;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public Integer getAlreadySaleCount() {
		return alreadySaleCount;
	}

	public void setAlreadySaleCount(Integer alreadySaleCount) {
		this.alreadySaleCount = alreadySaleCount;
	}
	
	
}
