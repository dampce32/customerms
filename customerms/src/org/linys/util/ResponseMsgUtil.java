package org.linys.util;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

@Component
public class ResponseMsgUtil {

	
	 public  void doResponse(String jsonStr) {
		  
			HttpServletResponse response = ServletActionContext.getResponse();
		  	if (response == null) {
		  	  
		      return;
		    }
		    response.setHeader("Cache-Control", "no-store");
		    response.setHeader("Pragma", "no-cache");
		    response.setContentType("text/plain; charset=UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    try {
		      response.getWriter().print(jsonStr);
		    } catch (Exception ex) {
		    	ex.printStackTrace();
		    }
		  }
}
