package org.linys.model.dict;
/**
 * @description:消费项目
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public class SaleItem {
	/**
	 * 消费项目Id
	 */
    private Integer saleItemId;
    /**
	 * 消费项目名称
	 */
    private String saleItemName;
    /**
	 * 消费金额
	 */
    private Float amount;
    /**
	 * 是否参与打折
	 */
    private Integer isDiscount;

    public Integer getSaleItemId() {
        return saleItemId;
    }

    public void setSaleItemId(Integer saleItemId) {
        this.saleItemId = saleItemId;
    }

    public String getSaleItemName() {
        return saleItemName;
    }

    public void setSaleItemName(String saleItemName) {
        this.saleItemName = saleItemName;
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
}