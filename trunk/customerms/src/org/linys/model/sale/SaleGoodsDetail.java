package org.linys.model.sale;

import org.linys.model.BaseModel;
/**
 * @description:消费产品明细
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-5
 * @author:以宋
 * @vesion:1.0
 */
public class SaleGoodsDetail extends BaseModel{
	/**
	 * 消费产品明细Id
	 */
    private Integer saleGoodsDetailId;
    /**
	 * 消费Id
	 */
    private Integer saleId;
    /**
	 * 产品Id
	 */
    private Integer goodsId;
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
	 * 消费产品名称
	 */
    private String goodsName;
    /**
	 * 用户名称
	 */
    private String userName;

    public Integer getSaleGoodsDetailId() {
        return saleGoodsDetailId;
    }

    public void setSaleGoodsDetailId(Integer saleGoodsDetailId) {
        this.saleGoodsDetailId = saleGoodsDetailId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
}