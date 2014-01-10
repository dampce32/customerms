package org.linys.action.customer;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.customer.CustomerRecharge;
import org.linys.service.customer.CustomerRechargeService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @Description:会员充值充值Action
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
@Controller
@Scope("prototype")
public class CustomerRechargeAction extends BaseAction implements ModelDriven<CustomerRecharge> {

	private static final long serialVersionUID = -3899336650807315718L;
	private CustomerRecharge model = new CustomerRecharge();

	@Resource
	private CustomerRechargeService customerRechargeService;

	public CustomerRecharge getModel() {
		return model;
	}
	
	/**
	 * @Description: 保存会员充值充值
	 * @Create: 2013-1-22 上午10:33:19
	 * @author lys
	 * @update logs
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = customerRechargeService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存会员充值充值失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	/**
	 * @description: 分页查询会员充值历史
	 * @created: 2014-1-10 下午10:01:13
	 * @author 以宋
	 */
	public void query(){
		String jsonArray = customerRechargeService.query(page, rows, model);
		ajaxJson(jsonArray);
	}
}
