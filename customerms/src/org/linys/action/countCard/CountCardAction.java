package org.linys.action.countCard;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.countCard.CountCard;
import org.linys.service.countCard.CountCardService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class CountCardAction extends BaseAction implements ModelDriven<CountCard> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4856779037718052450L;
	private CountCard model  = new CountCard();

	@Resource
	private CountCardService countCardService;
	public CountCard getModel() {
		return model;
	}
	/**
	 * 计次卡保存
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = countCardService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存计次卡失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}

	/**
	 * @Description: 分页查询计次卡
	 */
	public void query(){
		try {
			String jsonArray = countCardService.query(model);
			ajaxJson(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 批量计次卡删除
	 */
	public void mulDelete(){
		ServiceResult result = new ServiceResult(false);	
		try {
			result = countCardService.mulDelete(ids);
		} catch (Throwable e) {
			if(e instanceof org.springframework.dao.DataIntegrityViolationException){
				result.setMessage("其他模块已使用要删除的计次卡信息了");
			}else{
				result.setMessage("批量计次卡删除失败");
			}
		}
		ajaxJson(result.toJSON());
	}
	

}
