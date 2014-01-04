// 创建一个闭包  
(function($) {  
  // 插件的定义  
  $.fn.salesInit = function() {
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
		  	url:"sale/querySale.do",
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
			    {field:'saleDate',title:'消费日期',width:150,align:"center"},
			    {field:'customerName',title:'会员名称',width:100,align:"center"},
			    {field:'sourceAmount',title:'消费原价',width:100,align:"center"},
				{field:'discount',title:'折扣',width:100,align:"center"},
				{field:'amount',title:'消费金额',width:100,align:"center"},
				{field:'userName',title:'录入人',width:100,align:"center"}
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
	    title: '编辑消费',  
	    width:800,
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
	}
	//添加
	var onAdd = function(){
		var now = nowTimestamp();
		$('#saleDate',editForm).val(now);
		$('#userId',editForm).val(Customers.getCurrUser().userId);
		$('#userName',editForm).val(Customers.getCurrUser().userName);
		$(editDialog).dialog('open');
	};
	//保存前的赋值操作
	var setValue = function(){
		var saleDate = $.trim($('#saleDate',editForm).val());
		if(''==saleDate){
			$.messager.alert('提示','请填写消费日期','warning');
			return false;
		}
		var customerId = $.trim($('#customerId',editForm).val());
		if(''==customerId){
			$.messager.alert('提示','请选择会员','warning');
			return false;
		}
		var sourceAmount = $.trim($('#sourceAmount',editForm).val());
		if(''==sourceAmount){
			$.messager.alert('提示','请填写消费明细','warning');
			return false;
		}
		var discount = $.trim($('#discount',editForm).val());
		if(''==discount){
			$.messager.alert('提示','请填写折扣','warning');
			return false;
		}
		var amount = $.trim($('#amount',editForm).val());
		if(''==amount){
			$.messager.alert('提示','请填写消费金额','warning');
			return false;
		}
		return true;
	};
	//保存
	var onSave = function(){
		$(editForm).form('submit',{
			url:'sale/saveSale.do',
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
				idArray.push(rows[i].saleId);
			}
			var ids = idArray.join(CSIT.join);
			var url = "sale/mulDeleteSale.do";
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
	//-------------选择会员-----------------
	var customerDialog = $('#customerDialog_'+id,$this);
	var customerList =  $('#customerList_'+id,customerDialog);
	//编辑框
	$(customerDialog).dialog({  
	    title: '选择会员',  
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
				{field:'customerTypeName',title:'会员类型',width:100,align:"center"},
				{field:'discount',title:'折扣',width:100,align:"center"}
				
			]],
			onDblClickRow:function(rowIndex,rowData){
				onSelectCustomerOK();
			}
	  });
	
	$('#customerAddBtn_'+id,editDialog).click(function(){
		$(customerDialog).dialog('open');
		 $(customerList).datagrid({
			 url:"customer/queryCustomer.do"
		 });
	})
	var onSelectCustomerOK = function(){
		var selectCustomer = $(customerList).datagrid('getSelected');
		if(selectCustomer==null){
			 $.messager.alert('警告',"请选择会员","warning");
			 return;
		}
		$('#customerId',editDialog).val(selectCustomer.customerId);
		$('#customerName',editDialog).val(selectCustomer.customerName);
		$('#discount',editDialog).numberbox('setValue', selectCustomer.discount);
		$(customerDialog).dialog('close');
	}
	//----------检查权限--------------
	var rights = null;
	var checkBtnRight = function(){
	};
	checkBtnRight();
  };
})(jQuery);   