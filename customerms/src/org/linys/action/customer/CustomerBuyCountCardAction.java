package org.linys.action.customer;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.customer.CustomerBuyCountCard;
import org.linys.service.customer.CustomerBuyCountCardService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class CustomerBuyCountCardAction  extends BaseAction implements ModelDriven<CustomerBuyCountCard>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7845895866206045007L;

	@Resource
	private CustomerBuyCountCardService customerBuyCountCardService;
	
	private CustomerBuyCountCard model = new CustomerBuyCountCard();
	
	public CustomerBuyCountCard getModel() {
		return model;
	}

	/**
	 * 保存会员购买计次卡
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = customerBuyCountCardService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存会员购买计次卡失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	
	/**
	 * 分页查询该会员购买计次卡历史
	 */
	public void query(){
		String jsonArray = customerBuyCountCardService.query(model);
		ajaxJson(jsonArray);
	}
	
	/**
	 * 分页查询会员购买计次卡
	 */
	public void queryCustomer(){
		String jsonArray = customerBuyCountCardService.queryCustomerBuyCountCard(model);
		ajaxJson(jsonArray);
	}
}
