package org.linys.util;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;


/**
 * @Description:共用的工具类
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-1-13
 * @author lys
 * @vesion 1.0
 */
public class CommonUtil {
	/**
	 * @Description: 删除基础字典的json文件
	 * @Create: 2013-10-31 上午12:31:38
	 * @author lys
	 * @update logs
	 * @param fileName
	 */
	public static void deleteDictJsonFile(String dictJsonFileName){
		ServletContext servletContext = ServletActionContext.getServletContext();
		File jsonFile = new File(servletContext.getRealPath("/WEB-INF/dictJson/"+dictJsonFileName+".json"));
		if(jsonFile.exists()){
			jsonFile.delete();
		}
	}
	
}
