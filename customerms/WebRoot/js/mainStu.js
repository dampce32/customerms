var base = null;
$(function(){
	//当前选中的节点
    var currentNode = null;
	tabCloseEven();
	base = $('#baseMain').val();
 	$('#navi a').click(function(){
		var href =$(this).attr('name');
		var title = $(this).text();
		if(href==null){
			return false;
		}
		
		addTab(title,href);
		return false;
	});

 	$('#stuRightTree').tree({
		onBeforeExpand:function(node,param){
			var stuRoleId = $('#currStuRolesMain').combobox('getValue');
			$('#stuRightTree').tree('options').url = 'stu/getCurrStuRoleChildrenStuRoleRight.do?stuRightId='+node.id+"&stuRoleId="+stuRoleId;  
	    },
		onSelect:function (node) {
			$(this).tree('toggle',node.target);
            if (currentNode != null && node.id == currentNode.id) return;
            if (node.attributes == null) return;
            var url = node.attributes.stuRightUrl;
            var title = node.text;
            CSIT.currTabRightId = node.id;
            addTab(title, url);
        }
	});

 	asyncCallService('stu/getCurrStuRolesStudent.do',function(result){
 		if(result.isSuccess){
 			var data = result.data;
 			var currStuRoles = eval("("+data.currStuRoles+")");
 			$('#currStuRolesMain').combobox({   
   		    	valueField:'stuRoleId',   
       		    textField:'stuRoleName',
       		    data:currStuRoles,
       		    onChange: function(newValue, oldValue){ 
   		    		//改变左边的树节点
       		    	var content = {stuRoleId:newValue};
   	    		    asyncCallService('stu/getCurrStuRoleRootStuRoleRight.do',content,function(result){
   	    		    	if(result.isSuccess){
   	    		    		var data = result.data;
   	    		    		var tree = eval("("+data.tree+")");
   	    		    		$('#stuRightTree').tree('loadData',eval(tree));
   	    		    		$('#mm-tabcloseall').click();
	 	   	    		}else{
	 	   	     			$.messager.alert('提示',result.message,'warning');
	 	   	     		}
   	    		    });
   		    	}
       		}); 
 			var haveRole = false;
 			for ( var currStuRole in currStuRoles) {
 				haveRole = true;
 	   		}
 			if(haveRole){
 				$('#currStuRolesMain').combobox('setValue',currStuRoles[0].stuRoleId);
 			}
 			$('#loginNameMain').html(data.loginName);
 		}else{
 			$.messager.alert('提示',result.message,'warning');
 		}
 	});

 	//退出系统
 	 $('#exitSystem').click(function(){
     	$.messager.confirm('提示','确定要退出系统吗?',function(r){
     		if(r){
     			asyncCallService('stu/logoutStudent.do',function(result){
     				if(result.isSuccess){
     					window.location.href='login.html';
     				}
     				else{
     		 			$.messager.alert('错误',result.message,'error');
     		 		}
     			});
     		}
     	});
     });
   //---修改密码---
     $('#modifyPwdDialog').dialog({
     	title: '修改密码',  
 	    width:500,
 	    height:300,
 	    closed: true,  
 	    cache: false,  
 	    modal: true,
 	    closable:false,
 	    toolbar:[{
 			text:'保存',
 			iconCls:'icon-save',
 			handler:function(){
 				onSaveModifyPwd();
 			}
 		},'-',{
 			text:'退出',
 			iconCls:'icon-cancel',
 			handler:function(){
 				$('#modifyPwdDialog').dialog('close');
 			}
 		}]
     });
     $('#modifyPwdBtn').click(function(){
     	$('#modifyPwdForm').form('clear');
      	$('#modifyPwdDialog').dialog('open');
     });
     $('#cmsWebBtn').click(function(){
    	 window.open(basePath);
     });
     //保存前检验表单值
     var setValue = function(){
    	 var passwords = $('#passwords').val();
    	 if('' == passwords){
    		 $.messager.alert('提示','请输入原密码','warning');
    		 return false;
    	 }
    	 var newTeacherPwd = $('#newTeacherPwd').val();
    	 if('' == newTeacherPwd){
    		 $.messager.alert('提示','请输入新密码','warning');
    		 return false;
    	 }
    	 var newTeacherPwd2 = $('#newTeacherPwd2').val();
    	 if(newTeacherPwd!=newTeacherPwd2){
    		 $.messager.alert('提示','两次输入的新密码不一样','warning');
    		 return false;
    	 }
    	 return true;
     };
 	  
     var onSaveModifyPwd = function(){
    	 $('#modifyPwdForm').form('submit',{
    		 url: 'stu/modifyPwdStudent.do',
	 		 onSubmit: function(){
	 			 return setValue();
	 		 },
	 		 success: function(data){
	 			 var result = eval('('+data+')');
	 			 if(result.isSuccess){
	 				 $('#modifyPwdDialog').dialog('close');
	 				 $.messager.alert('提示','密码修改成功','info');
	 			 }else{
	 				 $.messager.alert( '提示',result.message,"warning");
	 			 }
	 		 }
    	 });
     };
// 	//上传前的赋值操作
//	var setUploadValue = function(){
//		var file = $('#fileMain').val();
//		if(file == ''){
//			$.messager.alert('提示','文件不能为空!','warning');
//			return false;
//		}
//		
//		$('#uploadDialogMain').mask({maskMsg:'正在上传'});
//		return true;
//	};
//	//保存
//	var onUploadSave = function(){
//		var urlUploadMain =$('#urlUploadMain').val();
//		var searchBtnIdMain = $('#searchBtnIdMain').val();
//		$('#uploadFormMain').form('submit',{
//			url:urlUploadMain,
//			onSubmit: function(){
//				return setUploadValue();
//			},
//			success: function(data){
//				var result = eval('('+data+')');
//				if(result.isSuccess){
//					var fn = function(){
//						$('#searchBtn_'+searchBtnIdMain).click();
//					};
//					$.messager.alert('提示','上传成功','info',fn);
//					$('#uploadDialogMain').dialog('close');
//					$('#uploadDialogMain').mask('hide');
//				}else{
//					$.messager.alert('提示',result.message,"error");
//					$('#uploadDialogMain').mask('hide');
//				}
//			}
//		});
//	};
// 	//上传文件框
//	$('#uploadDialogMain').dialog({  
//	    title: '上传学科大类',  
//	    width:400,
//	    height:200,
//	    closed: true,  
//	    cache: false,  
//	    modal: true,
//	    closable:false,
//	    toolbar:[{
//			text:'上传',
//			iconCls:'icon-upload',
//			handler:function(){
//		 		onUploadSave();
//			}
//		},'-',{
//			text:'下载模板',
//			iconCls:'icon-excel',
//			handler:function(){
//				var url = $('#urlTemplateMain').val();
//				window.open(url);
//			}
//		},'-',{
//			text:'退出',
//			iconCls:'icon-cancel',
//			handler:function(){
//				$('#uploadDialogMain').dialog('close');
//			}
//		}],
//		onClose:function(){
//			$('#uploadDialogMain').form('clear');
//		}
//	});
//	//导出文件框
//	$('#exportDialogMain').dialog({  
//	    title: '导出Excel',  
//	    width:600,
//	    height:300,
//	    closed: true,  
//	    cache: false,  
//	    modal: true,
//	    closable:false,
//	    toolbar:[{
//			text:'导出',
//			iconCls:'icon-upload',
//			handler:function(){
//		 		onExportOK();
//			}
//		},'-',{
//			text:'退出',
//			iconCls:'icon-cancel',
//			handler:function(){
//				$('#exportDialogMain').dialog('close');
//			}
//		}],
//		onClose:function(){
//			$('#exportDialogMain').form('clear');
//		}
//	});
//	//导出
//	var onExportOK = function(){
//		var fieldArray = new Array();
//		var fieldNameArray = new Array();
//		$('#exportColumnsMain :checkbox:checked').each(function(){
//			var field = $(this).attr('field');
//			var fieldName = $(this).attr('fieldName');
//			fieldArray.push(field);
//			fieldNameArray.push(fieldName);
//		});
//		if(fieldArray.length==0){
//			$.messager.alert('提示','请至少选择一列导出','warning');
//			return;
//		}else{
//			$('#fieldsMain').val(fieldArray.join(CSIT.join));
//			$('#fieldNamesMain').val(fieldNameArray.join(CSIT.join));
//			$('#submitBtn').click();
//		}
//	};
//	$("#reverseChooseMain").change( function() {
//		$('#exportColumnsMain li :checkbox').each(function(){
//			$(this).prop('checked',!this.checked);
//		});
//	});
});
////打开主界面导出Excel对话框
//var showExportDialogMain = function(action,viewList){
//	$('#exportFormMain').attr('action',action);
//	//导出字段
//	var exportColumnsHtml =''; 
//	var columns = $(viewList).datagrid('getColumnFields');
//	for ( var column in columns) {
//		var columnOption =  $(viewList).datagrid('getColumnOption',columns[column]);
//		if(columnOption.title!=undefined){
//			exportColumnsHtml+= '<li style="width:155px;"> <input type="checkbox" field="'+columnOption.field+'" id="'+columnOption.field+'Main" checked="checked" fieldName="'+columnOption.title+'"><label for="'+columnOption.field+'Main">&nbsp;&nbsp;'+columnOption.title+"</label></li>";
//		}
//	}
//	$('#exportColumnsMain').html(exportColumnsHtml);
//	//导出参数
//	var exportParams = $(viewList).datagrid('options').queryParams;
//	var exportParamsDivHtml = '';
//	for ( var exportParam in exportParams) {
//		exportParamsDivHtml+='<input type="hidden" name="'+exportParam+'" value="'+exportParams[exportParam]+'">';
//	}
//	$('#exportParamsDiv').html(exportParamsDivHtml);
//	$('#exportDialogMain').dialog('open');
//};

