package org.linys.action.system;

import java.util.List;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.system.Right;
import org.linys.service.system.RightService;
import org.linys.util.TreeUtil;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @Description:权限Action
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-16
 * @Author lys
 */
@Controller
@Scope("prototype")
public class RightAction extends BaseAction implements ModelDriven<Right> {
	private static final long serialVersionUID = 5144345768368157984L;
	private Right model = new Right();
	@Resource
	private RightService rightService;

	public Right getModel() {
		return model;
	}
	/**
	 * @Description: 取得跟权限
	 * @Created Time: 2013-4-16 下午2:59:16
	 * @Author lys
	 */
	public void getRootTreeNode() {
		String jsonString = rightService.getRootTreeNode();
		ajaxJson(jsonString);
	}
	/**
	 * @Description: 单击选择展开树节点
	 * @Create: 2012-10-27 下午3:21:25
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void getChildrenTreeNode(){
		List<Right> childrenTreeNode=rightService.getChildrenTreeNode(model);
		String jsonString = TreeUtil.toJSONRightList(childrenTreeNode);
		ajaxJson(jsonString);
	}
	
	/**
	 * @Description: 查询节点下的孩子节点
	 * @Create: 2012-10-27 上午9:46:10
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void queryChildren(){
		String jsonArray = rightService.queryChildren(page, rows, model);
		ajaxJson(jsonArray);
	}
	
	/**
	 * @Description: 保存权限
	 * @Create: 2013-1-22 上午10:33:19
	 * @author lys
	 * @update logs
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = rightService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存权限失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	
	/**
	 * @Description: 批量权限删除
	 * @Create: 2012-10-27 下午12:00:30
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void mulDelete(){
		ServiceResult result = new ServiceResult(false);	
		try {
			result = rightService.mulDelete(ids);
		} catch (Throwable e) {
			e.printStackTrace();
			result.setMessage("批量权限删除失败");
		}
		ajaxJson(result.toJSON());
	}
	/**
	 * @Description: 批量修改权限状态
	 * @Created Time: 2013-2-28 下午10:57:47
	 * @Author lys
	 */
	public void mulUpdateStatus(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = rightService.mulUpdateStatus(ids,model);
		} catch (Exception e) {
			result.setMessage("批量修改权限状态失败");
			result.setIsSuccess(false);
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	/**
	 * @Description: 拖拽改变权限排序
	 * @Created: 2013-8-6 下午3:31:48
	 * @Author lys
	 */
	public void dropUpdateArray(){
		String sourceId = getParameter("sourceId");
		String targetId = getParameter("targetId");
		String point = getParameter("point");
		ServiceResult result = new ServiceResult(false);
		try {
			result = rightService.dropUpdateArray(sourceId,targetId,point);
		} catch (Throwable e) {
			e.printStackTrace();
			result.setMessage("拖拽改变权限排序失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
}
