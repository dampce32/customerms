package org.linys.action.dict;

import javax.annotation.Resource;

import org.linys.action.BaseAction;
import org.linys.model.dict.Goods;
import org.linys.service.dict.GoodsService;
import org.linys.vo.ServiceResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @Description:产品Action
 * @Copyright: 福州骏华信息有限公司 (c)2013
 * @Created Date : 2013-4-17
 * @Author lys
 */
@Controller
@Scope("prototype")
public class GoodsAction extends BaseAction implements ModelDriven<Goods> {

	private static final long serialVersionUID = -3899336650807315718L;
	private Goods model = new Goods();

	@Resource
	private GoodsService goodsService;

	public Goods getModel() {
		return model;
	}
	
	/**
	 * @Description: 保存产品
	 * @Create: 2013-1-22 上午10:33:19
	 * @author lys
	 * @update logs
	 */
	public void save(){
		ServiceResult result = new ServiceResult(false);
		try {
			result = goodsService.save(model);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存产品失败");
		}
		String jsonString = result.toJSON();
		ajaxJson(jsonString);
	}
	
	/**
	 * @Description: 分页查询产品
	 * @Create: 2012-10-27 上午9:46:10
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void query(){
		try {
			String jsonArray = goodsService.query(page, rows, model);
			ajaxJson(jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 批量产品删除
	 * @Create: 2012-10-27 下午12:00:30
	 * @author lys
	 * @update logs
	 * @throws Exception
	 */
	public void mulDelete(){
		ServiceResult result = new ServiceResult(false);	
		try {
			result = goodsService.mulDelete(ids);
		} catch (Throwable e) {
			if(e instanceof org.springframework.dao.DataIntegrityViolationException){
				result.setMessage("其他模块已使用要删除的产品信息了");
			}else{
				result.setMessage("批量产品删除失败");
			}
		}
		ajaxJson(result.toJSON());
	}
	/**
	 * @description: 添加查询消费产品
	 * @created: 2014-1-4 下午4:43:52
	 * @author 以宋
	 */
	public void querySelect(){
		String jsonArray = goodsService.querySelect(page, rows, model,ids);
		ajaxJson(jsonArray);
	}
}
