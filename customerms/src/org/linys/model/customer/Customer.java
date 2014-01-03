package org.linys.model.customer;
/**
 * @description:会员
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-4
 * @author:以宋
 * @vesion:1.0
 */
public class Customer {
	/**
	 * 会员Id
	 */
    private Integer customerId;
    /**
	 * 会员类型Id
	 */
    private Integer customerTypeId;
    /**
	 * 会员号
	 */
    private String customerCode;
    /**
	 * 姓名
	 */
    private String customerName;
    /**
	 * 电话
	 */
    private String telephone;
    /**
	 * 微信
	 */
    private String wechat;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
}