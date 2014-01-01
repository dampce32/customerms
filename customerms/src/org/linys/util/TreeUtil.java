package org.linys.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.linys.model.system.Right;
import org.linys.vo.TreeNode;
import org.linys.vo.TreeNode.StateType;

public class TreeUtil extends TreeBaseUtil{
	
	/**
	 * @Description: 将List<Right>生成JSON字符串
	 * @Create: 2012-10-14 下午11:46:04
	 * @author lys
	 * @update logs
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static String toJSONRightList(List<Right> list){
		List<TreeNode> treeNodeList = toTreeNodeList(list);
		return toJSON(treeNodeList);
	}
	/**
	 * @Description: 将List<Right>转化成List<TreeNode>
	 * @Create: 2012-10-14 下午11:43:15
	 * @author lys
	 * @update logs
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<TreeNode> toTreeNodeList(List<Right> list){
		if(list==null){
			return null;
		}
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		for (Right right : list) {
			treeNodeList.add(toTreeNode(right));
		}
		return treeNodeList;
	}
	/**
	 * @Description: 将权限Right转化成TreeNode
	 * @Create: 2012-10-14 下午11:34:39
	 * @author lys
	 * @update logs
	 * @param right
	 * @return
	 * @throws Exception
	 */
	public static TreeNode toTreeNode(Right right){
		if(right==null){
			return null;
		}
		TreeNode treeNode = new TreeNode();
		treeNode.setId(right.getRightId());
		treeNode.setText(right.getRightName());
		if(StringUtils.isNotEmpty(right.getIconCls())){
			treeNode.setIconCls(right.getIconCls());
		}
		if(right.getStatus()!=null&&right.getStatus().intValue()==0){
			treeNode.setChecked(false);
		}else {
			treeNode.setChecked(true);
		}
		if(right.getIsLeaf()==0&&!"所有权限".equals(right.getRightName())){
			treeNode.setState(StateType.closed);
		}
		treeNode.getAttributes().put("rightUrl", right.getRightUrl());
		if(right.getIsLeaf()==0){
			List<TreeNode> childrenNode = new ArrayList<TreeNode>();
			List<Right> children = right.getChildrenRights();
			for (Right right2 : children) {
				childrenNode.add(toTreeNode(right2));
			}
			treeNode.setChildren(childrenNode);
		}
		return treeNode;
	}
}
