package org.linys.action.system;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.system.Role;
import org.linys.service.system.RoleService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @Description:角色Action
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction implements ModelDriven<Role> {

	private static final long serialVersionUID = -3899336650807315718L;
	private Role model = new Role();

	@Resource
	private RoleService roleService;

	public Role getModel() {
		return model;
	}
	
	/**
	 * @Description: 保存角色
	 * @Create: 2013-1-22 上午10:33:19
	 * @author lys
	 * @update logs
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = roleService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存角色失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	
	/**
	 * @Description: 分页查询角色
	 * @Create: 2012-10-27 上午9:46:10
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void query(){
		try {
			String jsonArray = roleService.query(page, rows, model);
			ajaxJson(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 批量角色删除
	 * @Create: 2012-10-27 下午12:00:30
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void mulDelete(){
		ServiceResult result = new ServiceResult(false);	
		try {
			result = roleService.mulDelete(ids);
		} catch (Throwable e) {
			if(e instanceof org.springframework.dao.DataIntegrityViolationException){
				result.setMessage("其他模块已使用要删除的角色信息了");
			}else{
				result.setMessage("批量角色删除失败");
			}
		}
		ajaxJson(result.toJSON());
	}
	
	/**
	 * @Description: 批量修改角色状态
	 * @Created Time: 2013-2-28 下午10:57:47
	 * @Author lys
	 */
	public void mulUpdateStatus(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = roleService.mulUpdateStatus(ids,model);
		} catch (Exception e) {
			result.setMessage("批量修改角色状态失败");
			result.setIsSuccess(false);
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	/**
	 * @Description: 更新角色顺序
	 * @Create: 2013-11-27 上午08:59:51
	 * @author jcf
	 * @update logs
	 */
	public void updateArray(){
		Integer updateRoleId = getParameterInteger("updateRoleId");
		ServiceResult result = new ServiceResult(false);
		try {
			result = roleService.updateArray(model.getRoleId(),updateRoleId);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("更新角色排序失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
}
