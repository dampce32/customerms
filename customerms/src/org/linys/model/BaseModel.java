package org.linys.model;

public class BaseModel {
	/**
	 * ids
	 */
	protected Integer [] idArray;
	/**
	 * 开始日期
	 */
	protected String beginDate;
	/**
	 * 结束日期
	 */
	protected String endDate;
	
	/**
	 * 接受jquery easyui datagrid中的page(第几页）
	 */
	protected Integer page=1;
	/**
	 * 接受jquery easyui datagrid中的rows(每页显示几行）
	 */
	protected Integer rows=20;
	
	/**
	 * 从第几行开始显示
	 */
	protected Integer start;
	
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
	public Integer[] getIdArray() {
		return idArray;
	}
	public void setIdArray(Integer[] idArray) {
		this.idArray = idArray;
	}
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getStart() {
		return  (page*rows-rows);
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	
	
	
	
}
