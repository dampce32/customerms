package org.linys.action.dict;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.dict.DataDict;
import org.linys.service.dict.DataDictService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @Description:数据字典Action
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
@Controller
@Scope("prototype")
public class DataDictAction extends BaseAction implements ModelDriven<DataDict> {

	private static final long serialVersionUID = -3899336650807315718L;
	private DataDict model = new DataDict();

	@Resource
	private DataDictService dataDictService;

	public DataDict getModel() {
		return model;
	}
	
	/**
	 * @Description: 保存数据字典
	 * @Create: 2013-1-22 上午10:33:19
	 * @author lys
	 * @update logs
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = dataDictService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存数据字典失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	
	/**
	 * @Description: 分页查询数据字典
	 * @Create: 2012-10-27 上午9:46:10
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void query(){
		try {
			String jsonArray = dataDictService.query(page, rows, model);
			ajaxJson(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 批量数据字典删除
	 * @Create: 2012-10-27 下午12:00:30
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void mulDelete(){
		ServiceResult result = new ServiceResult(false);	
		try {
			result = dataDictService.mulDelete(ids);
		} catch (Throwable e) {
			if(e instanceof org.springframework.dao.DataIntegrityViolationException){
				result.setMessage("其他模块已使用要删除的数据字典信息了");
			}else{
				result.setMessage("批量数据字典删除失败");
			}
		}
		ajaxJson(result.toJSON());
	}
	/**
	 * @description: 添加查询数据字典
	 * @created: 2014-1-4 下午4:43:52
	 * @author 以宋
	 */
	public void querySelect(){
		String jsonArray = dataDictService.querySelect(page, rows, model,ids);
		ajaxJson(jsonArray);
	}
	/**
	 * @Description: combobox查询
	 * @Created: 2015-1-10 上午9:04:09
	 * @author lys
	 */
	public void queryCombobox() {
		try {
			String jsonString = dataDictService.queryCombobox(model);
			ajaxJson(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
