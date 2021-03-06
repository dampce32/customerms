// 创建一个闭包  
(function($) {  
  // 插件的定义  
  $.fn.rolesInit = function() {
	  var $this = $(this);
	  var id = $(this).attr('id');
	  var rightId = $(this).attr('rightId');
	  var selectRow = null;
	
	  var editDialog = $('#editDialog_'+id,$this);
	  var editForm = $('#editForm_'+id,editDialog);
	  var viewList =  $('#viewList_'+id,$this);
	  var queryContent = $('#queryContent_'+id,$this);
	  
	  //加载列表
	  $(viewList).datagrid({
		  	url:"system/queryRole.do",
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
		          {field:'status',title:'状态',width:50,sortable:true,align:"center",
						formatter: function(value,row,index){
							if (value==0){
								return '<img src="style/v1/icons/warn.png"/>';
							} else if (value==1){
								return '<img src="style/v1/icons/info.png"/>';
							}
					 }},
				{field:'roleCode',title:'角色编号',width:100,align:"center"},
				{field:'roleName',title:'角色名称',width:100,align:"center"}
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
	  //状态
	  $('#state_'+id).menu({  
		    onClick:function(item){
		  		onMulUpdateStatus(item.name);
		    }
		    	
	  });
	//角色权限
	  $('#roleRight_'+id,$this).click(function(){
		  onRoleRight();
	  });
	  //上移
	  $('#moveUp_'+id,$this).click(function(){
		  onMove(-1);
	  });
	  //下移
	  $('#moveDown_'+id,$this).click(function(){
		  onMove(1);
	  });
	//编辑框
	$(editDialog).dialog({  
	    title: '编辑系统角色',  
	    width:400,
	    height:300,
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
		},'-',{
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
	//添加
	var onAdd = function(){
		$('#state',editForm).combobox('setValue',1);
		$(editDialog).dialog('open');
	};
	//保存前的赋值操作
	var setValue = function(){
		var roleCode = $.trim($('#roleCode',editForm).val());
		if(''==roleCode){
			$.messager.alert('提示','请填写角色编号','warning');
			return false;
		}
		var roleName = $.trim($('#roleName',editForm).val());
		if(''==roleName){
			$.messager.alert('提示','请填写角色名','warning');
			return false;
		}
		return true;
	};
	//保存
	var onSave = function(){
		$(editForm).form('submit',{
			url:'system/saveRole.do',
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
			if(selectRow.roleName=='超级管理员'){
				$.messager.alert('警告','超级管理员不能修改','warning');
				return false;
			}
			$(editForm).form('load',selectRow);
			$(editDialog).dialog('open');
		}
	 };
	//查询
	var search = function(){
		var roleCodeSearch = $('#roleCodeSearch',queryContent).val();
		var roleNameSearch = $('#roleNameSearch',queryContent).val();
		var content = {roleName:roleNameSearch,roleCode:roleCodeSearch};
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
				if(rows[i].roleName!="超级管理员"&&rows[i].roleName!="教师"){
					idArray.push(rows[i].roleId);
				}
			}
			var ids = idArray.join(CSIT.join);
			var url = "system/mulDeleteRole.do";
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
		if(status==1){
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
			if(rows[i].roleName!="超级管理员"){
				idArray.push(rows[i].roleId);
			}
		}
		$.messager.confirm("提示","确定要"+msg+"记录?",function(t){ 
			if(t){
				var url = 'system/mulUpdateStatusRole.do';
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
	//-------------------角色权限-----------------------
	var roleRightDialog = $('#roleRightDialog_'+id,$this);
	var roleRightTree = $('#roleRightTree_'+id,roleRightDialog);
	
	var loading = false;
	//编辑框
	$(roleRightDialog).dialog({  
	    title: '角色权限',  
	    width:400,
	    height:500,
	    cache: false, 
	    closed: true,  
	    modal: true,
	    closable:false,
	    toolbar:[{
			text:'退出',
			iconCls:'icon-cancel',
			handler:function(){
				$(roleRightDialog).dialog('close');
			}
		}],
		onOpen:function(){
			$(roleRightTree).tree({
					url: 'system/getRootTreeNodeRoleRight.do?roleId='+selectRow.roleId,
					checkbox:true,
					onBeforeExpand:function(node){
						var rightId = node.id;
						var url = 'system/getChildrenTreeNodeRoleRight.do?roleId='+selectRow.roleId+'&rightId='+rightId;
						$(roleRightTree).tree('options').url = url;
			        },
			        onBeforeLoad:function(node, param){ 
						loading=true; 
					}, 
					onLoadSuccess:function(node, data){ 
					    loading=false; 
					},
					onClick:function(node){ 
						$(roleRightTree).tree('expand',node.target);
					},
		            onCheck:function(node,checked){
		            	if(!loading){
		            		$(roleRightDialog).mask({maskMsg:'正在更新权限'});
		            		var rightId = node.id;
		    				var status = null;
		    				if(checked){
		    					status=1;
		    				}else{
		    					status=0;
		    				}
		    				var url = 'system/updateStatusRoleRight.do?roleId='+selectRow.roleId+'&rightId='+rightId+'&status='+status;
		    				$.post(url,
							  function(result){
		            			if(!result.isSuccess){
									$.messager.alert('提示',result.message,"error");
								}
		            			$(roleRightDialog).mask('hide');
		            		},'json');
		            	}
		            }
		  	});
		}
	}); 
	//角色权限
	var onRoleRight = function(){
		if(selectRow==null){
			$.messager.alert("警告","请选择数据行","warning");
			return;
		}
		$(roleRightDialog).dialog('open');
	};
	//移动
	var onMove = function(direction){
		if(selectRow == null){
			$.messager.alert('提示',"请选中一条记录","warning");
			return;
		}
		var rows  = $(viewList).datagrid('getRows');
		if(direction==-1){
			if(selectIndex==0){
				$.messager.alert('提示',"已经是第一条记录了","warning");
				return;
			}
		}else if(direction==1){//下移
			if(selectIndex==rows.length-1){
				$.messager.alert('提示',"已经是最后一条记录了","warning");
				return;
			}
		}
		var updateRow = rows[selectIndex+direction];
		
		var roleId = selectRow.roleId;
		var roleCode = selectRow.roleCode;
		var roleName = selectRow.roleName;
		var status = selectRow.status;
		var array = updateRow.array;
		updateRow.array = selectRow.array;
		//后台更新排序
		var url = "system/updateArrayRole.do";
		var content = {roleId:roleId,updateRoleId:updateRow.roleId};
		var result = syncCallService(url,content);
		if(result.isSuccess){
			$(viewList).datagrid('updateRow',{
				index: selectIndex,
				row: {
					roleId:updateRow.roleId,
					roleCode:updateRow.roleCode,
					roleName:updateRow.roleName,
					status:updateRow.status,
					array:updateRow.array
				}
			});
			$(viewList).datagrid('updateRow',{
				index: selectIndex+direction,
				row: {
					roleId:roleId,
					roleCode:roleCode,
					roleName:roleName,
					status:status,
					array:array
				}
			});
			$(viewList).datagrid('unselectAll');
			$(viewList).datagrid('selectRow',selectIndex+direction);
			selectIndex = selectIndex+direction;
			selectRow = $(viewList).datagrid('getSelected');
		}else{
			$.messager.alert('提示',result.message,"error");
		}
	};
	//----------检查权限--------------
	var rights = null;
	var checkBtnRight = function(){
	};
	checkBtnRight();
  };
})(jQuery);   