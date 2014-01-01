package org.linys.action;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Description:Action基类
 * @Copyright: 福州骏华信息有限公司 (c)2012
 * @Created Date : 2012-10-28
 * @author lys
 * @vesion 1.0
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -3993971048496950513L;
	
	
	/**
	 * response
	 */
	protected HttpServletResponse response = ServletActionContext.getResponse();
	/**
	 * request
	 */
	protected HttpServletRequest request = ServletActionContext.getRequest();
	/**
	 * 接受从界面传递的ids
	 */
	protected String ids;
	
	protected String urlFileName;
	public String getUrlFileName() {
		return urlFileName;
	}

	public void setUrlFileName(String urlFileName) {
		this.urlFileName = urlFileName;
	}

	/**
	 * 接受从界面传递的ids2
	 */
	protected String ids2;
	/**
	 * 接受从界面传递的oldIds
	 */
	protected String oldIds;
	/**
	 * 接受jquery easyui datagrid中的page(第几页）
	 */
	protected Integer page=1;
	/**
	 * 接受jquery easyui datagrid中的rows(每页显示几行）
	 */
	protected Integer rows=20;
	/**
	 * combogrid查询时，发出的参数
	 */
	protected String q;
	/**
	 * 开始时间
	 */
	protected Date beginDate;
	/**
	 * 结束时间
	 */
	protected Date endDate;
	/**
	 * 上传文件
	 */
	protected File file;
	
	/**
	 * 上传文件的名称(根据上传文件指定的文件名称+FileName）
	 */
	protected String fileFileName;
	/**
	 * 上传文件的文件类型(根据上传文件指定的文件名称+ContentType）
	 */
	protected String fileContentType; 
	/**
	 * 主界面导出的字段
	 */
	protected String fieldsExport; 
	/**
	 * 主界面导出的字段名
	 */
	protected String fieldNamesExport; 
	/**
	 * 导出的标题
	 */
	protected String titleExport; 
	/**
	 * 结果url
	 */
	protected String resultUrl;
	/**
	 * 排序字段
	 */
	protected String sort;
	/**
	 * asc,desc
	 */
	protected String order;
	/**
	 * 
	 * @Description: 根据字符串输出JSON，返回null
	 * @Created: 2012-9-19 上午9:41:55
	 * @Author lys
	 * @Update logs
	 * @param jsonString
	 * @return
	 * @Throws Exception
	 */
	public String ajaxJson(String jsonString) {
		return ajax(jsonString, "text/plain");
	}
	
	/**
	 * @Description: 返回Html
	 * @Created Time: 2013-3-12 下午10:15:50
	 * @Author lys
	 * @param html
	 * @return
	 */
	public String ajaxHtml(String html) {
		return ajax(html, "text/html");
	}

	/**
	 * 
	 * @Description: 往前台返回某种类型的字符串结果
	 * @Created: 2012-9-19 上午9:42:15
	 * @Author lys
	 * @Update logs
	 * @param content
	 * @param type
	 * @return
	 * @Throws Exception
	 */
	public String ajax(String content, String type) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description: 往Session里面设置key-value
	 * @Created: 2012-9-19 上午9:52:36
	 * @Author lys
	 * @Update logs
	 * @param name
	 * @param value
	 * @Throws Exception
	 */
	public void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}

	/**
	 * @Description: 取得Session中的某个属性
	 * @Create: 2012-10-26 下午11:18:47
	 * @author lys
	 * @update logs
	 * @return
	 * @throws Exception
	 */
	public Object getSession(String name) {
		Map<String, Object> session = getSession();
		return session.get(name);
	}
	/**
	 * @Description: 取得Session中String类型的某个属性
	 * @Created Time: 2013-4-16 下午4:49:45
	 * @Author lys
	 * @param name
	 * @return
	 */
	public String getStringSession(String name) {
		Map<String, Object> session = getSession();
		Object obj = session.get(name);
		return obj==null?null:obj.toString();
	}
	/**
	 * @Description: 取得Session中Integer类型的某个属性
	 * @Created Time: 2013-4-16 下午4:49:45
	 * @Author lys
	 * @param name
	 * @return
	 */
	public Integer getIntegerSession(String name){
		Map<String, Object> session = getSession();
		Object obj = session.get(name);
		return obj==null?null:Integer.parseInt(obj.toString());
	}
	/**
	 * @Description: 取得Parameter中Integer类型的某个属性
	 * @Created Time: 2013-4-16 下午4:49:45
	 * @Author lys
	 * @param name
	 * @return
	 */
	public Integer getIntegerParameter(String name){
		String parameter = getParameter(name);
		return parameter==null?null:Integer.parseInt(parameter.toString());
	}
	/**
	 * @Description: 取得Session中Integer类型的某个属性
	 * @Created Time: 2013-4-16 下午4:49:45
	 * @Author lys
	 * @param name
	 * @return
	 */
	public Double getDoubleSession(String name){
		Map<String, Object> session = getSession();
		return Double.parseDouble(session.get(name).toString());
	}
	
	/**
	 * @Description: 取得Session中Timestamp类型的某个属性
	 * @Created Time: 2013-4-16 下午4:49:45
	 * @Author lys
	 * @param name
	 * @return
	 */
	public Timestamp getTimestampSession(String name){
		return (Timestamp)getSession(name);
	}
	
	/**
	 * @Description: 取得Session中Date类型的某个属性
	 * @Created Time: 2013-4-16 下午4:49:45
	 * @Author lys
	 * @param name
	 * @return
	 */
	public Date getDateSession(String name){
		Object date = getSession(name);
		return date==null?null:(Date)date;
	}

	/**
	 * @Description: 取得Session
	 * @Create: 2012-10-26 下午11:18:47
	 * @author lys
	 * @update logs
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getSession() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session;
	}

	/**
	 * @Description: 取得request
	 * @Create: 2012-10-26 下午11:19:48
	 * @author lys
	 * @update logs
	 * @return
	 * @throws Exception
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * @Description: 取得response
	 * @Create: 2012-10-26 下午11:20:03
	 * @author lys
	 * @update logs
	 * @return
	 * @throws Exception
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * @Description: 取得Parameter
	 * @Create: 2012-10-26 下午11:21:01
	 * @author lys
	 * @update logs
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public String getParameter(String name) {
		return getRequest().getParameter(name);
	}
	
	/**
	 * @Description: 取得报表模板路径
	 * @Create: 2013-2-1 下午1:51:04
	 * @author lys
	 * @update logs
	 * @return
	 */
	public String getReportTemplatePath(){
		return getRequest().getSession().getServletContext().getRealPath("/report");
	}
	/**
	 * @Description: 取得跟路径
	 * @Created Time: 2013-2-20 下午4:41:36
	 * @Author lys
	 * @return
	 */
	public String getBasePath(){
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		return basePath;
	}
	/**
	 * @Description: 取得跟路径
	 * @Created Time: 2013-2-20 下午4:41:36
	 * @Author lys
	 * @return
	 */
	public String getBaseRealPath(){
		return getRequest().getSession().getServletContext().getRealPath("/");
	}
	/**
	 * @Description: 取得跟web路径
	 * @Created: 2013-7-21 下午9:06:59
	 * @author lys
	 * @update logs
	 * @throws Exception
	 * @return
	 */
	public String getBaseWebPath(){
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getOldIds() {
		return oldIds;
	}

	public void setOldIds(String oldIds) {
		this.oldIds = oldIds;
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

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIds2() {
		return ids2;
	}

	public void setIds2(String ids2) {
		this.ids2 = ids2;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFieldsExport() {
		return fieldsExport;
	}

	public void setFieldsExport(String fieldsExport) {
		this.fieldsExport = fieldsExport;
	}

	public String getFieldNamesExport() {
		return fieldNamesExport;
	}

	public void setFieldNamesExport(String fieldNamesExport) {
		this.fieldNamesExport = fieldNamesExport;
	}

	public String getTitleExport() {
		return titleExport;
	}

	public void setTitleExport(String titleExport) {
		this.titleExport = titleExport;
	}

	public String getResultUrl() {
		return resultUrl;
	}

	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