function addTab(title,href){
	if($('#tabs').tabs('exists',title)){//选择并更新tab
		$('#tabs').tabs('select',title);
	}else{
		if(href.indexOf('.html')!=-1){
			href ='stu/getStuTabPanel.do?html='+href+'&stuRightId='+CSIT.currTabRightId;
			var panelInfo = {
	                title:title,
	                closable:true,
	                href:href, border:false, plain:true,
	                extractor:function (d) {
	                    if (!d) {
	                        return d;
	                    }
	                    var currTabRightId = CSIT.currTabRightId;
	                    d = d.replace(/\$\{rightId\}/g, currTabRightId);
	                    if (window['CSIT']) {
	                        var id = CSIT.genId();
	                        return d.replace(/\$\{id\}/g, id);
	                    }
	                    return d;
	                },
	                onLoad:function (panel) {
	                    var tab = $('.plugins', this);
	                    if ($(tab).size() == 0) {
	                        return;
	                    }
	                   (window['CSIT'] && CSIT.initContent && CSIT.initContent(this));
	                }
	            };
			 $('#tabs').tabs('add', panelInfo);
		}else{
        	var content = createFrame(href);
    		$('#tabs').tabs('add',{
    			title:title,
    			content:content,
    			closable:true
    		});
        }
			
           
	}
	tabClose();
}

function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	});
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		return false;
	});
}	
//绑定右键菜单事件
function tabCloseEven() {
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != '首页') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			});
		}
	});
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	});
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != '首页') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != '首页') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != '首页') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			if(t != '首页') {
				$('#tabs').tabs('close',t);
			}
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==1){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			if(t != '首页') {
				$('#tabs').tabs('close',t);
			}
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	});
}