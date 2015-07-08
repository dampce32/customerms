package org.linys.model.dict;

import org.linys.model.BaseModel;

/**
 * @Description:数据字典
 * @Copyright: 福州骏华信息有限公司 (c)2015
 * @Created Date : 2015-1-9
 * @author lys
 * @vesion 1.0
 */
public class DataDict extends BaseModel{
	/**
	 * 数据字典Id
	 */
    private Integer dataDictId;
    /**
	 * 字典类型
	 */
    private String dataDictType;
    /**
	 * 字典编号
	 */
    private String dataDictCode;
    /**
	 * 字典名称
	 */
    private String dataDictName;
    /**
	 * 排序
	 */
    private Integer array;

    public Integer getDataDictId() {
        return dataDictId;
    }

    public void setDataDictId(Integer dataDictId) {
        this.dataDictId = dataDictId;
    }

    public String getDataDictType() {
        return dataDictType;
    }

    public void setDataDictType(String dataDictType) {
        this.dataDictType = dataDictType;
    }

    public String getDataDictCode() {
        return dataDictCode;
    }

    public void setDataDictCode(String dataDictCode) {
        this.dataDictCode = dataDictCode;
    }

    public String getDataDictName() {
        return dataDictName;
    }

    public void setDataDictName(String dataDictName) {
        this.dataDictName = dataDictName;
    }

    public Integer getArray() {
        return array;
    }

    public void setArray(Integer array) {
        this.array = array;
    }
}