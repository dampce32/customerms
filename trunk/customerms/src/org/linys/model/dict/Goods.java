package org.linys.model.dict;
/**
 * @description:产品
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
public class Goods {
	/**
	 * 产品Id
	 */
    private Integer goodsId;
    /**
	 * 产品名称
	 */
    private String goodsName;
    /**
	 * 消费金额
	 */
    private Float amount;
    /**
	 * 是否打折
	 */
    private Integer isDiscount;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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