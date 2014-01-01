package org.linys.service.common;

import org.linys.dao.common.CommonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServ {
	private CommonDAO commonDAO;

	public CommonDAO getCommonDAO() {
		return commonDAO;
	}
    @Autowired
	public void setCommonDAO(CommonDAO commonDAO) {
		this.commonDAO = commonDAO;
	}
	
    
    public int getPageCount(String totalSql) {
		 return commonDAO.getPageCount(totalSql);
	}
}
