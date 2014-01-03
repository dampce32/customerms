package org.linys.model.dict;
/**
 * @description:会员类型
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public class CustomerType {
	/**
	 * 会员类型Id
	 */
    private Integer customerTypeId;
    /**
	 * 会员类型名称
	 */
    private String customerTypeName;
    /**
	 * 折扣
	 */
    private Float discount;

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }
}