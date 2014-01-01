package org.linys.action;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
/**
 * @Description:一些共用的方法，比如文件下载什么的
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-8-14
 * @author jcf
 * @vesion 1.0
 */
@Controller
@Scope("prototype")
public class CommonAction extends BaseAction{
	private static final long serialVersionUID = 5144345768368157984L;
	/**
	 * 文件路径
	 */
	private String filePath;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * @Description: 返回Tab
	 * @Create: 2013-12-10 上午10:03:49
	 * @author jcf
	 * @update logs
	 */
	public void getTabPanel(){
		ServletContext servletContext = ServletActionContext.getServletContext();
		String html = getParameter("html");
//		String rightId = getParameter("rightId");
		File htmlFile = new File(servletContext.getRealPath("/WEB-INF/"+html));
		String htmlString = null;
		try {
			if(htmlFile.exists()){
				htmlString = FileUtils.readFileToString(htmlFile,"UTF-8");
//				List<Map<String,Object>> rights = null;
//				Map<String,Object> reportRightMap = null;
//				if(StringUtils.isEmpty(roleId)){
//					rights = teacherDAO.getChildrenRight(getStringSession(Teacher.LOGIN_ID), rightId, 2);
//					reportRightMap = getTeaReportRight(rights);
//					if(reportRightMap!=null){
//						List<Map<String,Object>> reportRights = teacherDAO.getChildrenRight(getStringSession(Teacher.LOGIN_ID), reportRightMap.get("rightId").toString(), 3);
//						htmlString = htmlString.replaceAll("\\$\\{reportRights\\}", JSONUtil.toJsonFromListMapWithOutRows(reportRights));
//					}else{
//						htmlString = htmlString.replaceAll("\\$\\{reportRights\\}", "[]");
//					}
//					htmlString = htmlString.replaceAll("\\$\\{rights\\}", JSONUtil.toJsonFromListMapWithOutRows(rights));
//				}else {
//					rights = teacherDAO.getChildrenRightByKind(roleId, rightId, 2);
//					reportRightMap = getTeaReportRight(rights);
//					if(reportRightMap!=null){
//						List<Map<String,Object>> reportRights = teacherDAO.queryChildrenReportRight(roleId,reportRightMap.get("rightId").toString());
//						htmlString = htmlString.replaceAll("\\$\\{reportRights\\}", JSONUtil.toJsonFromListMapWithOutRows(reportRights));
//					}else{
//						htmlString = htmlString.replaceAll("\\$\\{reportRights\\}", "[]");
//					}
//					htmlString = htmlString.replaceAll("\\$\\{rights\\}", JSONUtil.toJsonFromListMapWithOutRows(rights));
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ajaxHtml(htmlString);
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
