package org.linys.action.sale;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.sale.SaleBuyCountCard;
import org.linys.service.sale.SaleBuyCountCardService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 计次卡消费管理
 * @author wxy530
 *
 */
@Controller
@Scope("prototype")
public class SaleBuyCountCardAction extends BaseAction 
	implements ModelDriven<SaleBuyCountCard>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2048428493772475836L;
	private SaleBuyCountCard model = new SaleBuyCountCard();
	
	@Resource
	private SaleBuyCountCardService saleBuyCountCardService;

	public SaleBuyCountCard getModel() {
		return model;
	}
	
	/**
	 * 分页查询会员计次卡消费记录
	 * @param model
	 */
	public void query(){
		try{
			String jsonArray = saleBuyCountCardService.query(model);
		    ajaxJson(jsonArray);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存用户消费计次卡次数
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try{
			model.setCanCount(model.getCanCount()-model.getSaleCount());
			result = saleBuyCountCardService.save(model);
			saleBuyCountCardService.update(model);
		}catch(Exception e){
			e.printStackTrace();
			result.setMessage("保存用户消费计次卡次数失败");
		}
		 String jsonArray = result.toJSON();
		 ajaxJson(jsonArray);
	}

	public void delete(){
		ServiceResult result = new ServiceResult(false);
		try{
			model.setSaleCount(-model.getSaleCount());
			saleBuyCountCardService.update(model);
			result = saleBuyCountCardService.delete(model.getSaleBuyCountCardId());
		}catch(Exception e){
			e.printStackTrace();
			result.setMessage("删除用户消费计次卡次数失败");
		}
		 String jsonArray = result.toJSON();
		 ajaxJson(jsonArray);
	}
}
