package org.linys.action.dict;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.dict.SaleItem;
import org.linys.service.dict.SaleItemService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @Description:消费项目Action
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
@Controller
@Scope("prototype")
public class SaleItemAction extends BaseAction implements ModelDriven<SaleItem> {

	private static final long serialVersionUID = -3899336650807315718L;
	private SaleItem model = new SaleItem();

	@Resource
	private SaleItemService saleItemService;

	public SaleItem getModel() {
		return model;
	}
	
	/**
	 * @Description: 保存消费项目
	 * @Create: 2013-1-22 上午10:33:19
	 * @author lys
	 * @update logs
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = saleItemService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存消费项目失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	
	/**
	 * @Description: 分页查询消费项目
	 * @Create: 2012-10-27 上午9:46:10
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void query(){
		try {
			String jsonArray = saleItemService.query(page, rows, model);
			ajaxJson(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 批量消费项目删除
	 * @Create: 2012-10-27 下午12:00:30
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void mulDelete(){
		ServiceResult result = new ServiceResult(false);	
		try {
			result = saleItemService.mulDelete(ids);
		} catch (Throwable e) {
			if(e instanceof org.springframework.dao.DataIntegrityViolationException){
				result.setMessage("其他模块已使用要删除的消费项目信息了");
			}else{
				result.setMessage("批量消费项目删除失败");
			}
		}
		ajaxJson(result.toJSON());
	}
	/**
	 * @description: 添加查询消费项目
	 * @created: 2014-1-4 下午4:43:52
	 * @author 以宋
	 */
	public void querySelect(){
		String jsonArray = saleItemService.querySelect(page, rows, model,ids);
		ajaxJson(jsonArray);
	}
}
