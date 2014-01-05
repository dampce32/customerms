package org.linys.model;

public class BaseModel {
	/**
	 * ids
	 */
	protected Integer [] idArray;
	public Integer[] getIdArray() {
		return idArray;
	}
	public void setIdArray(Integer[] idArray) {
		this.idArray = idArray;
	}
	/**
	 * 开始日期
	 */
	protected String beginDate;
	/**
	 * 结束日期
	 */
	protected String endDate;
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
