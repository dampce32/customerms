var Customers={};
//会员类型
Customers.CustomerTypeList = null;
Customers.getCustomerTypeList = function(){
	if(Customers.CustomerTypeList==null){
		var url = 'dict/queryComboboxCustomerType.do';
		Customers.CustomerTypeList = syncCallService(url);
	}
	return Customers.CustomerTypeList;
};
