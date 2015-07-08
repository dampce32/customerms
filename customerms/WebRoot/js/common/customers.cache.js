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
//用户
Customers.UserList = null;
Customers.getUserList = function(){
	if(Customers.UserList==null){
		var url = 'system/queryComboboxUser.do';
		Customers.UserList = syncCallService(url);
	}
	return Customers.UserList;
};

//当前操作员登录数据
Customers.currUser = null;
Customers.getCurrUser = function(){
	if(Customers.currUser==null){
		var url = 'system/getCurrUser.do';
		var result = syncCallService(url,null);
		if(result.isSuccess){
			Customers.currUser = result.data;
		}else{
			$.messager.alert('提示',result.message,'error');
		}
	}
	return Customers.currUser;
};
//计次卡类型
Customers.CountCardTypeList = null;
Customers.getCountCardTypeList = function(){
	if(Customers.CountCardTypeList==null){
		var url = 'dict/queryComboboxDataDict.do';
		var content = {'dataDictType':'countCardType'};
		Customers.CountCardTypeList = syncCallService(url,content);
	}
	return Customers.CountCardTypeList;
};
Customers.initCountCardTypeCombobox = function(id,range){
	$(id,range).combobox({
		  data:Customers.getCountCardTypeList(),
		  valueField:'dataDictId',
		  textField:'dataDictName'
	});
};
