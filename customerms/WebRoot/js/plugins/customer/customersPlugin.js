// 创建一个闭包  
(function($) {  
  // 插件的定义  
  $.fn.customersInit = function() {
	  var $this = $(this);
	  var id = $(this).attr('id');
	  var rightId = $(this).attr('rightId');
	  var selectRow = null;
	  var height = $(document.body).height();
	
	  var editDialog = $('#editDialog_'+id,$this);
	  var editForm = $('#editForm_'+id,editDialog);
	  var viewList =  $('#viewList_'+id,$this);
	  var queryContent = $('#queryContent_'+id,$this);
	  
	  //加载列表
	  $(viewList).datagrid({
		  	url:"customer/queryCustomer.do",
		  	singleSelect:true,
			method:"POST",
			nowrap:true,
			striped: true,
			collapsible:true,
			pagination:true,
			rownumbers:true,
			selectOnCheck:false,
			checkOnSelect:false,
			singleSelect:true,
			fit:true,
			toolbar:'#tb_'+id,
			columns:[[
			    {field:'ck',checkbox:true},
				{field:'customerCode',title:'会员号',width:100,align:"center"},
				{field:'customerName',title:'会员名称',width:100,align:"center"},
				{field:'telephone',title:'电话',width:100,align:"center"},
				{field:'wechat',title:'微信',width:100,align:"center"},
				{field:'customerTypeId',title:'会员类型',width:100,align:"center",
					formatter: function(value,row,index){
						var customerTypeList =Customers.getCustomerTypeList();
						for(var customerType in customerTypeList){
							if(customerTypeList[customerType].customerTypeId==value){
								return customerTypeList[customerType].customerTypeName;
							}
						}
				 }},
				 {field:'amount',title:'会员卡余额',width:100,align:"center"}
			]],
			onClickRow:function(rowIndex, rowData){
				selectRow = rowData;
				selectIndex = rowIndex;
			},
			onDblClickRow:function(rowIndex,rowData){
				onUpdate();
			},
			onLoadSuccess:function(){
				selectRow = null;
		 		selectIndex = null;
				pageNumber = 1;
			}
	  });
	  //添加
	  $('#add_'+id,$this).click(function(){
		  onAdd();
	  });
	  //修改
	  $('#update_'+id,$this).click(function(){
		  onUpdate();
	  });
	  //删除
	  $('#delete_'+id,$this).click(function(){
		  onMulDelete();
	  });
	//编辑框
	$(editDialog).dialog({  
	    title: '编辑会员',  
	    width:400,
	    height:400,
	    closed: true,  
	    cache: false,  
	    modal: true,
	    closable:false,
	    toolbar:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				onSave();
			}
		},{
			text:'退出',
			iconCls:'icon-exit',
			handler:function(){
				$(editDialog).dialog('close');
			}
		}],
		onClose:function(){
			$(editForm).form('clear');
		}
	});  
	var initCombobox=function(){
		$('#customerType',editDialog).combobox({
			  data:Customers.getCustomerTypeList(),
			  valueField:'customerTypeId',
			  textField:'customerTypeName'
		  });
	}
	//添加
	var onAdd = function(){
		initCombobox();
		$(editDialog).dialog('open');
	};
	//保存前的赋值操作
	var setValue = function(){
		var customerCode = $.trim($('#customerCode',editForm).val());
		if(''==customerCode){
			$.messager.alert('提示','请填写会员号','warning');
			return false;
		}
		var customerName = $.trim($('#customerName',editForm).val());
		if(''==customerName){
			$.messager.alert('提示','请填写会员名称','warning');
			return false;
		}
		var customerType = $.trim($('#customerType',editForm).combobox('getValue'));
		if(''==customerType){
			$.messager.alert('提示','请填写金额','warning');
			return false;
		}
		var isValidComboboxsArray = new Array();
		isValidComboboxsArray.push({0:$('#customerType',editForm),1:'会员类型'});
		if(!isValidComboboxs(isValidComboboxsArray)){
			return false;
		}
		return true;
	};
	//保存
	var onSave = function(){
		$(editForm).form('submit',{
			url:'customer/saveCustomer.do',
			onSubmit: function(){
				return setValue();
			},
			success: function(data){
				var result = eval('('+data+')');
				if(result.isSuccess){
					var fn = function(){
						search();
						$(editDialog).dialog('close');
					};
					$.messager.alert('提示','保存成功','info',fn);
				}else{
					$.messager.alert('提示',result.message,"error");
				}
			}
		});
	};
	//修改
	var onUpdate = function(){
		if(!$('#update_'+id,$this).is(":hidden")){
			if(selectRow==null){
				$.messager.alert("警告","请选择数据行",'warning');
				return;
			}
			initCombobox();
			$(editForm).form('load',selectRow);
			$(editDialog).dialog('open');
		}
	 };
	//查询
	var search = function(){
		var customerNameSearch = $('#customerNameSearch',queryContent).val();
		var content = {customerName:customerNameSearch};
		$(viewList).datagrid({
			queryParams:content
		});
		checkBtnRight();
	};
	//查询
	$('#search',$this).click(function(){
		search();
	});
	//批量删除
	var onMulDelete = function(){
		var rows = $(viewList).datagrid('getChecked');
		if(rows.length==0){
			 $.messager.alert('警告',"请选中要删除的纪录","warning");
			 return;	
		}
		$.messager.confirm("提示！","确定要删除选中的记录?",function(t){ 
			if(!t) return;
			var idArray = new Array();
			for(var i=0;i<rows.length;i++){
				idArray.push(rows[i].customerId);
			}
			var ids = idArray.join(CSIT.join);
			var url = "customer/mulDeleteCustomer.do";
			var content = {ids:ids};
			$.post(url,content,
				function(result){
					if(result.isSuccess){
						search();
					}else{
						$.messager.alert('提示',result.message,"error");
					}
				}, "json");
		});
	};
	//----------检查权限--------------
	var rights = null;
	var checkBtnRight = function(){
	};
	checkBtnRight();
  };
})(jQuery);   