package org.linys.service.countCard.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.linys.dao.countCard.CountCardDAO;
import org.linys.model.countCard.CountCard;
import org.linys.service.countCard.CountCardService;
import org.linys.util.JSONUtil;
import org.linys.util.StringUtil;
import org.linys.vo.ServiceResult;
import org.springframework.stereotype.Service;

/**
 * 计次卡sevice
 * @author wxy530
 *
 */
@Service
public class CountCardServiceImpl implements CountCardService{

	@Resource
	private CountCardDAO countCardDAO;
	
	public ServiceResult save(CountCard model) {
		ServiceResult result = new ServiceResult(false);
		if(model.getCountCardTypeId()==null){
			result.setMessage("请填写次卡类型");
			return result;
		}
		if(model.getAmount()==null){
			result.setMessage("请填写档次");
			return result;
		}
		if(model.getCanSaleCount()==null){
			result.setMessage("请填写可消费次数");
			return result;
		}
		CountCard oldCountCard = countCardDAO.load(model);
		if(model.getCountCardId()==null){//新增
			if(oldCountCard!=null){
				result.setMessage("该计次卡类型的档次已存在，请重新输入计次卡该类型或档次");
				return result;
			}
			countCardDAO.insert(model);
		}else{
			if(oldCountCard !=null && !oldCountCard.getCountCardId().equals(model.getCountCardId())){
				result.setMessage("该计次卡已被其他使用");
				return result;
			}
			countCardDAO.update(model);
		}
		result.getData().put("countCardId", model.getCountCardId());
		result.setIsSuccess(true);
		return result;
	}

	public String query(CountCard model) {
		List<CountCard> list = countCardDAO.query(model);
		Long total = countCardDAO.count(model);
		String[] properties = {"countCardId","countCardTypeId","amount","canSaleCount","countCardTypeName"};
		return JSONUtil.toJson(list,properties,total);
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
			countCardDAO.delete(id);
			haveDelete = true;
		}
		if(!haveDelete){
			result.setMessage("没有可删除的计次卡");
			return result;
		}
		result.setIsSuccess(true);
		return result;
	}

}
