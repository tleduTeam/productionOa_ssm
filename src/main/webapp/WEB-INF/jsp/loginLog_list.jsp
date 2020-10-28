<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<table class="easyui-datagrid" id="loginLogList" title="订单列表" data-options="singleSelect:false,collapsible:true,
	pagination:true,rownumbers:true,url:'loginLog/list',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_loginLog">
    <thead>
        <tr>
			<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',align:'center',width:150">编号</th>
            <th data-options="field:'come_time',align:'center',width:150">登录时间</th>
            <th data-options="field:'ip',align:'center',width:150">IP地址</th>
            <th data-options="field:'username',width:150,align:'center'">登录用户</th>
        </tr>
    </thead>
</table> 

<div  id="toolbar_loginLog" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
	<c:forEach items="${sessionScope.sysPermissionList}" var="per" >
		<c:if test="${per=='loginLog:delete' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="loginLog_delete()">删除</a>  
		    </div>  
		</c:if>
	</c:forEach>
	
	<div class="datagrid-btn-separator"></div>  
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="loginLog_reload()">刷新</a>  
	</div>  
	
    <div id="search_loginLog" style="float: right;">
        <input id="search_text_loginLog" class="easyui-searchbox"  
            data-options="searcher:doSearch_loginLog,prompt:'请输入...',menu:'#menu_loginLog'"  
            style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_loginLog" style="width:120px"> 
			<div data-options="name:'loginLogId'">角色编号</div> 
			<div data-options="name:'loginLogName'">登录用户</div>
		</div>     
    </div>  
</div>  

<div id="loginLogEditWindow" class="easyui-window" title="编辑订单" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'loginLog/edit'" style="width:65%;height:80%;padding:10px;">
</div>
<div id="loginLogAddWindow" class="easyui-window" title="添加订单" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'loginLog/add'" style="width:65%;height:80%;padding:10px;">
</div>

