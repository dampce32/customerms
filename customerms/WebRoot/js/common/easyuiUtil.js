var EasyUIUtil = {};
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
	var multiple = combobox.combobox('options').multiple;
	var isValid = false;
	
	if(multiple){
		var valueFieldValues = combobox.combobox('getValues');
		var valueField = combobox.combobox('options').valueField;
		for ( var i = 0; i < valueFieldValues.length; i++) {
			var valueFieldValue = valueFieldValues[i];
			for(var data in datas){
				if(datas[data][valueField]==valueFieldValue){
					isValid = true;
				}
			}
			if(!isValid){
				$.messager.alert('警告','请选择提供的'+fieldName,'warning');
				return false;
			}
		}
		return true;
	}else{
		var valueFieldValue = combobox.combobox('getValue');
		var valueField = combobox.combobox('options').valueField;
		if(valueFieldValue==''){
			return true;
		}else{
			for(var data in datas){
				if(datas[data][valueField]==valueFieldValue){
					return true;
				}
			}
			$.messager.alert('警告','请选择提供的'+fieldName,'warning');
			return false;
		}
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
$.extend($.fn.datagrid.methods, {
    addEditor : function(jq, param) {
        if (param instanceof Array) {
            $.each(param, function(index, item) {
                var e = $(jq).datagrid('getColumnOption', item.field);
                e.editor = item.editor;
            });
        } else {
            var e = $(jq).datagrid('getColumnOption', param.field);
            e.editor = param.editor;
        }
    },
    removeEditor : function(jq, param) {
        if (param instanceof Array) {
            $.each(param, function(index, item) {
                var e = $(jq).datagrid('getColumnOption', item);
                e.editor = {};
            });
        } else {
            var e = $(jq).datagrid('getColumnOption', param);
            e.editor = {};
        }
    }
});
$.extend($.fn.validatebox.defaults.rules, {
	minLength : { // 判断最小长度
		validator : function(value, param) {
			return value.length >= param[0];
		},
		message : '最少输入 {0} 个字符。'
	},
	length:{validator:function(value,param){
		var len=$.trim(value).length;
			return len>=param[0]&&len<=param[1];
		},
			message:"内容长度介于{0}和{1}之间."
		},
	minValue:{validator:function(value,param){
			return value >=param[0];
		},
			message:"最小值是{0}."
		},
	maxValue:{validator:function(value,param){
			return value <=param[0];
		},
			message:"最大值是{0}."
		},
	betweenValue:{validator:function(value,param){
			return value >=param[0]&&value<=param[1];
		},
			message:"值介于{0}和{1}之间."
		},
	phoneOrZero : {// 验证电话号码
		validator : function(value) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value)||/^0$/i.test(value);
		},
		message : '格式不正确,请使用下面格式:0591-88888888或0'
	},
	phone : {// 验证电话号码
		validator : function(value) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
		},
		message : '格式不正确,请使用下面格式:0591-88888888'
	},
	mobile : {// 验证手机号码
		validator : function(value) {
			return /^(13|15|17|18)\d{9}$/i.test(value);
		},
		message : '手机号码格式不正确(正确格式如：13450774432)'
	},
	phoneOrMobile:{//验证手机或电话
		validator : function(value) {
			return /^(13|15|17|18)\d{9}$/i.test(value) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
		},
		message:'请填入手机或电话号码,如13988888888或0591-8888888'
	},
	phoneOrMobileOrZero:{//验证手机或电话或0
		validator : function(value) {
			return /^(13|15|17|18)\d{9}$/i.test(value) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value)||/^0$/i.test(value);
		},
		message:'请填入手机或电话号码或0,如13988888888或0591-8888888或0'
	},
	idcard : {// 验证身份证
		validator : function(value) {
			return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
		},
		message : '身份证号码格式不正确'
	},
	floatOrInt : {// 验证是否为小数或整数
		validator : function(value) {
			return /^(\d{1,3}(,\d\d\d)*(\.\d{1,3}(,\d\d\d)*)?|\d+(\.\d+))?$/i.test(value);
		},
		message : '请输入数字，并保证格式正确'
	},
	currency : {// 验证货币
		validator : function(value) {
			return /^d{0,}(\.\d+)?$/i.test(value);
		},
		message : '货币格式不正确'
	},
	qq : {// 验证QQ,从10000开始
		validator : function(value) {
			return /^[1-9]\d{4,9}$/i.test(value);
		},
		message : 'QQ号码格式不正确(正确如：453384319)'
	},
	integer : {// 验证整数
		validator : function(value) {
			return /^[+]?[1-9]+\d*$/i.test(value);
		},
		message : '请输入整数'
	},
	chinese : {// 验证中文
		validator : function(value) {
			return /^[\u0391-\uFFE5]+$/i.test(value);
		},
		message : '请输入中文'
	},
	english : {// 验证英语
		validator : function(value) {
			return /^[A-Za-z]+$/i.test(value);
		},
		message : '请输入英文'
	},
	unnormal : {// 验证是否包含空格和非法字符
		validator : function(value) {
			return /.+/i.test(value);
		},
		message : '输入值不能为空和包含其他非法字符'
	},
	username : {// 验证用户名
		validator : function(value) {
			return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
		},
		message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
	},
	faxno : {// 验证传真
		validator : function(value) {
//			return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
		},
		message : '传真号码不正确'
	},
	postalcode : {// 验证邮政编码
		validator : function(value) {
			return /^[1-9]\d{5}$/i.test(value);
		},
		message : '邮政编码格式不正确'
	},
	ip : {// 验证IP地址
		validator : function(value) {
			return /d+.d+.d+.d+/i.test(value);
		},
		message : 'IP地址格式不正确'
	},
	name : {// 验证姓名，可以是中文或英文
			validator : function(value) {
				return /^[\u0391-\uFFE5]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value);
			},
			message : '姓名格式不正确'
	},
	carNo:{//车牌号
		validator : function(value){
			return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value); 
		},
		message : '车牌号码无效（例：粤J12350）'
	},
	carenergin:{//发动机号
		validator : function(value){
			return /^[a-zA-Z0-9]{16}$/.test(value); 
		},
		message : '发动机型号无效(例：FG6H012345654584)'
	},
	email:{//电子邮件
		validator : function(value){
		return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
	},
	message : '请输入有效的电子邮件账号(例：abc@126.com)'	
	},
	photo:{//图片
		validator : function(value){
		return /\.(jpg|jpeg|png|gif|JPG|PNG|JPEG|GIF)$/.test(value); 
	},
	message : '图片类型必须是jpeg,jpg,png中的一种'	
	},
	zip:{//格式为zip
		validator : function(value){
		return /\.(zip)$/.test(value); 
	},
	message : '上传文件类型必须是zip'	
	},
	word:{//Word文档
		validator : function(value){
			return /\.(doc|docx)$/.test(value); 
		},
		message : '上传文件类型必须是word文档'	
	},
	msn:{
		validator : function(value){
			return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
		},
		message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
	},
	department:{
		validator : function(value){
			return /^[0-9]*$/.test(value); 
		},
		message : '请输入部门排序号(例：1)'	
	},
	same:{
		validator : function(value, param){
			if($("#"+param[0]).val() != "" && value != ""){
				return $("#"+param[0]).val() == value; 
			}else{
				return true;
			}
		},
		message : '两次输入的密码不一致！'	
	},
	matchUserPwd:{
		validator : function(value){
			var r, re; // 声明变量。
			re = new RegExp("^(?!^[0-9]+$)(?!^[a-zA-Z]+$).{6,}$","g"); // 创建正则表达式对象。
			r = re.test(value); // 在字符串 s 中查找匹配。
			return(r);
		},
		message : '密码不能全为数字或字母！'	
	},
	percentage:{//分式的值不超过0.1（10%）
		validator:function(value, param){
			var id1 = param[0];
			var id2 = param[1];
			var range = param[2];
			var numerator = parseFloat($('#'+id1,'#'+range).numberbox('getValue'));
			var denominator = parseFloat($('#'+id2,'#'+range).numberbox('getValue'));
			return numerator / denominator <= 0.1;
		},
		message:'必须前10%'
	},
	maxLength:{//内容不超过指定长度
		validator:function(value, param) {
			return value.length <= param[0];
		},
		message:'不超过{0}字'
	},
	unitCode:{//单位组织机构代码（辅导员就业去向管理）
		validator : function(value){
			var r, re; // 声明变量。
			re = new RegExp('^([0-9]{9}|([0-9]{8}x)|无)$','g'); // 创建正则表达式对象。
			r = re.test(value); // 在字符串 s 中查找匹配。
			return(r);
		},
		message : '请填写“9个数字”或“8个数字+x”或“无”'	
	},
	confidentialCode:{//机要号（辅导员档案去向管理）
		validator : function(value){
			var r, re; // 声明变量。
			re = new RegExp('^B[0-9]{11}$','g'); // 创建正则表达式对象。
			r = re.test(value); // 在字符串 s 中查找匹配。
			return(r);
		},
		message : '机要号的格式：“B”开头加上11位数字'	
	}
});
//转化成combobox能解析的value值
var convertToComboboxValues = function(sourceStr){
	var sourceArray = sourceStr.split(",");
	var valueArray = new Array();
	for ( var source in sourceArray) {
		valueArray.push($.trim(sourceArray[source]));
	}
	return valueArray;
};

