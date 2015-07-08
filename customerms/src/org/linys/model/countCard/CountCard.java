package org.linys.model.countCard;

import org.linys.model.BaseModel;


/**
 * @Description:计次卡
 * @Copyright: 福州骏华信息有限公司 (c)2015
 * @Created Date : 2015-1-10
 * @author lys
 * @vesion 1.0
 */
public class CountCard extends BaseModel{
	/**
	 * 计次卡
	 */
    private Integer countCardId;
    /**
	 * 计次卡类型
	 */
    private Integer countCardTypeId;
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
     * 计次卡购买时间 
     */
    private String buyCountCardDate;
    
    /**
     * 可消费次数
     */
    private Integer canCount;

    public Integer getCountCardId() {
        return countCardId;
    }

    public void setCountCardId(Integer countCardId) {
        this.countCardId = countCardId;
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

	public Integer getCountCardTypeId() {
		return countCardTypeId;
	}

	public void setCountCardTypeId(Integer countCardTypeId) {
		this.countCardTypeId = countCardTypeId;
	}

	public String getCountCardTypeName() {
		return countCardTypeName;
	}

	public void setCountCardTypeName(String countCardTypeName) {
		this.countCardTypeName = countCardTypeName;
	}

	public String getBuyCountCardDate() {
		return buyCountCardDate;
	}

	public void setBuyCountCardDate(String buyCountCardDate) {
		this.buyCountCardDate = buyCountCardDate;
	}

	public Integer getCanCount() {
		return canCount;
	}

	public void setCanCount(Integer canCount) {
		this.canCount = canCount;
	}

}