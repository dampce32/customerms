package org.linys.service.dict.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.linys.dao.dict.GoodsDAO;
import org.linys.model.dict.Goods;
import org.linys.service.dict.GoodsService;
import org.linys.util.JSONUtil;
import org.linys.util.StringUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;
/**
 * @description:产品Service实现类
 * @copyright:福州骏华信息有限公司 (c)2014
 * @created:2014-1-3
 * @author:以宋
 * @vesion:1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	@Resource
	private GoodsDAO goodsDAO;

	public ServiceResult save(Goods model) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(model.getGoodsName())){
			result.setMessage("请填写产品名称");
			return result;
		}
		
		Goods oldGoods = goodsDAO.loadByGoodsName(model.getGoodsName());
		if(model.getGoodsId()==null){//新增
			if(oldGoods!=null){
				result.setMessage("产品名称已存在，请重新输入产品名称");
				return result;
			}
			goodsDAO.insert(model);
		}else{
			if(oldGoods!=null&&!oldGoods.getGoodsId().equals(model.getGoodsId())){
				result.setMessage("其他权限已使用该产品名称");
				return result;
			}
			goodsDAO.update(model);
		}
		result.getData().put("goodsId", model.getGoodsId());
		result.setIsSuccess(true);
		return result;
	}

	public String query(Integer page, Integer rows, Goods model) {
		List<Goods> list = goodsDAO.query(page,rows,model);
		Long total = goodsDAO.count(model);
		String[] properties = {"goodsId","goodsName","amount","isDiscount"};
		return JSONUtil.toJson(list, properties, total);
	}

	public ServiceResult mulDelete(String ids) {
		ServiceResult result = new ServiceResult(false);
		if(StringUtils.isEmpty(ids)){
			result.setMessage("请选择要删除的记录");
			return result;
		}
		Integer[] idArray = StringUtil.splitToInteger(ids);
		if(idArray.length==0){
			result.setMessage("请选择要删除的记录");
			return result;
		}
		boolean haveDelete = false;
		for (Integer id : idArray) {
			goodsDAO.delete(id);
			haveDelete = true;
		}
		if(!haveDelete){
			result.setMessage("没有可删除的产品");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}
	
	
}
