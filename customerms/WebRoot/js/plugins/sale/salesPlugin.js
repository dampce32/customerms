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
			singleSelect:true,
			fit:true,
			toolbar:'#tb_'+id,
			columns:[[
			    {field:'ck',checkbox:true},
			    {field:'saleDate',title:'消费日期',width:150,align:"center"},
			    {field:'customerName',title:'会员名称',width:100,align:"center"},
			    {field:'notIntoDiscountAmount',title:'不打折金额',width:100,align:"center"},
			    {field:'intoDiscountAmount',title:'打折金额',width:100,align:"center"},
				{field:'discount',title:'折扣',width:100,align:"center"},
				{field:'amount',title:'消费金额',width:100,align:"center"},
				{field:'payByCard',title:'会员卡付款金额',width:100,align:"center"},
				{field:'payByCash',title:'现金付款金额',width:100,align:"center"},
				{field:'balance',title:'消费后会员卡余额',width:100,align:"center"},
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
			}
	  });
	  //添加
	  $('#addSale_'+id,$this).click(function(){
		  onAddSale();
	  });
	  //添加退费
	  $('#addRej_'+id,$this).click(function(){
		  onAddRej();
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
	    height:height-10,
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
			$(saleItemDetailList).datagrid('loadData',{rows:[],total:0});
			search();
		}
	}); 
	var initCombobox=function(){
	}
	//添加
	var onAddSale = function(){
		var now = nowTimestamp();
		$(editDialog).dialog({  
		    title: '添加消费'
		});
		$('#saleDate',editForm).val(now);
		$('#payType',editForm).val('addSale');
		$('#userId',editForm).val(Customers.getCurrUser().userId);
		$('#intoDiscountAmount',editForm).numberbox('setValue',0);
		$('#notIntoDiscountAmount',editForm).numberbox('setValue',0);
		$('#amount',editForm).numberbox('setValue',0);
		$('#discount',editForm).numberbox('setValue',0);
		$('#userId',editForm).val(Customers.getCurrUser().userId);
		$('#userName',editForm).val(Customers.getCurrUser().userName);
		
		$('#customerAddBtn_'+id,editDialog).linkbutton('enable');
		$('#selectSaleItemBtn_'+id,editDialog).linkbutton('enable');
		$('#deleteSaleItemDetailBtn_'+id,editDialog).linkbutton('enable');
		$('#selectGoodsBtn_'+id,editDialog).linkbutton('enable');
		$('#deleteSaleGoodsDetailBtn_'+id,editDialog).linkbutton('enable');
		$(editDialog).dialog('open');
	};
	//保存前的赋值操作
	var setValue = function(){
		var saleId = $.trim($('#saleId',editForm).val());
		if(!saleId){
			var customerId = $.trim($('#customerId',editForm).val());
			if(''==customerId){
				$.messager.alert('提示','请选择会员','warning');
				return false;
			}
			var notIntoDiscountAmount = $('#notIntoDiscountAmount',editForm).numberbox('getValue');
			var intoDiscountAmount = $('#intoDiscountAmount',editForm).numberbox('getValue');
			if(0==notIntoDiscountAmount&&0==intoDiscountAmount){
				$.messager.alert('提示','请填写消费明细','warning');
				return false;
			}
			var discount = $.trim($('#discount',editForm).val());
			if(''==discount){
				$.messager.alert('提示','请填写折扣','warning');
				return false;
			}
			var amount = parseFloat($('#amount',editForm).numberbox('getValue'));
			if(0==amount){
				$.messager.alert('提示','请填写消费金额','warning');
				return false;
			}
			//消费项目明细
			//验证添加的消费项目
			if(lastIndexSaleItemDetail!=null){
				$(saleItemDetailList).datagrid('endEdit', lastIndexSaleItemDetail);
			}
			$(saleItemDetailList).datagrid('unselectAll');
			lastIndexSaleItemDetail = null;
			var rows = $(saleItemDetailList).datagrid('getRows');
			var saleItemDetailIdArray = new Array();
			var saleItemIdArray = new Array();
			var amountArray = new Array();
			var isDiscountArray = new Array();
			var userIdArray = new Array();
			for ( var i = 0; i < rows.length; i++) {
				saleItemDetailIdArray.push(rows[i].saleItemDetailId);
				saleItemIdArray.push(rows[i].saleItemId);
				amountArray.push(rows[i].amount);
				isDiscountArray.push(rows[i].isDiscount);
				userIdArray.push(rows[i].userId);
			}
			var delSaleItemDetailIdArray = new Array();
			//统计原记录中被删除的记录
			for ( var i = 0; i < oldSaleItemDetailIdArray.length; i++) {
				var haveDel = true;
				for(var j=0;j<rows.length;j++){
					if(oldSaleItemDetailIdArray[i]==rows[j].saleItemDetailId){
						haveDel = false;
						break;
					}
				}
				if(haveDel){
					delSaleItemDetailIdArray.push(oldSaleItemDetailIdArray[i]);
				}
			}
			$('#saleItemDetailIds',editForm).val(saleItemDetailIdArray.join(CSIT.join));
			$('#saleItemIds',editForm).val(saleItemIdArray.join(CSIT.join));
			$('#amounts',editForm).val(amountArray.join(CSIT.join));
			$('#isDiscounts',editForm).val(isDiscountArray.join(CSIT.join));
			$('#userIds',editForm).val(userIdArray.join(CSIT.join));
			$('#delSaleItemDetailIds',editForm).val(delSaleItemDetailIdArray.join(CSIT.join));
			
			//消费产品明细
			if(lastIndexSaleGoodsDetail!=null){
				$(saleGoodsDetailList).datagrid('endEdit', lastIndexSaleGoodsDetail);
			}
			$(saleGoodsDetailList).datagrid('unselectAll');
			lastIndexSaleGoodsDetail = null;
			var rows = $(saleGoodsDetailList).datagrid('getRows');
			var saleGoodsDetailIdArray = new Array();
			var goodsIdArray = new Array();
			var amountArray = new Array();
			var isDiscountArray = new Array();
			var userIdArray = new Array();
			for ( var i = 0; i < rows.length; i++) {
				saleGoodsDetailIdArray.push(rows[i].saleGoodsDetailId);
				goodsIdArray.push(rows[i].goodsId);
				amountArray.push(rows[i].amount);
				isDiscountArray.push(rows[i].isDiscount);
				userIdArray.push(rows[i].userId);
			}
			var delSaleGoodsDetailIdArray = new Array();
			//统计原记录中被删除的记录
			for ( var i = 0; i < oldSaleGoodsDetailIdArray.length; i++) {
				var haveDel = true;
				for(var j=0;j<rows.length;j++){
					if(oldSaleGoodsDetailIdArray[i]==rows[j].saleGoodsDetailId){
						haveDel = false;
						break;
					}
				}
				if(haveDel){
					delSaleGoodsDetailIdArray.push(oldSaleGoodsDetailIdArray[i]);
				}
			}
			$('#saleGoodsDetailIds',editForm).val(saleGoodsDetailIdArray.join(CSIT.join));
			$('#goodsIds',editForm).val(goodsIdArray.join(CSIT.join));
			$('#amountsGoods',editForm).val(amountArray.join(CSIT.join));
			$('#isDiscountsGoods',editForm).val(isDiscountArray.join(CSIT.join));
			$('#userIdsGoods',editForm).val(userIdArray.join(CSIT.join));
			$('#delSaleGoodsDetailIds',editForm).val(delSaleGoodsDetailIdArray.join(CSIT.join));
			
			var customerAmount  = parseFloat($('#customerAmount',editForm).numberbox('getValue'));
			var saleId = $('#saleId',editForm).val();
			if(saleId==''){
				if(customerAmount<amount){
					$('#customerName',amountLessDialog).val($('#customerName',editDialog).val());
					$('#customerAmount',amountLessDialog).numberbox('setValue',customerAmount);
					$('#customerAmountLabel',amountLessDialog).html(customerAmount);
					$('#amount',amountLessDialog).numberbox('setValue',amount);
					$('#lessAmount',amountLessDialog).numberbox('setValue',amount-customerAmount);
					$('#lessAmountLabel',amountLessDialog).html((amount-customerAmount).toFixed(2));
					
					$(amountLessDialog).dialog('open');
					return false;
				}
			}
		}else{//只能修改消费日期
			var saleDate = $.trim($('#saleDate',editForm).val());
			if(''==saleDate){
				$.messager.alert('提示','请填写消费日期','warning');
				return false;
			}
		}
		$(editDialog).mask({maskMsg:'正在保存'});
		return true;
	};
	
	//打开
	var onOpen = function(saleId){
		var url = 'sale/initSale.do';
		var content ={saleId:saleId};
		asyncCallService(url,content,function(result){
			if(result.isSuccess){
				var data = result.data;
				var saleData = eval("("+data.saleData+")");
				$('#saleId',editDialog).val(saleData.saleId);
				$('#saleDate',editDialog).val(saleData.saleDate);
				$('#customerId',editDialog).val(saleData.customerId);
				$('#customerName',editDialog).val(saleData.customerName);
				$('#customerAmount',editDialog).numberbox('setValue',saleData.customerAmount);
				$('#notIntoDiscountAmount',editDialog).numberbox('setValue',saleData.notIntoDiscountAmount);
				$('#intoDiscountAmount',editDialog).numberbox('setValue',saleData.intoDiscountAmount);
				$('#discount',editDialog).numberbox('setValue',saleData.discount);
				$('#amount',editDialog).numberbox('setValue',saleData.amount);
				$('#userName',editDialog).val(saleData.userName);
				
				var saleItemDetailData = eval("("+data.saleItemDetailData+")");
				$(saleItemDetailList).datagrid('loadData',saleItemDetailData);
				
				var saleGoodsDetailData = eval("("+data.saleGoodsDetailData+")");
				$(saleGoodsDetailList).datagrid('loadData',saleGoodsDetailData);
			}else{
				$.messager.alert('提示',result.message,'error');
			}
		});
	}
	//保存
	var onSave = function(saveType){
		$(editForm).form('submit',{
			url:'sale/saveSale.do',
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
	//修改
	var onUpdate = function(){
		if(!$('#update_'+id,$this).is(":hidden")){
			if(selectRow==null){
				$.messager.alert("警告","请选择数据行",'warning');
				return;
			}
			initCombobox();
			onOpen(selectRow.saleId);
			$(editDialog).dialog('open');
			
			$('#customerAddBtn_'+id,editDialog).linkbutton('disable');
			$('#selectSaleItemBtn_'+id,editDialog).linkbutton('disable');
			$('#deleteSaleItemDetailBtn_'+id,editDialog).linkbutton('disable');
			$('#selectGoodsBtn_'+id,editDialog).linkbutton('disable');
			$('#deleteSaleGoodsDetailBtn_'+id,editDialog).linkbutton('disable');
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
		$.messager.confirm("提示！","确定要删除选中的记录，删除后，消费金额将加到会员卡余额中？",function(t){ 
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
	//---选择会员s---
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
				{field:'discount',title:'折扣',width:100,align:"center"},
				{field:'amount',title:'会员卡余额',width:100,align:"center"}
				
			]],
			onDblClickRow:function(rowIndex,rowData){
				onSelectCustomerOK();
			}
	  });
	
	$('#customerAddBtn_'+id,editDialog).click(function(){
		var disabled = $('#customerAddBtn_'+id,editDialog).linkbutton('options').disabled;
		if(disabled==false){
			$(customerDialog).dialog('open');
			onSearchCustomer();
		}
	});
	var onSearchCustomer = function(){
		var customerCode = $('#customerCodeSearch',customerDialog).val();
		var customerName = $('#customerNameSearch',customerDialog).val();
		var content = {customerCode:customerCode,customerName:customerName};
		$(customerList).datagrid({
			url:"customer/queryCustomer.do",
			queryParams:content
		});
	};
	$('#searchCustomerBtn_'+id,customerDialog).click(function(){
		onSearchCustomer();
	});
	var onSelectCustomerOK = function(){
		var selectCustomer = $(customerList).datagrid('getSelected');
		if(selectCustomer==null){
			 $.messager.alert('警告',"请选择会员","warning");
			 return;
		}
		$('#customerId',editDialog).val(selectCustomer.customerId);
		$('#customerName',editDialog).val(selectCustomer.customerName);
		$('#discount',editDialog).numberbox('setValue', selectCustomer.discount);
		$('#customerAmount',editDialog).numberbox('setValue', selectCustomer.amount);
		$(customerDialog).dialog('close');
	};
	//---选择会员e---
	//修改折扣
	$('#discount',editDialog).numberbox({onChange: function(newValue,oldValue){
		var notIntoDiscountAmount= $('#notIntoDiscountAmount',editDialog).numberbox('getValue');
		var intoDiscountAmount= $('#intoDiscountAmount',editDialog).numberbox('getValue');
		$('#amount',editDialog).numberbox('setValue',Math.round(parseFloat(notIntoDiscountAmount)+intoDiscountAmount*parseFloat(newValue)/10));
	}});
	//----------消费项目--------------
	var saleItemDetailList = $('#saleItemDetailList_'+id,editDialog);
	var lastIndexSaleItemDetail=null;
	var oldSaleItemDetailIdArray = new Array();
	//加载列表
	$(saleItemDetailList).datagrid({
	  	singleSelect:true,
		method:"POST",
		nowrap:true,
		striped: true,
		collapsible:true,
		rownumbers:true,
		fit:true,
		toolbar: [{id:'selectSaleItemBtn_'+id,text:'添加',iconCls: 'icon-add',handler: function(){onSelectSaleItem();}},
		          {id:'deleteSaleItemDetailBtn_'+id,text:'删除',iconCls: 'icon-remove',handler: function(){onDeleteSaleItemDetail();}}
		],
		columns:[[
			{field:'saleItemName',title:'消费项目',width:100,align:"center"},
			{field:'amount',title:'消费金额',width:100,align:"center",editor:{type:'numberbox',options:{precision:2}}},
			{field:'isDiscount',title:'是否打折',width:100,align:"center",editor:{type:'combobox',options:{
				valueField: 'value',
				textField: 'label',
				data: [{
					label: '打折',
					value: '1'
				},{
					label: '不打折',
					value: '0'
				}],
			    	onChange:function(newValue,oldValue){
			    		var selectSaleItemDetailRow = $(saleItemDetailList).datagrid('getSelected');
			    		var rowIndex = $(saleItemDetailList).datagrid('getRowIndex',selectSaleItemDetailRow);
			    		calculate(rowIndex,newValue,'isDiscount');
					}
				}},formatter: function(value,row,index){
					if(value==1){
						return "打折";
					}else if(value==0){
						return "不打折";
					}
				}},
			{field:'userId',title:'技师',width:100,align:"center",editor:{type:'combobox',options:{
				valueField: 'userId',
				textField: 'userName',
				data: Customers.getUserList()}},
				formatter: function(value,row,index){
					var users = Customers.getUserList();
					for(var user in users){
						if(users[user].userId==value){
							return users[user].userName;
						}
					}
				}
			}
		]],
		  onBeforeLoad:function(){
				$(this).datagrid('rejectChanges');
		  },
		  onClickRow:function(rowIndex){
			var saleId = $('#saleId',editDialog).val();
			if(!saleId){
				if (lastIndexSaleItemDetail != rowIndex){
					if(lastIndexSaleItemDetail!=null){
						$(saleItemDetailList).datagrid('endEdit', lastIndexSaleItemDetail);
					}
					$(saleItemDetailList).datagrid('beginEdit', rowIndex);
					setEditingSaleItemDetail(rowIndex);
				}
				lastIndexSaleItemDetail = rowIndex;
			}
		  },onLoadSuccess:function(data){
				var rows = data.rows;
				oldSaleItemDetailIdArray = new Array();
				for ( var i = 0; i < rows.length; i++) {
					oldSaleItemDetailIdArray.push(rows[i].saleItemDetailId);
				}
			}
	});
	function setEditingSaleItemDetail(rowIndex){  
	    var amountEditor =  $(saleItemDetailList).datagrid('getEditor', {index:rowIndex,field:'amount'});
	    var isDiscountEditor =  $(saleItemDetailList).datagrid('getEditor', {index:rowIndex,field:'isDiscount'});
	    $(amountEditor.target).numberbox({
	    	onChange:function(newValue,oldValue){
	    		calculate(rowIndex,newValue,'amount');
			}
	    });
	}
	var calculate = function(rowIndex,newValue,changeField){
		var saleItemDetailRows = $(saleItemDetailList).datagrid('getRows');
		var saleGoodsDetailRows = $(saleGoodsDetailList).datagrid('getRows');
		var intoDiscountAmountTotal = 0;
		var notIntoDiscountAmountTotal = 0;
		var discount = $('#discount',editDialog).numberbox('getValue');
		
		if(rowIndex<0){//不属于行字段改变
			for ( var i = 0; i < saleItemDetailRows.length; i++) {
				if(saleItemDetailRows[i].isDiscount==1){
					intoDiscountAmountTotal+=parseFloat(saleItemDetailRows[i].amount);
				}else{
					notIntoDiscountAmountTotal+=parseFloat(saleItemDetailRows[i].amount);
				}
			}
			for ( var i = 0; i < saleGoodsDetailRows.length; i++) {
				if(saleGoodsDetailRows[i].isDiscount==1){
					intoDiscountAmountTotal+=parseFloat(saleGoodsDetailRows[i].amount);
				}else{
					notIntoDiscountAmountTotal+=parseFloat(saleGoodsDetailRows[i].amount);
				}
			}
		}else{
			if('amount'==changeField||'isDiscount'==changeField){//消费项目
				var amountEditor =  $(saleItemDetailList).datagrid('getEditor', {index:rowIndex,field:'amount'});
			    var isDiscountEditor =  $(saleItemDetailList).datagrid('getEditor', {index:rowIndex,field:'isDiscount'});
			    var amount = $(amountEditor.target).numberbox('getValue');
			    var isDiscount = $(isDiscountEditor.target).combobox('getValue');
				//消费产品
				for ( var i = 0; i < saleGoodsDetailRows.length; i++) {
					if(saleGoodsDetailRows[i].isDiscount==1){
						intoDiscountAmountTotal+=parseFloat(saleGoodsDetailRows[i].amount);
					}else{
						notIntoDiscountAmountTotal+=parseFloat(saleGoodsDetailRows[i].amount);
					}
				}
				for ( var i = 0; i < saleItemDetailRows.length; i++) {
					if(i!=rowIndex){
						if(saleItemDetailRows[i].isDiscount==1){
							intoDiscountAmountTotal+=parseFloat(saleItemDetailRows[i].amount);
						}else{
							notIntoDiscountAmountTotal+=parseFloat(saleItemDetailRows[i].amount);
						}
					}else{
						if(isDiscount==1){
							intoDiscountAmountTotal+=parseFloat(amount);
						}else{
							notIntoDiscountAmountTotal+=parseFloat(amount);
						}
					}
				}
			}else if('amountGoods'==changeField||'isDiscountGoods'==changeField){//消费产品
				//消费项目
				for ( var i = 0; i < saleItemDetailRows.length; i++) {
					if(saleItemDetailRows[i].isDiscount==1){
						intoDiscountAmountTotal+=parseFloat(saleItemDetailRows[i].amount);
					}else{
						notIntoDiscountAmountTotal+=parseFloat(saleItemDetailRows[i].amount);
					}
				}
				var amountEditor =  $(saleGoodsDetailList).datagrid('getEditor', {index:rowIndex,field:'amount'});
			    var isDiscountEditor =  $(saleGoodsDetailList).datagrid('getEditor', {index:rowIndex,field:'isDiscount'});
			    var amount = $(amountEditor.target).numberbox('getValue');
			    var isDiscount = $(isDiscountEditor.target).combobox('getValue');
				for ( var i = 0; i < saleGoodsDetailRows.length; i++) {
					if(i!=rowIndex){
						if(saleGoodsDetailRows[i].isDiscount==1){
							intoDiscountAmountTotal+=parseFloat(saleGoodsDetailRows[i].amount);
						}else{
							notIntoDiscountAmountTotal+=parseFloat(saleGoodsDetailRows[i].amount);
						}
					}else{
						if(isDiscount==1){
							intoDiscountAmountTotal+=parseFloat(amount);
						}else{
							notIntoDiscountAmountTotal+=parseFloat(amount);
						}
					}
				}
			}
		}
		$('#intoDiscountAmount',editDialog).numberbox('setValue',intoDiscountAmountTotal);
		$('#notIntoDiscountAmount',editDialog).numberbox('setValue',notIntoDiscountAmountTotal);
		$('#amount',editDialog).numberbox('setValue',Math.round(notIntoDiscountAmountTotal+intoDiscountAmountTotal*discount/10));
	};
	var onSelectSaleItem = function(){
		if(checkCustomerChoosed()){
			var addedSaleItems = $(saleItemDetailList).datagrid('getRows');
			var addedSaleItemArray = new Array();
			for (var saleItem in addedSaleItems) {
				addedSaleItemArray.push(addedSaleItems[saleItem].saleItemId);
			}
			var ids=addedSaleItemArray.join(CSIT.join);
			$(saleItemDialog).dialog('open');
			$(saleItemList).datagrid({
				url:'dict/querySelectSaleItem.do',
				queryParams: {
					ids: ids
				}
			});
		}
	}
	//----------选择消费项目--------------
	var saleItemDialog = $('#saleItemDialog_'+id,$this);
	var saleItemList = $('#saleItemList_'+id,saleItemDialog);
	$(saleItemDialog).dialog({  
	    title: '选择消费项目',  
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
				onSelectSaleItemOK();
			}
		},{
			text:'退出',
			iconCls:'icon-exit',
			handler:function(){
				$(saleItemDialog).dialog('close');
			}
		}],
		onClose:function(){
			$(saleItemDialog).form('clear');
			$(saleItemList).datagrid('loadData',{rows:[],total:0});
		}
	}); 
	//加载列表
	$(saleItemList).datagrid({
		method:"POST",
		nowrap:true,
		striped: true,
		collapsible:true,
		rownumbers:true,
		pagination:true,
		fit:true,
		columns:[[
		    {field:'ck',checkbox:true},
			{field:'saleItemName',title:'消费项目',width:100,align:"center"},
			{field:'amount',title:'消费金额',width:100,align:"center"},
			{field:'isDiscount',title:'是否打折',width:100,align:"center",formatter: function(value,row,index){
				if (row.isDiscount==1){
					return "打折";
				} else if (row.isDiscount==0){
					return "不打折";
				}
			}}
		]]
	});
	var onSelectSaleItemOK = function(){
		var rows = $(saleItemList).datagrid('getSelections');
		 if(rows.length==0){
			 $.messager.alert('提示','请选择消费项目',"warning");
			 return;
		 }
		 var userId = Customers.getCurrUser().userId;
		 for ( var i = 0; i < rows.length; i++) {
			var row = rows[i];
			 $(saleItemDetailList).datagrid('appendRow',{
				 saleItemDetailId:'',
				 saleItemId:row.saleItemId,
				 saleItemName:row.saleItemName,
				 amount:row.amount,
				 isDiscount:row.isDiscount,
				 userId:userId
			});
		}
		if(lastIndexSaleItemDetail!=null){
			$(saleItemDetailList).datagrid('endEdit', lastIndexSaleItemDetail);
		}
		$(saleItemDetailList).datagrid('unselectAll');
		lastIndexSaleItemDetail = null;
		$(saleItemDialog).dialog('close');
		calculate(-2);
	}
	var onDeleteSaleItemDetail = function(){
		if(checkCustomerChoosed()){
			var selectSaleItemDetailRow = $(saleItemDetailList).datagrid('getSelected');
			if(selectSaleItemDetailRow==null){
				$.messager.alert('提示','请选中要删除的消费项目明细',"warning");
				return;
			}
			var rowIndex = $(saleItemDetailList).datagrid('getRowIndex',selectSaleItemDetailRow);
			$(saleItemDetailList).datagrid('deleteRow',rowIndex);
			lastIndexSaleItemDetail = null;
			calculate(-2);
		}
	}
	//----------消费产品--------------
	var saleGoodsDetailList = $('#saleGoodsDetailList_'+id,editDialog);
	var lastIndexSaleGoodsDetail=null;
	var oldSaleGoodsDetailIdArray = new Array();
	//加载列表
	$(saleGoodsDetailList).datagrid({
	  	singleSelect:true,
		method:"POST",
		nowrap:true,
		striped: true,
		collapsible:true,
		rownumbers:true,
		fit:true,
		toolbar: [{id:'selectGoodsBtn_'+id,text:'添加',iconCls: 'icon-add',handler: function(){onSelectGoods();}},
		          {id:'deleteSaleGoodsDetailBtn_'+id,text:'删除',iconCls: 'icon-remove',handler: function(){onDeleteSaleGoodsDetail();}}
		],
		columns:[[
			{field:'goodsName',title:'消费产品',width:100,align:"center"},
			{field:'amount',title:'消费金额',width:100,align:"center",editor:{type:'numberbox',options:{precision:2}}},
			{field:'isDiscount',title:'是否打折',width:100,align:"center",editor:{type:'combobox',options:{
				valueField: 'value',
				textField: 'label',
				data: [{
					label: '打折',
					value: '1'
				},{
					label: '不打折',
					value: '0'
				}],
			    	onChange:function(newValue,oldValue){
			    		var selectSaleGoodsDetailRow = $(saleGoodsDetailList).datagrid('getSelected');
			    		var rowIndex = $(saleGoodsDetailList).datagrid('getRowIndex',selectSaleGoodsDetailRow);
			    		calculate(rowIndex,newValue,'isDiscountGoods');
					}
				}},formatter: function(value,row,index){
					if(value==1){
						return "打折";
					}else if(value==0){
						return "不打折";
					}
				}},
			{field:'userId',title:'技师',width:100,align:"center",editor:{type:'combobox',options:{
				valueField: 'userId',
				textField: 'userName',
				data: Customers.getUserList()}},
				formatter: function(value,row,index){
					var users = Customers.getUserList();
					for(var user in users){
						if(users[user].userId==value){
							return users[user].userName;
						}
					}
				}
			}
		]],
		  onBeforeLoad:function(){
				$(this).datagrid('rejectChanges');
		  },
		  onClickRow:function(rowIndex){
			  var saleId = $('#saleId',editDialog).val();
			  if(!saleId){
				  if (lastIndexSaleGoodsDetail != rowIndex){
					if(lastIndexSaleGoodsDetail!=null){
						$(saleGoodsDetailList).datagrid('endEdit', lastIndexSaleGoodsDetail);
					}
					$(saleGoodsDetailList).datagrid('beginEdit', rowIndex);
					setEditingSaleGoodsDetail(rowIndex);
				  }
				  lastIndexSaleGoodsDetail = rowIndex;
			  }
		  },onLoadSuccess:function(data){
				var rows = data.rows;
				oldSaleGoodsDetailIdArray = new Array();
				for ( var i = 0; i < rows.length; i++) {
					oldSaleGoodsDetailIdArray.push(rows[i].saleGoodsDetailId);
				}
			}
	});
	function setEditingSaleGoodsDetail(rowIndex){  
	    var amountEditor =  $(saleGoodsDetailList).datagrid('getEditor', {index:rowIndex,field:'amount'});
	    var isDiscountEditor =  $(saleGoodsDetailList).datagrid('getEditor', {index:rowIndex,field:'isDiscount'});
	    $(amountEditor.target).numberbox({
	    	onChange:function(newValue,oldValue){
	    		calculate(rowIndex,newValue,'amountGoods');
			}
	    });
	}
	var onSelectGoods = function(){
		if(checkCustomerChoosed()){
			var addedGoodss = $(saleGoodsDetailList).datagrid('getRows');
			var addedGoodsArray = new Array();
			for (var saleGoods in addedGoodss) {
				addedGoodsArray.push(addedGoodss[saleGoods].goodsId);
			}
			var ids=addedGoodsArray.join(CSIT.join);
			$(goodsDialog).dialog('open');
			$(goodsList).datagrid({
				url:'dict/querySelectGoods.do',
				queryParams: {
					ids: ids
				}
			});
		}
	}
	//----------选择消费产品--------------
	var goodsDialog = $('#goodsDialog_'+id,$this);
	var goodsList = $('#goodsList_'+id,goodsDialog);
	$(goodsDialog).dialog({  
	    title: '选择消费产品',  
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
				onSelectGoodsOK();
			}
		},{
			text:'退出',
			iconCls:'icon-exit',
			handler:function(){
				$(goodsDialog).dialog('close');
			}
		}],
		onClose:function(){
			$(goodsDialog).form('clear');
			$(goodsList).datagrid('loadData',{rows:[],total:0});
		}
	}); 
	//加载列表
	$(goodsList).datagrid({
		method:"POST",
		nowrap:true,
		striped: true,
		collapsible:true,
		rownumbers:true,
		pagination:true,
		fit:true,
		columns:[[
		    {field:'ck',checkbox:true},
			{field:'goodsName',title:'产品名称',width:100,align:"center"},
			{field:'amount',title:'消费金额',width:100,align:"center"},
			{field:'isDiscount',title:'是否打折',width:100,align:"center",formatter: function(value,row,index){
				if (row.isDiscount==1){
					return "打折";
				} else if (row.isDiscount==0){
					return "不打折";
				}
			}}
		]]
	});
	var onSelectGoodsOK = function(){
		var rows = $(goodsList).datagrid('getSelections');
		 if(rows.length==0){
			 $.messager.alert('提示','请选择消费项目',"warning");
			 return;
		 }
		 var userId = Customers.getCurrUser().userId;
		 for ( var i = 0; i < rows.length; i++) {
			var row = rows[i];
			 $(saleGoodsDetailList).datagrid('appendRow',{
				 saleGoodsDetailId:'',
				 goodsId:row.goodsId,
				 goodsName:row.goodsName,
				 amount:row.amount,
				 isDiscount:row.isDiscount,
				 userId:userId
			});
		}
		if(lastIndexSaleGoodsDetail!=null){
			$(saleGoodsDetailList).datagrid('endEdit', lastIndexSaleGoodsDetail);
		}
		$(saleGoodsDetailList).datagrid('unselectAll');
		lastIndexSaleGoodsDetail = null;
		$(goodsDialog).dialog('close');
		calculate(-2);
	}
	var onDeleteSaleGoodsDetail = function(){
		if(checkCustomerChoosed()){
			var selectSaleGoodsDetailRow = $(saleGoodsDetailList).datagrid('getSelected');
			if(selectSaleGoodsDetailRow==null){
				$.messager.alert('提示','请选中要删除的消费产品明细',"warning");
				return;
			}
			var rowIndex = $(saleGoodsDetailList).datagrid('getRowIndex',selectSaleGoodsDetailRow);
			$(saleGoodsDetailList).datagrid('deleteRow',rowIndex);
			lastIndexSaleGoodsDetail = null;
			calculate(-2);
		}
	}
	//消费项目和消费产品切换
	$('#saleTabs_'+id).tabs({
		onSelect:function(title,index){
			if('消费项目'==title){
				if(lastIndexSaleItemDetail!=null){
					$(saleItemDetailList).datagrid('endEdit',lastIndexSaleItemDetail);
				}
				$(saleItemDetailList).datagrid('unselectAll');
				lastIndexSaleItemDetail = null;
			}else if('消费产品'==title){
				if(lastIndexSaleGoodsDetail!=null){
					$(saleItemDetailList).datagrid('endEdit',lastIndexSaleGoodsDetail);
				}
				$(saleGoodsDetailList).datagrid('unselectAll');
				lastIndexSaleGoodsDetail = null;
			}
		}
	})
	//----------充值-----------------
	//余额不足
	var amountLessDialog = $('#amountLessDialog_'+id,$this);
	$(amountLessDialog).dialog({  
	    title: '余额不足',  
	    width:600,
	    height:276,
	    closed: true,  
	    cache: false,  
	    modal: true,
	    closable:false,
		onClose:function(){
			$(amountLessDialog).form('clear');
		}
	}); 
	//检查会员是否已选择
	var checkCustomerChoosed = function(){
		var customerId = $('#customerId',editDialog).val();
		if(customerId==''){
			$.messager.alert('提示','请先选择会员',"warning");
			 return false;
		}
		return true;
	}
	$('#exitAmountLessDialogBtn_'+id,amountLessDialog).click(function(){
		$(amountLessDialog).dialog('close');
	})
	$('#rechargeBtn_'+id,editDialog).click(function(){
		$(rechargeDialog).dialog('open');
	});
	
	//----------充值-----------------
	var rechargeDialog = $('#rechargeDialog_'+id,$this);
	$(rechargeDialog).dialog({  
	    title: '会员充值',  
	    width:400,
	    height:276,
	    closed: true,  
	    cache: false,  
	    modal: true,
	    closable:false,
		onClose:function(){
			$(rechargeDialog).form('clear');
		},
		toolbar:[{
			text:'充值',
			iconCls:'icon-ok',
			handler:function(){
				onRecharge();
			}
		},{
			text:'退出',
			iconCls:'icon-exit',
			handler:function(){
				$(rechargeDialog).dialog('close');
			}
		}]
	}); 
	$('#rechargePayBtn_'+id,amountLessDialog).click(function(){
		$('#customerId',rechargeDialog).val($('#customerId',editDialog).val());
		$('#customerName',rechargeDialog).val($('#customerName',amountLessDialog).val());
		$('#customerAmount',rechargeDialog).numberbox('setValue',$('#customerAmount',amountLessDialog).numberbox('getValue'));
		$('#amountSale',rechargeDialog).numberbox('setValue',$('#amount',amountLessDialog).numberbox('getValue'));
		$('#lessAmount',rechargeDialog).numberbox('setValue',$('#lessAmount',amountLessDialog).numberbox('getValue'));
		$('#amount',rechargeDialog).numberbox('setValue',$('#lessAmount',amountLessDialog).numberbox('getValue'));
		$(rechargeDialog).dialog('open');
	});
	var onRechargeSetValue = function(){
		var amount = parseFloat($('#amount',rechargeDialog).numberbox('getValue'));
		var lessAmount = parseFloat($('#lessAmount',rechargeDialog).numberbox('getValue'));
		if(amount==0){
			$.messager.alert('提示','请输入充值金额！','warning');
			return false;
		}
		if(amount<lessAmount){
			$.messager.alert('提示','不足金额：'+lessAmount+',充值金额：'+amount+'，请检查输入的充值金额！','warning');
			return false;
		}
		return true;
	};
	//保存
	var onRecharge = function(){
		$('#rechargeForm_'+id).form('submit',{
			url:'customer/saveCustomerRecharge.do',
			onSubmit: function(){
				return onRechargeSetValue();
			},
			success: function(data){
				var result = eval('('+data+')');
				if(result.isSuccess){
					var fn = function(){
						var amount = $('#amount',rechargeDialog).numberbox('getValue');
						var customerAmount  = $('#customerAmount',editDialog).numberbox('getValue');
						$('#customerAmount',editDialog).numberbox('setValue',amount+customerAmount);
						$(rechargeDialog).dialog('close');
						$(amountLessDialog).dialog('close');
						
						$(editForm).form('submit',{
							url:'sale/saveSale.do',
							success: function(data){
								var result = eval('('+data+')');
								if(result.isSuccess){
									$(editDialog).dialog('close');
								}else{
									$.messager.alert('提示',result.message,"error");
								}
							}
						});
					};
					$.messager.alert('提示','付款成功','info',fn);
				}else{
					$.messager.alert('提示',result.message,"error");
				}
			}
		});
	};
	//---联合结账s---
	var unitPayDialog = $('#unitPayDialog_'+id,$this);
	$(unitPayDialog).dialog({  
	    title: '联合结账',  
	    width:400,
	    height:276,
	    closed: true,  
	    cache: false,  
	    modal: true,
	    closable:false,
		onClose:function(){
			$(unitPayDialog).form('clear');
		},
		toolbar:[{
			text:'结账',
			iconCls:'icon-ok',
			handler:function(){
				onUnitPay();
			}
		},{
			text:'退出',
			iconCls:'icon-exit',
			handler:function(){
				$(unitPayDialog).dialog('close');
			}
		}]
	}); 
	$('#unitPayBtn_'+id,amountLessDialog).click(function(){
		$('#customerId',unitPayDialog).val($('#customerId',editDialog).val());
		$('#customerName',unitPayDialog).val($('#customerName',amountLessDialog).val());
		$('#customerAmount',unitPayDialog).numberbox('setValue',$('#customerAmount',amountLessDialog).numberbox('getValue'));
		$('#amountSale',unitPayDialog).numberbox('setValue',$('#amount',amountLessDialog).numberbox('getValue'));
		$('#lessAmount',unitPayDialog).numberbox('setValue',$('#lessAmount',amountLessDialog).numberbox('getValue'));
		$('#payByCash',unitPayDialog).numberbox('setValue',$('#lessAmount',amountLessDialog).numberbox('getValue'));
		$(unitPayDialog).dialog('open');
	});
	var onUnitPaySetValue = function(){
		var payByCash = $('#payByCash',unitPayDialog).numberbox('getValue');
		if(payByCash==''){
			$.messager.alert('提示','请输入现金付款！','warning');
			return false;
		}
		$('#payByCash',editDialog).val(payByCash);
		return true;
	};
	//保存
	var onUnitPay = function(){
		$(editForm).form('submit',{
			url:'sale/saveSale.do',
			onSubmit: function(){
				return onUnitPaySetValue();
			},
			success: function(data){
				var result = eval('('+data+')');
				if(result.isSuccess){
					var fn = function(){
						$(amountLessDialog).dialog('close');
						$(unitPayDialog).dialog('close');
						$(editDialog).dialog('close');
					};
					$.messager.alert('提示','保存成功','info',fn);
				}else{
					$.messager.alert('提示',result.message,"error");
				}
				$(editDialog).mask('hide');
			}
		});
	};
	//---联合结账e---
	var rights = null;
	var checkBtnRight = function(){
	};
	checkBtnRight();
  };
})(jQuery);   