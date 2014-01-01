package org.linys.action.system;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.system.RoleRight;
import org.linys.service.system.RoleRightService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @Description:角色权限Action
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-16
 * @Author lys
 */
@Controller
@Scope("prototype")
public class RoleRightAction extends BaseAction implements ModelDriven<RoleRight> {
	private static final long serialVersionUID = 5144345768368157984L;
	private RoleRight model = new RoleRight();
	@Resource
	private RoleRightService roleRightService;

	public RoleRight getModel() {
		return model;
	}
	/**
	 * @Description: 取得跟角色权限
	 * @Created Time: 2013-4-16 下午2:59:16
	 * @Author lys
	 */
	public void getRootTreeNode() {
		String jsonString = roleRightService.getRootTreeNode(model);
		ajaxJson(jsonString);
	}
	/**
	 * @Description: 取得角色权限节点下的子权限
	 * @Create: 2012-10-27 下午3:21:25
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void getChildrenTreeNode(){
		String jsonString =roleRightService.getChildrenTreeNode(model);
		ajaxJson(jsonString);
	}
	/**
	 * @description: 更新角色权限状态
	 * @created: 2014-1-1 下午10:29:53
	 * @author 以宋
	 */
	public void updateStatus(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = roleRightService.updateStatus(model);
		} catch (Exception e) {
			result.setMessage("更新角色权限状态失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
}
