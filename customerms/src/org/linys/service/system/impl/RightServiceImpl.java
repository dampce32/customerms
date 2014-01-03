package org.linys.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.linys.dao.system.RightDAO;
import org.linys.dao.system.RoleRightDAO;
import org.linys.model.system.Right;
import org.linys.service.system.RightService;
import org.linys.util.JSONUtil;
import org.linys.util.StringUtil;
import org.linys.util.TreeUtil;
import org.linys.vo.GlobalConstants;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @Description:权限Service实现类
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-16
 * @Author lys
 */
@Service
public class RightServiceImpl implements RightService {
	@Resource
	private RightDAO rightDAO;
//	@Resource
//	private RoleDAO roleDAO;
	@Resource
	private RoleRightDAO roleRightDAO;
	public String getRootTreeNode() {
		Right root = rightDAO.getRootTreeNode();
		if(root!=null){
			if(root.getIsLeaf()==0){//不是叶子节点
				List<Right> childrenRights = rightDAO.getChildrenTreeNode(root);
				root.setChildrenRights(childrenRights);
			}
		}
		return TreeUtil.toJSON(TreeUtil.toTreeNode(root));
	}
	
	public List<Right> getChildrenTreeNode(Right model) {
		return rightDAO.getChildrenTreeNode(model);
	}
	
	public String queryChildren(Integer page, Integer rows, Right model) {
		if(model.getRightId()==null){
			Right root = rightDAO.getRootTreeNode();
			model.setRightId(root.getRightId());
		}
		List<Right> list = rightDAO.queryChildren(page,rows,model);
		Long total=rightDAO.countChildren(model);
		
		String[] properties = {"rightId","rightCode","rightName","rightUrl","rightKind","status","iconCls"};
		return JSONUtil.toJson(list, properties, total);
	}
	
