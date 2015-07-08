package org.linys.dao.countCard;

import java.util.List;

import org.linys.annotation.MyBatisRepository;
import org.linys.model.countCard.CountCard;

@MyBatisRepository
public interface CountCardDAO {
    int deleteByPrimaryKey(Integer countCardId);

    int insertSelective(CountCard record);

    int updateByPrimaryKeySelective(CountCard record);

    int updateByPrimaryKey(CountCard record);
    
    /**
     * 
     */
    void insert(CountCard model);
    
    /**
     * 根据计次卡属性查找计次卡
     */
    CountCard load(CountCard model);
    
    /**
     * 更新会员
     */
    void update(CountCard model);
    
    /**
     * 查询
     */
    List<CountCard> query(CountCard model);
    
    /**
	 * @description: 统计计次卡
	 */
	Long count(CountCard model);
	
	/**
	 * 删除
	 * @param customerId
	 */
	void delete(Integer customerId);
}