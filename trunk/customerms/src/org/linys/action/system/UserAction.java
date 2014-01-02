package org.linys.action.system;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.system.User;
import org.linys.service.system.UserService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @Description:用户Action
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction implements ModelDriven<User> {

	private static final long serialVersionUID = -3899336650807315718L;
	private User model = new User();
	@Resource
	private UserService userService;

	public User getModel() {
		return model;
	}
	/**
	 * @Description: 保存用户
	 * @Create: 2013-1-22 上午10:33:19
	 * @author lys
	 * @update logs
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = userService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存用户失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	
	/**
	 * @Description: 分页查询用户
	 * @Create: 2012-10-27 上午9:46:10
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void query(){
		try {
			String jsonArray = userService.query(page, rows, model);
			ajaxJson(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 批量用户删除
	 * @Create: 2012-10-27 下午12:00:30
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void mulDelete(){
		ServiceResult result = new ServiceResult(false);	
		try {
			result = userService.mulDelete(ids);
		} catch (Throwable e) {
			if(e instanceof org.springframework.dao.DataIntegrityViolationException){
				result.setMessage("其他模块已使用要删除的用户信息了");
			}else{
				result.setMessage("批量用户删除失败");
			}
		}
		ajaxJson(result.toJSON());
	}
	
	/**
	 * @Description: 批量修改用户状态
	 * @Created Time: 2013-2-28 下午10:57:47
	 * @Author lys
	 */
	public void mulUpdateStatus(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = userService.mulUpdateStatus(ids,model);
		} catch (Exception e) {
			result.setMessage("批量修改用户状态失败");
			result.setIsSuccess(false);
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	/**
	 * @Description: 退出系统
	 * @Create: 2012-11-15 上午11:07:41
	 * @author lys
	 * @update logs
	 */
	public void logout(){
		getSession().clear();
		ServiceResult result = new ServiceResult(true);
		ajaxJson(result.toJSON());
	}
}