	public ServiceResult save(Right model) {
		ServiceResult result = new ServiceResult(false);
		if(model==null){
			result.setMessage("请填写权限信息");
			return result;
		}
		if(StringUtils.isEmpty(model.getRightCode())){
			result.setMessage("请填写权限编号");
			return result;
		}
		if(StringUtils.isEmpty(model.getRightName())){
			result.setMessage("请填写权限名称");
			return result;
		}
		if(model.getRightKind()==null){
			result.setMessage("请选择权限类型");
			return result;
		}
//		/*
//		 * 如果是报表权限，则还需要对报表配置的信息进行验证
//		 */
//		if(model.getRightKind()==3){//报表权限
//			if(model.getIsNeedSelect()==null){
//				result.setMessage("请选择是否需要选择");
//				return result;
//			}
//			if(StringUtils.isEmpty(model.getReportDetailSql())){
//				result.setMessage("请选择明细网格Sql");
//				return result;
//			}
//			if(reportTemplateFile!=null&&"grf".equals(FilenameUtils.getExtension(reportTemplateFile.getName()))){
//				result.setMessage("上传的文件格式不正确");
//				return result;
//			}
//		}
		
		//判断权限编号是否已存在
		Right oldRight = rightDAO.loadByRightCode(model.getRightCode());
		if(model.getRightId()==null){//新增
			if(model.getParentRightId()==null){
				result.setMessage("请选择父权限");
				return result;
			}
			if(oldRight!=null){
				result.setMessage("权限编号已存在");
				return result;
			}
//			/*
//			 * 验证报表配置
//			 */
//			if(model.getKind()==3){//报表权限
//				if(reportTemplateFile==null){
//					result.setMessage("请上传报表模板");
//					return result;
//				}
//				FileUtil.saveFile(reportTemplateFile, reportTemplatePath+File.separator+model.getRightCode()+".grf");
//			}
			//计算新增的权限主键编号
			String maxRightPKCodeObj = rightDAO.getMaxRightPKCode(model.getParentRightId());
			String newRightPKCode = "";
			if(maxRightPKCodeObj==null){
				Right parentRight = rightDAO.loadByRightId(model.getParentRightId());
				newRightPKCode = parentRight.getRightPKCode()+String.format("%03d", 1);
			}else{
				String maxRightPKCode = maxRightPKCodeObj.toString();
				String parentRighId = maxRightPKCode.substring(0,maxRightPKCode.length()-3);
				newRightPKCode = parentRighId+String.format("%03d", Integer.parseInt(maxRightPKCode.substring(maxRightPKCode.length()-3))+10);
			}
			model.setRightPKCode(newRightPKCode);
			//查找该父权限下的权限排序最大值
			Integer maxArray = rightDAO.getMaxArray(model.getParentRightId());
			if(maxArray==null){
				maxArray=0;
			}
			model.setArray(maxArray+1);
			model.setIsLeaf(1);
			rightDAO.insert(model);
			roleRightDAO.insertByRightId(model.getRightId());
		}else{
			/*
			 * 1.验证权限配置
			 */
			if(oldRight!=null&&!oldRight.getRightId().equals(model.getRightId())){
				result.setMessage("其他权限已使用该权限编号");
				return result;
			}
			/*
			 * 验证报表配置
			 */
//			if(model.getKind()==3){//报表权限
//				//处理报表模版文件
//				if(reportTemplateFile!=null){
//					//移除原先的模板文件，保存新的模板文件
//					FileUtils.deleteQuietly(new File(reportTemplatePath+File.separator+oldModel.getRightCode()+".grf"));
//					FileUtil.saveFile(reportTemplateFile, reportTemplatePath+File.separator+model.getRightCode()+".grf");
//				}else{
//					if(!model.getRightCode().equals(oldModel.getRightCode())){
//						try {
//							FileUtils.moveFile(new File(reportTemplatePath+File.separator+oldModel.getRightCode()+".grf"), new File(reportTemplatePath+File.separator+model.getRightCode()+".grf"));
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
			rightDAO.update(model);
		}
		result.getData().put("rightId", model.getRightId());
		result.setIsSuccess(true);
		return result;
	}
	/*
	 * (non-Javadoc)   
	 * @see com.csit.service.RightService#mulDelete(java.lang.String)
	 */
	public ServiceResult mulDelete(String ids) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(ids)){
			result.setMessage("请选择要删除的记录");
			return result;
		}
		String[] idArray = StringUtil.split(ids, GlobalConstants.SPLIT_SEPARATOR);
		if(idArray.length==0){
			result.setMessage("请选择要删除的记录");
			return result;
		}
		Integer parentId = null;
		Right item = null;
		if(StringUtils.isNotEmpty(idArray[0])){
			item = rightDAO.loadByRightId(Integer.parseInt(idArray[0]));
		}
		for (String id : idArray) {
			if(StringUtils.isNotEmpty(idArray[0])){
				rightDAO.delete(Integer.parseInt(id));
			}
		}
		if(idArray.length>0){
			if(item!=null&&item.getParentRightId()!=null){
				parentId = item.getParentRightId();
				Long countChildren = rightDAO.countChildrenByParentRightId(parentId);
				if(countChildren==0){
					rightDAO.updateIsLeaf(parentId, 1);
				}
			}
		}
		result.setIsSuccess(true);
		return result;
	}
	/*
	 * (non-Javadoc)   
	 * @see com.csit.service.RightService#mulUpdateState(java.lang.String, com.csit.model.Right)
	 */
	public ServiceResult mulUpdateStatus(String ids, Right model) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(ids)){
			result.setMessage("请选择要修改状态的权限");
			return result;
		}
		String[] idArray =StringUtil.split(ids);
		if(idArray.length==0){
			result.setMessage("请选择要修改状态的权限");
			return result;
		}
		if(model==null||model.getStatus()==null){
			result.setMessage("请选择要修改成的状态");
			return result;
		}
		boolean haveUpdateStatus = false;
		for (String id : idArray) {
			Right oldRight = rightDAO.loadByRightId(Integer.parseInt(ids));
			if(oldRight!=null&&oldRight.getStatus().intValue()!=model.getStatus()){
				rightDAO.updateStatus(Integer.parseInt(id), model.getStatus());
				haveUpdateStatus = true;
			}
		}
		if(!haveUpdateStatus){
			result.setMessage("没有可修改状态的权限");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}
	
	public ServiceResult dropUpdateArray(String sourceId, String targetId,
			String point) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(sourceId)){
			result.setMessage("请选择要拖拽改变顺序的权限节点");
			return result;
		}
		if(StringUtils.isEmpty(targetId)){
			result.setMessage("请选择要拖拽的目标权限节点");
			return result;
		}
		if(StringUtils.isEmpty(targetId)){
			result.setMessage("请指定拖拽节点和目标节点的顺序");
			return result;
		}
		/*
		 * 改变顺序
		 * 1. 如果point是top和bottom,则
		 * 	1.1调整为targetId节点同父节点的array值
		 * 		如果point是top,则改变为targetId节点同父节点并array值>=targetId节点array的array值+1，
		 *		如果point是bottom,则改变与targetId节点同父节点并array值<=targetId节点array的array值-1，并将sourceId节点的array值改为原targetId节点值
		 * 	1.2.将sourceId节点的array值改为原targetId节点值
		 * 2. 如果point是append,则将直接更新拖拽节点的父节点和排序
		 * 3.更新IsLeaf 
		 * 	3.1 如果point是top和bottom,则需要更新的有sourceId节点的父节点
		 *  3.2.如果point是append,则一定要更新targetId节点为false和sourceId节点的父节点
		 */
		Right targetRight = rightDAO.loadByRightId(Integer.parseInt(targetId));
		Right sourceRight = rightDAO.loadByRightId(Integer.parseInt(sourceId));
		Integer sourceParentId = sourceRight.getParentRightId();
		
		if("top".equals(point)||"bottom".equals(point)){
			rightDAO.dropUpdateArray(targetRight,point);
			String newCode = dragChangeRightPKCode(targetRight.getParentRightId(), sourceRight);
			sourceRight.setRightPKCode(newCode);
			sourceRight.setParentRightId(targetRight.getParentRightId());
			sourceRight.setArray(targetRight.getArray());
			rightDAO.updateDragChange(sourceRight);
		}else if("append".equals(point)){
			Object maxArrayObj = rightDAO.getMaxArray(Integer.parseInt(targetId));
			Integer maxArray =  maxArrayObj==null?0:(Integer)maxArrayObj;
			String newCode = dragChangeRightPKCode(targetRight.getRightId(), sourceRight);
			sourceRight.setRightPKCode(newCode);
			sourceRight.setParentRightId(targetRight.getRightId());
			sourceRight.setArray(maxArray+1);
			rightDAO.updateDragChange(sourceRight);
			
			rightDAO.updateIsLeaf(targetRight.getRightId(), 0);
		}
		//更新sourceId节点的父节点的isLeaf
		Long countChildren = rightDAO.countChildrenByParentRightId(sourceParentId);
		if(countChildren==0){
			rightDAO.updateIsLeaf(sourceParentId, 1);
		}else{
			rightDAO.updateIsLeaf(sourceParentId, 0);
		}
		result.setIsSuccess(true);
		return result;
	}
	/**
	 * @Description: 拖拽改变权限PK编号
	 * @Create: 2013-9-30 下午5:30:09
	 * @author lys
	 * @update logs
	 * @param targetRight
	 * @param sourceRight
	 * @return
	 */
	private String dragChangeRightPKCode(Integer parentRightId, Right sourceRight) {
		/*
		 * 计算拖拽权限的code
		 * 1. 计算当前拖拽的权限主键编号：拖拽后所在的父权限下的最大孩子权限主键编号+1
		 * 2. 如果当前拖拽的权限有孩子权限，还需要更新孩子权限的编号：当前拖拽权限的新编号+孩子权限截断当前拖拽权限的原编号后的编号
		 */
		String maxCodeObj = rightDAO.getMaxRightPKCode(parentRightId);
		String newCode = "";
		if(maxCodeObj==null){
			Right parentRight = rightDAO.loadByRightId(parentRightId);
			newCode = parentRight.getRightPKCode()+String.format("%03d", 1);
		}else{
			String maxCode = maxCodeObj.toString();
			String parentCode = maxCode.substring(0,maxCode.length()-3);
			newCode = parentCode+String.format("%03d", Integer.parseInt(maxCode.substring(maxCode.length()-3))+1);
		}
		if(sourceRight.getIsLeaf()==0){
			List<Right> list = rightDAO.queryChildrenListByParentRightPKCode(sourceRight.getRightPKCode());
			for(Right right : list){
				if(right.getRightId()!=sourceRight.getRightId()){
					right.setRightPKCode(newCode+right.getRightPKCode().substring(sourceRight.getRightPKCode().length()));
				}
			}
		}
		return newCode;
	}
//	/**
//	 * @Description: 从未勾选到勾选情况下更新父节点的状态
//	 * @Create: 2012-10-27 下午10:27:01
//	 * @author lys
//	 * @update logs
//	 * @param model
//	 * @throws Exception
//	 */
//	private void setParentTrue(RoleRight model){
//		Right parentRight = rightDAO.getParentRight(model.getRight());
//		if(parentRight!=null){
//			RoleRightId id = new RoleRightId();
//			id.setRoleId(model.getRole().getRoleId());
//			id.setRightId(parentRight.getRightId());
//			
//			RoleRight parentRoleRight = roleRightDAO.load(id);
//			if(parentRoleRight!=null&&parentRoleRight.getState().intValue()==0){
//				roleRightDAO.updateState(model.getRole().getRoleId(),parentRight.getRightId(),1);
//				setParentTrue(parentRoleRight);
//			}
//		}
//	}
}
