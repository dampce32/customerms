var lastIndex=-1;
var url;
$(function(){
	 $('#addOperatorDialog').dialog({
		 	modal:true,
		 	closed:true,
			toolbar:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
	                $('#addOperatorForm').form('submit',{
	    				url: url,
	    				onSubmit: function(){
	    					return $(this).form('validate');
	    				},
	    				success: function(result){
	    					var result = eval('('+result+')');
	    					if (result.success){
	    						$('#addOperatorDialog').dialog('close');
	    						$('#operatorTable').datagrid('reload');	
	    					} else {
	    						$.messager.show({
	    							title: 'Error',
	    							msg: result.msg
	    						});
	    					}
	    				}
	    			});
				}
			 },'-',{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$('#addOperatorDialog').dialog('close');
				}
			}]
		});
	
	$("#add").click(function(){
		$(".password").show();
		$('#addOperatorDialog').dialog('open').dialog('setTitle','新增用户');
		$('#addOperatorDialog').form('clear');
		url = js+'operator/addOperator.action'
		return false;
	});
	
	
	/*删除用户*/
	$("#detele").click(function(){
		var idsArray = new Array();
		var rows = $('#operatorTable').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert("提示","请选中数据","warning");
			return false;
		}
		$.messager.confirm("提示！","确定要删除选中用户?",function(t){ 
			if(!t) return;
			for(var i=0;i<rows.length;i++){
				idsArray.push(rows[i].ID);
			}	
			var ids= idsArray.join(",");
			$.post(js+"operator/deleteByIDsOperator.action",{ids:ids},function(result){
			     if(result.success){
			     	$('#operatorTable').datagrid('reload');
			      }else{
			        $.messager.alert('提示',"删除不成功","error");	
			      }
			},"json");
			
		});
		
		return false;
	});
	
	
	$('#operatorTable').datagrid({
				fit:true,
				nowrap: false,
				url:js+'operator/viewOperator.action',
				sortName: 'code',
				sortOrder: 'desc',
				idField:'ID',
				fitColumns: true,
				height: 'auto',
				pagination:true,
				columns:[[{field:'ID',checkbox:true},
	                {field:'code',title:'登陆名称',width:80,sortable:true,editor:'text'},
					{field:'name',title:'姓名',width:120,sortable:true,editor:'text'}
				]],
				onBeforeLoad:function(){
						$(this).datagrid('rejectChanges');
				},
				onClickRow:function(rowIndex){
					lastIndex = rowIndex;
				},onDblClickRow:function(rowIndex, rowData){
					    $(".password").hide()
						$('#addOperatorDialog').dialog('open').dialog('setTitle','修改用户');
						$('#addOperatorDialog').form('load',rowData);
						url = js+'operator/updateOperator.action'
				}
	});
	
	
})

