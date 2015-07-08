// 创建一个闭包  
(function($) {  
  // 插件的定义  
  $.fn.saleBuyCountCardInit = function() {
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
		  	url:"sale/querySaleBuyCountCard.do",
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
			    {field:'saleDate',title:'消费日期',width:150,align:"center"},
			    {field:'customerCode',title:'会员号',width:100,align:"center"},
			    {field:'customerName',title:'会员名称',width:100,align:"center"},
			    {field:'countCardTypeName',title:'计次卡类型',width:100,align:"center"},
			    {field:'amount',title:'档次',width:100,align:"center"},
			    {field:'canSaleCount',title:'购买消费次数',width:100,align:"center"},
			    {field:'saleCount',title:'消费次数',width:100,align:"center"},
			    {field:'canCount',title:'剩余次数',width:100,align:"center"},
				{field:'userName',title:'录入人',width:100,align:"center"}
			]],
			onClickRow:function(rowIndex, rowData){
				selectRow = rowData;
				selectIndex = rowIndex;
			},
			onLoadSuccess:function(){
				selectRow = null;
		 		selectIndex = null;
				pageNumber = 1;
			}
	  });
	  //添加
	  $('#addSale_'+id,$this).click(function(){
		  onAddSale();
	  });
	  //删除
	  $('#delete_'+id,$this).click(function(){
		  onDelete();
	  });
	//编辑框
	$(editDialog).dialog({  
	    title: '编辑消费计次卡',  
	    width:550,
	    height:350,
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
			search();
		}
	}); 
	var initCombobox=function(){
		$('#user',editDialog).combobox({
			  data:Customers.getUserList(),
			  valueField:'userId',
			  textField:'userName',
			  required:true
		  });
	}
	//添加
	var onAddSale = function(){
		$(editDialog).dialog({  
		    title: '添加消费计次卡'
		});
		initCombobox();
		$(editDialog).dialog('open');
	};
	//保存前的赋值操作
	var setValue = function(){
		var isValid = $(editDialog).form('validate');
		if(!isValid){
			return false;
		}
		var customerBuyCountCardId = $.trim($('#customerBuyCountCardId',editForm).val());
		if(''==customerBuyCountCardId){
			$.messager.alert('提示','请选择会员购买计次卡','warning');
			return false;
		}
		var canCount = parseFloat($('#canCount',editForm).val());
		var saleCount = parseFloat($('#saleCount',editForm).numberbox('getValue'));
		if(canCount<saleCount){
			$.messager.alert('提示','消费次数不可大于可消费次数','warning');
			return false;
		}
		$(editDialog).mask({maskMsg:'正在保存'});
		return true;
	};
	
	//保存
	var onSave = function(saveType){
		$(editForm).form('submit',{
			url:'sale/saveSaleBuyCountCard.do',
			onSubmit: function(){
				return setValue();
			},
			success: function(data){
				var result = eval('('+data+')');
				if(result.isSuccess){
					if(saveType!='rechargeSave'){
						$.messager.alert('提示','保存成功','info');
					}
					$(editDialog).dialog('close');
				}else{
					$.messager.alert('提示',result.message,"error");
				}
				$(editDialog).mask('hide');
			}
		});
	};
	//查询
	var search = function(){
		var beginDate = $('#beginDateSearch',queryContent).val();
		var endDate = $('#endDateSearch',queryContent).val();
		var customerName = $('#customerNameSearch',queryContent).val();
		var content = {beginDate:beginDate,endDate:endDate,customerName:customerName};
		$(viewList).datagrid({
			queryParams:content
		});
		checkBtnRight();
	};
	//查询
	$('#searchBtn_'+id,$this).click(function(){
		search();
	});
	$('#clearBtn_'+id,$this).click(function(){
		$(queryContent).form('clear');
	});
	//删除
	var onDelete = function(){
		var rows = $(viewList).datagrid('getSelected');
		if(rows==null){
			 $.messager.alert('警告',"请选中要删除的纪录","warning");
			 return;	
		}
		$.messager.confirm("提示！","确定要删除选中的记录，删除后，会员计次卡中将扣除消费次数？",function(t){ 
			if(!t) return;
			var url = "sale/deleteSaleBuyCountCard.do";
			var content = {saleBuyCountCardId:rows.saleBuyCountCardId,customerBuyCountCardId:rows.customerBuyCountCardId,
					       saleCount:rows.saleCount,alreadySaleCount:(rows.canSaleCount-rows.canCount)};
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
	//---选择会员购买计次卡---
	$('#searchCustomerBuyCountCard_'+id,editDialog).click(function(){
		$(customerDialog).dialog('open');
		onSearchCustomer();
	  });
	var customerDialog = $('#customerDialog_'+id,$this);
	var customerList =  $('#customerList_'+id,customerDialog);
	//编辑框
	$(customerDialog).dialog({  
	    title: '选择会员购买计次卡',  
	    width:800,
	    height:400,
	    closed: true,  
	    cache: false,  
	    modal: true,
	    closable:false,
	    toolbar:[{
			text:'选择',
			iconCls:'icon-ok',
			handler:function(){
				onSelectCustomerOK();
			}
		},{
			text:'退出',
			iconCls:'icon-exit',
			handler:function(){
				$(customerDialog).dialog('close');
			}
		}],
		onClose:function(){
			$(customerDialog).form('clear');
			$(customerList).datagrid('loadData',{rows:[],total:0});
		}
	}); 
	  //加载列表
	  $(customerList).datagrid({
		  	singleSelect:true,
			method:"POST",
			nowrap:true,
			striped: true,
			collapsible:true,
			pagination:true,
			rownumbers:true,
			fit:true,
			columns:[[
				{field:'customerCode',title:'会员号',width:100,align:"center"},
				{field:'customerName',title:'会员名称',width:100,align:"center"},
				{field:'countCardTypeName',title:'计次卡类型',width:100,align:"center"},
				{field:'amount',title:'档次',width:100,align:"center"},
				{field:'canSaleCount',title:'购买消费次数',width:100,align:"center"},
				{field:'alreadySaleCount',title:'已消费次数',width:100,align:"center"},
				{field:'canCount',title:'可消费次数',width:100,align:"center"}
				
			]],
			onDblClickRow:function(rowIndex,rowData){
				onSelectCustomerOK();
			}
	  });
	
	var onSearchCustomer = function(){
		var customerCode = $('#customerCodeSearch',customerDialog).val();
		var customerName = $('#customerNameSearch',customerDialog).val();
		var content = {customerCode:customerCode,customerName:customerName};
		$(customerList).datagrid({
			url:"customer/queryCustomerCustomerBuyCountCard.do",
			queryParams:content
		});
	};
	$('#searchCustomerBtn_'+id,customerDialog).click(function(){
		onSearchCustomer();
	});
	var onSelectCustomerOK = function(){
		var selectCustomer = $(customerList).datagrid('getSelected');
		if(selectCustomer==null){
			 $.messager.alert('警告',"请选择会员购买计次卡","warning");
			 return;
		}
		$('#customerBuyCountCardId',editDialog).val(selectCustomer.customerBuyCountCardId);
		$('#customerCode',editDialog).val(selectCustomer.customerCode);
		$('#customerName',editDialog).val(selectCustomer.customerName);
		$('#countCardTypeName',editDialog).val(selectCustomer.countCardTypeName);
		$('#amount',editDialog).val(selectCustomer.amount);
		$('#canSaleCount',editDialog).val(selectCustomer.canSaleCount);
		$('#alreadySaleCount',editDialog).val(selectCustomer.alreadySaleCount);
		$('#canCount',editDialog).val(selectCustomer.canCount);
		$(customerDialog).dialog('close');
	};

	
	//---联合结账e---
	var rights = null;
	var checkBtnRight = function(){
	};
	checkBtnRight();
  };
})(jQuery);   