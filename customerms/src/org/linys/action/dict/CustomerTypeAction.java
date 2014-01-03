package org.linys.action.dict;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.dict.CustomerType;
import org.linys.service.dict.CustomerTypeService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @Description:会员类型Action
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
@Controller
@Scope("prototype")
public class CustomerTypeAction extends BaseAction implements ModelDriven<CustomerType> {

	private static final long serialVersionUID = -3899336650807315718L;
	private CustomerType model = new CustomerType();

	@Resource
	private CustomerTypeService customerTypeService;

	public CustomerType getModel() {
		return model;
	}
	
	/**
	 * @Description: 保存会员类型
	 * @Create: 2013-1-22 上午10:33:19
	 * @author lys
	 * @update logs
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = customerTypeService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存会员类型失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	
	/**
	 * @Description: 分页查询会员类型
	 * @Create: 2012-10-27 上午9:46:10
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void query(){
		try {
			String jsonArray = customerTypeService.query(page, rows, model);
			ajaxJson(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 批量会员类型删除
	 * @Create: 2012-10-27 下午12:00:30
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void mulDelete(){
		ServiceResult result = new ServiceResult(false);	
		try {
			result = customerTypeService.mulDelete(ids);
		} catch (Throwable e) {
			if(e instanceof org.springframework.dao.DataIntegrityViolationException){
				result.setMessage("其他模块已使用要删除的会员类型信息了");
			}else{
				result.setMessage("批量会员类型删除失败");
			}
		}
		ajaxJson(result.toJSON());
	}
}
