package org.linys.dao.sale;

import java.util.List;
import org.linys.annotation.MyBatisRepository;
import org.linys.model.sale.SaleBuyCountCard;

/**
 * 计次卡消费管理 DAO
 * @author wxy530
 *
 */
@MyBatisRepository
public interface SaleBuyCountCardDAO {

	/**
	 * 查询计次卡消费情况
	 * @param model
	 * @return
	 */
	public List<SaleBuyCountCard> query(SaleBuyCountCard model);
	
	/**
	 * 统计计次卡消费总记录数
	 * @param model
	 * @return
	 */
	public Long count(SaleBuyCountCard model);
	
	/**
	 * 插入会员计次卡消费记录
	 * @param model
	 */
	public void insert(SaleBuyCountCard model);
	
	/**
	 * 更新会员已消费次数
	 * @param model
	 */
	public void update(SaleBuyCountCard model);
	
	/**
	 * 删除会员计次卡消费记录
	 * @param model
	 */
	public void delete(Integer saleBuyCountCardId);
}
