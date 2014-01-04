package org.linys.action.sale;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.sale.Sale;
import org.linys.service.sale.SaleService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @Description:消费Action
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
@Controller
@Scope("prototype")
public class SaleAction extends BaseAction implements ModelDriven<Sale> {

	private static final long serialVersionUID = -3899336650807315718L;
	private Sale model = new Sale();

	@Resource
	private SaleService saleService;

	public Sale getModel() {
		return model;
	}
	
	/**
	 * @Description: 保存消费
	 * @Create: 2013-1-22 上午10:33:19
	 * @author lys
	 * @update logs
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = saleService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存消费失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	
	/**
	 * @Description: 分页查询消费
	 * @Create: 2012-10-27 上午9:46:10
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void query(){
		try {
			String jsonArray = saleService.query(page, rows, model);
			ajaxJson(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 批量消费删除
	 * @Create: 2012-10-27 下午12:00:30
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void mulDelete(){
		ServiceResult result = new ServiceResult(false);	
		try {
			result = saleService.mulDelete(ids);
		} catch (Throwable e) {
			if(e instanceof org.springframework.dao.DataIntegrityViolationException){
				result.setMessage("其他模块已使用要删除的消费信息了");
			}else{
				result.setMessage("批量消费删除失败");
			}
		}
		ajaxJson(result.toJSON());
	}
}
