// 创建一个闭包  
(function($) {  
  // 插件的定义  
  $.fn.rightsInit = function() {
	  var $this = $(this);
	  var id = $(this).attr('id');
	  var rightId = $(this).attr('rightId');
	  
	  var selectRow = null;
	  var selectIndex = null;
	  
	  var editDialog = $('#editDialog_'+id,$this);
	  var editForm = $('#editForm_'+id,editDialog);
	  var rightTree =  $('#rightTree_'+id,$this);
	  var rightList =  $('#rightList_'+id,$this);
	  var queryContent = $('#queryContent_'+id,$this);
	  //权限树  
	  $(rightTree).tree({
		  animate:true,
		  dnd:true,
			url: 'system/getRootTreeNodeRight.do',
			onBeforeExpand:function(node,param){
				$(rightTree).tree('options').url = 'system/getChildrenTreeNodeRight.do?rightId='+node.id;  
	        },
			onClick:function(node){ 
				$(rightTree).tree('expand',node.target);
				var rightName = $('#rightNameSearch').val();
				var content = {rightId:node.id,rightName:rightName};
				$(rightList).datagrid({
					url:'system/queryChildrenRight.do',
					queryParams:content
				});
			},
			onDrop: function(targetNode, source, point){  
				var targetId = $(rightTree).tree('getNode', targetNode).id;  
				var url = "system/dropUpdateArrayRight.do";
				var content = {sourceId:source.id,targetId:targetId,point:point};
				var result = syncCallService(url,content);
				if(!result.isSuccess){
					$.messager.alert('提示',result.message,"error");
				}
			},
			onBeforeDrop:function(target,source,point){
				var root = $(rightTree).tree('getRoot');
				var targetId = $(rightTree).tree('getNode', target).id;  
				if(root.id==targetId){
					$.messager.alert('提示','不能与根节点同级','warning');
					return false;
				}
				return true;
			}
	  });
	  //列表
	  $(rightList).datagrid({
		  	url:'system/queryChildrenRight.do',
			fit:true,
			idField:'rightId',
			rownumbers:true,
			pagination:true,
			singleSelect:true,
			selectOnCheck:false,
			checkOnSelect:false,
			toolbar:[	
						{id:'add_'+id,text:'添加',iconCls:'icon-add',handler:function(){onAdd();}},
						{id:'update_'+id,text:'修改',iconCls:'icon-edit',handler:function(){onUpdate();}},
						{id:'delete_'+id,text:'删除',iconCls:'icon-remove',handler:function(){onMulDelete();}},
						{id:'open_'+id,text:'启用',iconCls:'icon-info',handler:function(){onMulUpdateStatus(1);}},
						{id:'close_'+id,text:'禁用',iconCls:'icon-warn',handler:function(){onMulUpdateStatus(0);}}
					],
			columns:[[{field:'ck',checkbox:true},
			    {field:'status',title:'状态',width:50,sortable:true,align:"center",
					formatter: function(value,row,index){
						if (value==0){
							return '<img src="js/easyui/icons/warn.png"/>';
						} else if (value==1){
							return '<img src="js/easyui/icons/info.png"/>';
						}
				 }},
			    {field:'rightCode',title:'权限编号',width:200,sortable:true,align:"center"},
			    {field:'rightName',title:'权限名称',width:100,sortable:true,align:"center"},
			    {field:'iconCls',title:'图标',width:100,sortable:true,align:"center"},
				{field:'rightUrl',title:'权限Url',width:200,sortable:true,align:"center"},
				{field:'rightKind',title:'类型',width:100,sortable:true,align:"center",formatter: function(value,row,index){
					if (value==1){
						return 'Url权限';
					} else if (value==2){
						return '界面按钮权限';
					}else if (value==3){
						return '数据显示权限';
					}
			  }}
			]],
			onDblClickRow:function(rowIndex, rowData){
				onUpdate();
			},
			onClickRow:function(rowIndex, rowData){
				selectRow = rowData;
				selectIndex = rowIndex;
			},
			onLoadSuccess:function(){
				selectRow = null;
		 		selectIndex = null;
		 		$(rightList).datagrid('uncheckAll');
			}
	 });
	//编辑框
	$(editDialog).dialog({  
	    title: '编辑系统权限',  
	    width:380,
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
			iconCls:'icon-cancel',
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
		var selectedNote = $(rightTree).tree('getSelected');
		if(selectedNote==null){
			$.messager.alert('提示','请选择父节点','warning');
			return false;
		}
		$('#rightKind',editForm).combobox('setValue',1);
		$('#status',editForm).combobox('setValue','1');
		$(editDialog).dialog('open');
	};
	//保存前的赋值操作
	var setValue = function(){
		var rightCode = $.trim($('#rightCode',editForm).val());
		if(rightCode==''){
			$.messager.alert('提示','请填写权限编号','warning');
			return false;
		}
		var rightName = $.trim($('#rightName',editForm).val());
		if(rightName==''){
			$.messager.alert('提示','请填写权限名称','warning');
			return false;
		}
		var rightKind = $.trim($('#rightKind',editForm).combobox('getValue'));
		if(rightKind==''){
			$.messager.alert('提示','请选择权限类型','warning');
			return false;
		}
		var selectedNote = $(rightTree).tree('getSelected');
		if(selectedNote==null){
			var root = $(rightTree).tree('getRoot');
			if(root==null){
				$('#parentId',editForm).val(null);
			}else{
				$('#parentId',editForm).val(root.id);
			}
		}else{
			$('#parentId',editForm).val(selectedNote.id);
		}
		return true;
	};
	//保存
	var onSave = function(){
		 $(editForm).form('submit',{
			url: 'system/saveRight.do',
			onSubmit: function(){
				return setValue();
			},
			success: function(data){
				var result = eval('('+data+')');
				if(result.isSuccess){
					var fn = function(){
						var rightId = $('#rightId',editForm).val();
						//新增
						if(rightId==''){
							var node = $(rightTree).tree('getSelected');
							var rightName = $('#rightName',editForm).val();
							$(rightTree).tree('append',{
								parent: (node?node.target:null),
								data:[{
									id:result.data.rightId,
									text:rightName
								}]
							});
							search();
						}else{
							var row = $(editForm).serializeObject();
							$(rightList).datagrid('updateRow',{index:selectIndex,row:row});
							var rightId=$("#rightId",editForm).val();
							var rightName = $('#rightName',editForm).val();
							var updateNote=$(rightTree).tree('find',rightId);
							updateNote.text=rightName;
							$(rightTree).tree('update', updateNote);
						}
						$(editDialog).dialog('close');
					};
					$.messager.alert('提示','保存成功','info',fn);
				}else{
					$.messager.alert('提示',result.message,'error');
				}
				
			}
		 });
	};
	//修改
	var onUpdate = function(){
		var options = $('#update_'+id,$this).linkbutton('options');
		if(!options.disabled){
			if(selectRow==null){
				$.messager.alert("提示","请选择数据行","warning");
				return;
			}
			$(editForm).form('clear');
			$(editForm).form('load',selectRow);
			$(editDialog).dialog('open');
		}
	 };
	//删除
	var onMulDelete = function(){
		var rows = $(rightList).datagrid('getChecked');
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
				idArray.push(rows[i].rightId);
			}
			var ids = idArray.join(CSIT.join);
			var url = "system/mulDeleteRight.do";
			var content = {ids:ids};
			$.post(url,content,
				function(result){
					if(result.isSuccess){
						var rows = $(rightList).datagrid('getChecked');
						for(var i=0;i<rows.length>0;i++){
							$(rightTree).tree('remove',$(rightTree).tree('find',rows[i].rightId).target);
						}
						search();
						$(rightList).datagrid('uncheckAll');
					}else{
						$.messager.alert('提示',result.message,"error");
					}
				}, "json");
		});
	};
	//查询
	$('#search',$this).click(function(){
		search();
	});
	//分页操作
	var search = function(){
		var rightCodeSearch = $('#rightCodeSearch',queryContent).val();
		var rightNameSearch = $('#rightNameSearch',queryContent).val();
		var rightId = '';
		var selectedNote = $(rightTree).tree('getSelected');
		if(selectedNote==null){
			var root = $(rightTree).tree('getRoot');
			if(root!=null){
				rightId = root.id;
			}
		}else{
			rightId = selectedNote.id;
		}
		var content = {rightId:rightId,rightCode:rightCodeSearch,rightName:rightNameSearch};
		
		$(rightList).datagrid({
			queryParams:content
		});
	};
	
	//修改多个审核状态
	var onMulUpdateStatus = function(status){
		var rows =  $(rightList).datagrid('getChecked');
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
			idArray.push(rows[i].rightId);
		}
		$.messager.confirm("提示","确定要"+msg+"记录?",function(t){ 
			if(t){
				var url = 'system/mulUpdateStatusRight.do';
				var content ={ids:idArray.join(CSIT.join),status:status};
				asyncCallService(url,content,function(result){
					if(result.isSuccess){
						var fn = function(){
							search(true);
							$(rightList).datagrid('uncheckAll');
						};
						$.messager.alert('提示',msg+'成功','info',fn);
					}else{
						$.messager.alert('提示',result.message,'error');
					}
				});
			}
		});
	};
  };
})(jQuery);   