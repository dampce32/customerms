// 创建一个闭包  
(function($) {  
  // 插件的定义  
  $.fn.usersInit = function() {
	  var $this = $(this);
	  var id =   $(this).attr('id');
	  var rightId = $(this).attr('rightId');
	  var viewList = $('#viewList_'+id,$this);
	  var editDialog = $('#editDialog_'+id,$this);
	  var editForm = $('#editForm_'+id,editDialog);
	  var queryContent = $('#queryContent_'+id,$this);
	  
	  var selectRow = null;
	  var selectIndex = null;
	//加载列表
	$(viewList).datagrid({
		url:"system/queryUser.do",
		fit:true,
		singleSelect:true,
		selectOnCheck:false,
		checkOnSelect:false,
		method:"POST",
		nowrap:true,
		striped: true,
		collapsible:true,
		rownumbers:true,
		pagination:true,
		remoteSort:false,
		toolbar:[
			{id:'add_'+id,text:'添加',iconCls:'icon-add',handler:function(){onAdd();}},
			{id:'update_'+id,text:'修改',iconCls:'icon-edit',handler:function(){onUpdate();}},
			{id:'delete_'+id,text:'删除',iconCls:'icon-remove',handler:function(){onMulDelete();}},
			{id:'userRole_'+id,text:'用户角色',iconCls:'icon-role',handler:function(){onUserRole();}},
			{id:'userRight_'+id,text:'查看权限',iconCls:'icon-role-right',handler:function(){onUserRight();}},
			{id:'resetPwd_'+id,text:'重置密码',iconCls:'icon-resetPwd',handler:function(){onMulResetPwd();}}
			
		],
		columns:[[
			{field:'ck',checkbox:true},
			{field:'status',title:'状态',width:50,sortable:true,align:"center",
				formatter: function(value,row,index){
					if (value==0){
						return '<img src="style/v1/icons/warn.png"/>';
					} else if (value==1){
						return '<img src="style/v1/icons/info.png"/>';
					}
			 }},
		    {field:'userCode',title:'用户编号',width:100,align:"center"},
			{field:'userName',title:'用户名称',width:100,align:"center"}
		]],
		onDblClickRow:function(rowIndex, rowData){
			onUpdate();
		},
		onClickRow:function(rowIndex, rowData){
			selectRow = rowData;
			selectIndex = rowIndex;
		}
	});

	//编辑框
	  $(editDialog).dialog({  
	    title: '编辑用户信息',  
	    width:400,
	    height:340,
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
			iconCls:'icon-cancel',
			handler:function(){
				$(editDialog).dialog('close');
			}
		}],
		onClose:function(){
			$(editForm).form('clear');
		}
	  });
	 var init = function(){
	 };
	//添加信息
	var onAdd = function(){
		$('#status',editForm).combobox('setValue',1);
		$(editDialog).dialog('open');
	};
	//修改
	var onUpdate = function(){
		if(selectRow==null){
			$.messager.alert("提示","请选择数据行","warning");
			return;
		}
		$(editForm).form('load',selectRow);
		$(editDialog).dialog('open');
	 };
	//删除
	var onMulDelete = function(){
		var rows = $(viewList).datagrid('getChecked');
		if(rows.length==0){
			 $.messager.alert('提示',"请选中要删除的纪录","warming");
			 return;	
		}
		$.messager.confirm("提示！","确定要删除选中的记录?",function(t){ 
			if(!t) return;
			if(rows.length==0){
				 $.messager.alert('提示',"请选中要删除的纪录","warming");
				 return;	
			}
			var idArray = new Array();
			for(var i=0;i<rows.length;i++){
				idArray.push(rows[i].userId);
			}
			var ids = idArray.join(CSIT.join);
			var url = "system/mulDeleteUser.do";
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
	//修改多个审核状态
	var onMulUpdateStatus = function(status){
		var rows =  $(viewList).datagrid('getChecked');
		var msg = '';
		if(status){
			msg = '启用';
		}else{
			msg = '禁用';
		}
		if(rows.length==0){
			$.messager.alert("提示","请选择要"+msg+"的数据行","warning");
			return;
		}
		var idArray = new Array();
		for ( var i = 0; i < rows.length; i++) {
			idArray.push(rows[i].userId);
		}
		$.messager.confirm("提示","确定要"+msg+"记录?",function(t){ 
			if(t){
				var url = 'system/mulUpdateStatusUser.do';
				var content ={ids:idArray.join(CSIT.join),status:status};
				asyncCallService(url,content,function(result){
					if(result.isSuccess){
						var fn = function(){
							search(true);
						};
						$.messager.alert('提示',msg+'成功','info',fn);
					}else{
						$.messager.alert('提示',result.message,'error');
					}
				});
			}
		});
	};
	//提交前验证
	var setValue = function(){
		var userCode = $.trim($('#userCode',editForm).val());
		if(''==userCode){
			$.messager.alert('提示','请填写用户编号','warning');
			return false;
		}
		var userName = $.trim($('#userName',editForm).val());
		if(''==userName){
			$.messager.alert('提示','请填写用户名称','warning');
			return false;
		}
		return true;
	};
	//保存
	var onSave = function(){
		$(editForm).form('submit',{
			url: 'system/saveUser.do',
			onSubmit: function(){
				return setValue();
			},
			success: function(data){
				var result = eval('(' + data + ')'); 
				if(result.isSuccess){
					var fn = function(){
						search();
					};
					$.messager.alert('提示','保存成功','info',fn);
					$(editDialog).dialog('close');
				}else{
					$.messager.alert('提示',result.message,"error");
				}
				
			}
		});
	};
	//查询
	var search = function(){
		var userName = $('#userNameSearch',queryContent).val();
		var userCode = $('#userCodeSearch',queryContent).val();
		var content = {userCode:userCode,userName:userName};
		$(viewList).datagrid({
			queryParams:content
		});
	};
	//查询
	$('#search',$this).click(function(){
		search();
	});
	//--------------用户角色---------------
	var userRoleList = $('#userRoleList_'+id,$this);
	var userRoleDialog = $('#userRoleDialog_'+id,$this);
	var oldIdArray = new Array();
	var idArray = new Array();
	var deleteIdList = new Array();
	var addIdList = new Array();
	//编辑框
	$(userRoleDialog).dialog({  
	    title: '编辑用户角色信息',  
	    width:400,
	    height:500,
	    closed: true,  
	    cache: false,  
	    modal: true,
	    closable:false,
	    toolbar:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				onSaveUserRole();
			}
		},{
			text:'退出',
			iconCls:'icon-cancel',
			handler:function(){
				$(userRoleDialog).dialog('close');
			}
		}]
	});
	//用户角色
	var onUserRole = function(){
		if(selectRow==null){
			$.messager.alert("提示","请选择数据行","warning");
			return;
		}
		 //加载列表
		$(userRoleList).datagrid({
			url:'system/queryRoleUserRole.do?user.userId='+
				selectRow.userId+'&role.roleType=0',
			fit:true,
			method:"POST",
			nowrap:true,
			striped: true,
			collapsible:true,
			rownumbers:true,
			columns:[[
			    {field:'ck',checkbox:true},
				{field:'roleCode',title:'角色编号',width:100,align:"center"},
			    {field:'roleName',title:'角色名',width:100,align:"center"}
			]],
			onLoadSuccess:function(data){
				var rows = $(userRoleList).datagrid('getRows');
				oldIdArray = new Array();
				for(var i=0;i<rows.length;i++){
					if(rows[i].checked){
						oldIdArray.push(rows[i].roleId);
						$(userRoleList).datagrid('selectRow',i);
					}
				}
			}
		  });
		$(userRoleDialog).dialog('open');
	};
	//保存用户角色前验证
	var setValueUserRole = function(){
		deleteIdList = new Array();
		addIdList = new Array();
		idArray = new Array();
		var rows = $(userRoleList).datagrid('getSelections');
		for(var i =0;i<rows.length;i++){
			idArray.push(rows[i].roleId);
		}
		
		for (var i =0;i<oldIdArray.length;i++) {
			var oldId = oldIdArray[i];
			var isDel = true;
			for (var j =0;j<idArray.length;j++) {
				var id = idArray[j];
				if(oldId==id){
					isDel =false;
					break;
				}
			}
			if(isDel){
				deleteIdList.push(oldId);
			}
		}
		for (var i =0;i<idArray.length;i++) {
			var id = idArray[i];
			var isAdd = true;
			for (var j =0;j<oldIdArray.length;j++) {
				var oldId = idArray[j];
				if(oldId==id){
					isAdd =false;
					break;
				}
			}
			if(isAdd){
				addIdList.push(id);
			}
		}
		if(addIdList.length==0&&deleteIdList.length==0){
			$.messager.alert('提示','角色没有修改');
			return false;
		}
		return true;
	};
	//保存
	var onSaveUserRole = function(){
		if(setValueUserRole()){
			var userId = selectRow.userId;
			var content = {'user.userId':userId,ids: idArray.join(CSIT.join),oldIds:oldIdArray.join(CSIT.join)};
			var url = "system/updateRoleUserRole.do";
			$.post(url,content,
				function(result){
					if(result.isSuccess){
						oldIdArray = idArray;
						$.messager.alert('提示','保存成功');
					}else{
						$.messager.alert('提示',result.message,"warning");
					}
			},'json');
		}
	};
	
	//----------------------用户权限---------------------
	var userRightDialog = $('#userRightDialog_'+id,$this);
	var userRightTree = $('#userRightTree_'+id,$this);
	//编辑框
	$(userRightDialog).dialog({  
	    title: '用户权限',  
	    width:400,
	    height:500,
	    closed: true,  
	    cache: false,  
	    modal: true,
	    closable:false,
	    toolbar:[{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$(userRightDialog).dialog('close');
			}
		}]
	});
	//用户权限
	var onUserRight = function(){
		if(selectRow==null){
			$.messager.alert("提示","请选择数据行","warning");
			return;
		}
		$(userRightTree).tree({
			url: 'system/queryRootRightUser.do?userId='+selectRow.userId,
			onBeforeExpand:function(node,param){
				$(userRightTree).tree('options').url = 'system/getChildrenUrlRightUser.do?userId='+selectRow.userId+'&rightId='+node.id;  
	        }
		});
		$(userRightDialog).dialog('open');
	};
	//重置密码
	var onMulResetPwd = function(){
		var rows = $(viewList).datagrid('getChecked');
		if(rows.length==0){
			 $.messager.alert('提示',"请勾选要重置密码的纪录","warming");
			 return;	
		}
		$.messager.confirm("提示！","确定将所选记录密码重置为123456?",function(t){ 
			if(!t) return;
			var idArray = new Array();
			for(var i=0;i<rows.length;i++){
				idArray.push(rows[i].userId);
			}
			var ids = idArray.join(CSIT.join);
			var url = "system/mulResetPwdUser.do";
			var content = {ids:ids};
			$.post(url,content,
				function(result){
					if(result.isSuccess){
						$.messager.alert('提示','重置密码成功',"info");
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