//判断combobox的内容是否为提供的选项
var isValidComboboxs = function(comboboxArray){
	for ( var i = 0; i < comboboxArray.length; i++) {
		var combobox = comboboxArray[i];
		if(!isValidCombobox(combobox[0],combobox[1])){
			return false;
		}
	}
	return true;
};
var isValidCombobox = function(combobox,fieldName){
	var datas = combobox.combobox('getData');
	var valueFieldValue = combobox.combobox('getValue');
	var valueField = combobox.combobox('options').valueField;
	var isValid = false;
	if(valueFieldValue==''){
		return true;
	}else{
		for(var data in datas){
			if(datas[data][valueField]==valueFieldValue){
				isValid = true;
			}
		}
		if(!isValid){
			$.messager.alert('警告','请选择提供的'+fieldName,'warning');
			return false;
		}
		return true;
	}
};

//判断combogrid的内容是否为提供的选项
var isValidCombogrids = function(combogridArray){
	for ( var i = 0; i < combogridArray.length; i++) {
		var combogrid = combogridArray[i];
		if(!isValidCombogrid(combogrid[0],combogrid[1])){
			return false;
		}
	}
	return true;
};
var isValidCombogrid = function(combogrid,fieldName){
	var datas = combogrid.combogrid('grid').datagrid('getData');
	var idFieldValue = combogrid.combogrid('getValue');
	var idField = combogrid.combogrid('options').idField;
	var isValid = false;
	if(idFieldValue==''){
		return true;
	}else{
		for(var row in datas.rows){
			if(datas.rows[row][idField]==idFieldValue){
				isValid = true;
			}
		}
		if(!isValid){
			$.messager.alert('警告','请选择提供的'+fieldName,'warning');
			return false;
		}
		return true;
	}
};