<script>
function doSearch_loginLog(value,name){ //用户输入用户名,点击搜素,触发此函数  
	if(value == null || value == ''){
		
		$("#loginLogList").datagrid({
	        title:'订单列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_loginLog", url:'loginLog/list', method:'get', loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'id', width : 150, align:'center', title : '角色编号'},
				{field : 'come_time', width : 150, align : 'center', title : '登陆时间'},
				{field : 'ip', width : 150, align : 'center', title : 'IP地址'},
				{field : 'username', width : 150, title : '登录用户', align:'center',	}
	        ] ],  
	    });
	}else{
		$("#loginLogList").datagrid({  
	        title:'订单列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_loginLog", url:'loginLog/search_loginLog_by_'+name+'?searchValue='+value,
			loadMsg:'数据加载中......', fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
	     			{field : 'ck', checkbox:true },
	    			{field : 'id', width : 150, align:'center', title : '角色编号'},
	    			{field : 'come_time', width : 150, align : 'center', title : '登陆时间'},
	    			{field : 'ip', width : 150, align : 'center', title : 'IP地址'},
	    			{field : 'username', width : 150, title : '登录用户', align:'center'}
	        ] ],  
	    });
	}
}
	var loginLogNoteEditor ;
	
	var loginLogProductEditor;
	
	var loginLogCustomEditor;
	
	//格式化客户信息
	function formatCustom(value, row, index){ 
		if(value !=null && value != ''){
			var row = onloginLogClickRow(index); 
			return "<a href=javascript:openloginLogCustom("+index+")>"+value.customName+"</a>";
		}else{
			return "无";
		}
	};  
	
	//格式化产品信息
	function  formatProduct(value, row, index){ 
		if(value !=null && value != ''){
			return "<a href=javascript:openloginLogProduct("+index+")>"+value.productName+"</a>";
		}else{
			return "无";
		}
	};
	
	//格式化订单要求
	function formatloginLogNote(value, row, index){ 
		if(value !=null && value != ''){
			return "<a href=javascript:openloginLogNote("+index+")>"+"订单要求"+"</a>";
		}else{
			return "无";
		}
	}

	//根据index拿到该行值
	function onloginLogClickRow(index) {
		var rows = $('#loginLogList').datagrid('getRows');
		return rows[index];
		
	}
	
	//打开客户信息对话框
	function  openloginLogCustom(index){ 
		var row = onloginLogClickRow(index);
		$("#loginLogCustomInfo").dialog({
    		onOpen :function(){
    			$.get("custom/get/"+row.custom.customId,'',function(data){
    				loginLogCustomEditor = TAOTAO.createEditor("#loginLogCustomEditForm [name=note]");	
		    		//回显数据
		    		$("#loginLogCustomEditForm").form("load", data);
		    		loginLogCustomEditor.html(data.note);
		    		
		    		TAOTAO.init({
        				"pics" : data.image,
        			});
    	    	});
    		},
			onBeforeClose: function (event, ui) {
				// 关闭Dialog前移除编辑器
			   	KindEditor.remove("#loginLogCustomEditForm [name=note]");
			}
    	}).dialog("open");
	};
	
	function submitloginLogCustomEditForm(){
		$.get("custom/edit_judge",'',function(data){
    		if(data.msg != null){
    			$.messager.alert('提示', data.msg);
    		}else{
    			if(!$('#loginLogCustomEditForm').form('validate')){
    				$.messager.alert('提示','表单还未填写完成!');
    				return ;
    			}
    			//同步文本框中的备注
    			loginLogCustomEditor.sync();
    			$.post("custom/update_all",$("#loginLogCustomEditForm").serialize(), function(data){
    				if(data.status == 200){
    					$.messager.alert('提示','修改客户成功!','info',function(){
    						$("#loginLogCustomInfo").dialog("close");
    						$("#loginLogList").datagrid("reload");
    					});
    				}else{
    					$.messager.alert('提示',data.msg);
    				}
    			});
    		}
    	});
	}
	
	//打开产品信息对话框
	function  openloginLogProduct(index){ 
		var row = onloginLogClickRow(index);
		$("#loginLogProductInfo").dialog({
    		onOpen :function(){
    			$.get("product/get/"+row.product.productId,'',function(data){
    				
    				loginLogProductEditor = TAOTAO.createEditor("#loginLogProductEditForm [name=note]");	
		    		//回显数据
		    		$("#loginLogProductEditForm").form("load", data);
		    		loginLogProductEditor.html(data.note);
		    		
		    		//加载图片
 	        		initloginLogProductPic({
           				"pics" : data.image,
           			});
    	    	});
    		},
			onBeforeClose: function (event, ui) {
				// 关闭Dialog前移除编辑器
			   	KindEditor.remove("#loginLogProductEditForm [name=note]");
			   	clearManuSpan();
			}
    	}).dialog("open");
	};
	
	// 加载图片
    function initloginLogProductPic(data){
    	$(".loginLogProductPic").each(function(i,e){
    		var _ele = $(e);
    		_ele.siblings("div.pics").remove();
    		_ele.after('\
    			<div class="pics">\
        			<ul></ul>\
        		</div>');
    		// 回显图片
    		var j = false;
        	if(data && data.pics){
        		var imgs = data.pics.split(",");
        		for(var i in imgs){
        			if($.trim(imgs[i]).length > 0){
        				_ele.siblings(".pics").find("ul").append("<li><a id='img"+i+"' href='"+imgs[i]+"' target='_blank'>" +
        						"<img src='"+imgs[i]+"' width='80' height='50' /></a> ");
        				j = true;
        			}
        		}
        	}
        	if(!j){
    			$("#loginLogProductPic").html("<span style='font-size: 12px;font-family: Microsoft YaHei;'>无</span>");
    		}
    	});
    }
	
    function clearManuSpan(){
		$("#loginLogProductPic").html('');
	}
    
	function submitloginLogProductEditForm(){
		$.get("product/edit_judge",'',function(data){
    		if(data.msg != null){
    			$.messager.alert('提示', data.msg);
    		}else{
    			if(!$('#loginLogProductEditForm').form('validate')){
    				$.messager.alert('提示','表单还未填写完成!');
    				return ;
    			}
    			loginLogProductEditor.sync();
    			
    			$.post("product/update_all",$("#loginLogProductEditForm").serialize(), function(data){
    				if(data.status == 200){
    					$.messager.alert('提示','修改产品成功!','info',function(){
    						$("#loginLogProductInfo").dialog("close");
    						$("#loginLogList").datagrid("reload");
    					});
    				}else{
    					$.messager.alert('提示',data.msg);
    				}
    			});
    		}
    	});
	}
	
	//打开订单要求富文本编辑器对话框
	function  openloginLogNote(index){ 
		var row = onloginLogClickRow(index);
		$("#loginLogNoteDialog").dialog({
    		onOpen :function(){
    			$("#loginLogNoteForm [name=loginLogId]").val(row.loginLogId);
    			loginLogNoteEditor = TAOTAO.createEditor("#loginLogNoteForm [name=note]");
    			loginLogNoteEditor.html(row.note);
    		},
		
			onBeforeClose: function (event, ui) {
				// 关闭Dialog前移除编辑器
			   	KindEditor.remove("#loginLogNoteForm [name=note]");
			}
    	}).dialog("open");
		
	};
	
	//更新订单要求
	function updateloginLogNote(){
		$.get("loginLog/edit_judge",'',function(data){
    		if(data.msg != null){
    			$.messager.alert('提示', data.msg);
    		}else{
    			loginLogNoteEditor.sync();
    			$.post("loginLog/update_note",$("#loginLogNoteForm").serialize(), function(data){
    				if(data.status == 200){
    					$("#loginLogNoteDialog").dialog("close");
    					$("#loginLogList").datagrid("reload");
    					$.messager.alert("操作提示", "更新订单要求成功！");
    				}else{
    					$.messager.alert("操作提示", "更新订单要求失败！");
    				}
    			});
    		}
    	});
	}
	
    function getloginLogSelectionsIds(){
    	var loginLogList = $("#loginLogList");
    	var sels = loginLogList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(","); 
    	
    	return ids;
    }
    
    function loginLog_add(){
    	$.get("loginLog/add_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			$("#loginLogAddWindow").window("open");
       		}
       	});
    }
    
    function loginLog_edit(){
    	$.get("loginLog/edit_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			var ids = getloginLogSelectionsIds();
               	
               	if(ids.length == 0){
               		$.messager.alert('提示','必须选择一个订单才能编辑!');
               		return ;
               	}
               	if(ids.indexOf(',') > 0){
               		$.messager.alert('提示','只能选择一个订单!');
               		return ;
               	}
               	
               	$("#loginLogEditWindow").window({
               		onLoad :function(){
               			//回显数据
               			var data = $("#loginLogList").datagrid("getSelections")[0];
               			data.customId = data.custom.customId; 
               			data.productId = data.product.productId; 
               			data.loginLogDate = TAOTAO.formatDateTime(data.loginLogDate);
               			data.requestDate = TAOTAO.formatDateTime(data.requestDate);
               			$("#loginLogEditForm").form("load", data);
               			loginLogEditEditor.html(data.note);
               			
               			TAOTAO.init({
               				"pics" : data.image,
               			});
               			
               			//加载文件上传插件
               			initloginLogEditFileUpload();
               			//加载上传过的文件
               			initUploadedFile();
               		}
               	}).window("open");
       		}
       	});
    }
    
    function loginLog_delete(){
    	$.get("loginLog/delete_judge",'',function(data){
      		if(data.msg != null){
      			$.messager.alert('提示', data.msg);
      		}else{
      			var ids = getloginLogSelectionsIds();
              	if(ids.length == 0){
              		$.messager.alert('提示','未选中订单!');
              		return ;
              	}
              	$.messager.confirm('确认','确定删除ID为 '+ids+' 的日志吗？',function(r){
              	    if (r){
              	    	var params = {"ids":ids};
                      	$.post("loginLog/delete_batch",params, function(data){
                  			if(data.status == 200){
                  				$.messager.alert('提示','删除日志成功!',undefined,function(){
                  					$("#loginLogList").datagrid("reload");
                  				});
                  			}
                  		});
              	    }
              	});
      		}
      	});
    }
    
    function loginLog_reload(){
    	$("#loginLogList").datagrid("reload");
    }
</script>