/**
 * EasyUI DataGrid根据字段动态合并单元格
 * @param tableID 要合并table的id
 * @param colList 要合并的列,用逗号分隔(例如："name,department,office");
 */
function mergeCellsByField(tableID,colList){
    var ColArray = colList.split(",");//需要合并列的属性名数组
    var tTable = $('#'+tableID);//datagrid对象
    var TableRowCnts=tTable.datagrid("getRows").length;//返回当前页的记录的长度
    var tmpA;//需要合并的行数
  //  var tmpB;//合并到第几行
    var PerTxt = "";//循环时存放上一个单元格的值
    var CurTxt = "";//每个要合并单元格的值
    var alertStr = "";
    for (j=ColArray.length-1;j>=0 ;j-- )
    {
        PerTxt="";
        tmpA=1;
        //tmpB=0;
        for (i=0;i<=TableRowCnts ;i++ )
        {
            if (i==TableRowCnts)
            {
                CurTxt="";
            }
            else
            {
                CurTxt=tTable.datagrid("getRows")[i][ColArray[j]];
            }
            if (PerTxt==CurTxt)
            {
            	//行数加1
                tmpA+=1;
            }
            else
            {
                //tmpB+=tmpA;
                tTable.datagrid('mergeCells',{
                    index:i-tmpA,
                    field:ColArray[j],
                    rowspan:tmpA,
                    colspan:null
                });
                //将需要合并的行数设置为1
                tmpA=1;
            }
            PerTxt=CurTxt;
        }
    }
}

