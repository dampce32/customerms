package org.linys.model.customer;
/**
 * @description:会员充值
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-10
 * @author:以宋
 * @vesion:1.0
 */
public class CustomerRecharge {
	/**
	 * 会员充值Id
	 */
    private Integer customerRechargeId;
    /**
	 * 会员Id
	 */
    private Integer customerId;
    /**
	 * 充值日期
	 */
    private String rechargeDate;
    /**
	 * 充值金额
	 */
    private Float amount;

    public Integer getCustomerRechargeId() {
        return customerRechargeId;
    }

    public void setCustomerRechargeId(Integer customerRechargeId) {
        this.customerRechargeId = customerRechargeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(String rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}