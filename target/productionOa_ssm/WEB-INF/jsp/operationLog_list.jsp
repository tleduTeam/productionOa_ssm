<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<table class="easyui-datagrid" id="operationLogList" title="角色列表" data-options="singleSelect:false,collapsible:true,
		pagination:true,rownumbers:true,url:'operationLog/list',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_operationLog">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',align:'center',width:150">编号</th>
            <th data-options="field:'come_time',align:'center',width:150">操作时间</th>
            <th data-options="field:'l_desc',align:'center',width:150">操作描述</th>
            <th data-options="field:'username',width:150,align:'center'">操作用户</th>
        </tr>
    </thead>
</table>

<div  id="toolbar_operationLog" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
	<c:forEach items="${sessionScope.sysPermissionList}" var="per" >
			
		<c:if test="${per=='operationLog:delete' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="operationLog_delete()">删除</a>  
		    </div>  
		</c:if>
	</c:forEach>
	
	<div class="datagrid-btn-separator"></div>  
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="operationLog_reload()">刷新</a>  
	</div>  
	
    <div id="search_operationLog" style="float: right;">
        <input id="search_text_operationLog" class="easyui-searchbox"  
            data-options="searcher:doSearch_operationLog,prompt:'请输入...',menu:'#menu_operationLog'"  
            style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_operationLog" style="width:120px"> 
			<div data-options="name:'operationLogId'">编　　号</div> 
			<div data-options="name:'operationLogName'">用户名称</div>
		</div>     
    </div>  
</div>  

<div id="operationLogEditWindow" class="easyui-window" title="编辑角色" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'operationLog/edit'" style="width:45%;height:60%;padding:10px;">
</div>
<div id="operationLogAddWindow" class="easyui-window" title="添加角色" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'operationLog/add'" style="width:45%;height:60%;padding:10px;">
</div>

<div id="permissionWindow" class="easyui-window" title="权限管理" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'operationLog/permission'" style="width:45%;height:60%;padding:10px;">
</div>
<script>
function doSearch_operationLog(value,name){ //用户输入用户名,点击搜素,触发此函数  
	if(value == null || value == ''){
		
		$("#operationLogList").datagrid({
	        title:'角色列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_operationLog", url:'operationLog/list', method:'get', loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'id', width : 150, align:'center', title : '编号'},
				{field : 'come_time', width : 150, align : 'center', title : '操作时间'},
				{field : 'l_desc', width : 150, align : 'center', title : '操作描述'},
				{field : 'username', width : 150, title : '操作用户', align:'center'}
	        ] ],  
	    });
	}else{
		$("#operationLogList").datagrid({  
	        title:'角色列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_operationLog", url:'operationLog/search_operationLog_by_'+name+'?searchValue='+value,
			loadMsg:'数据加载中......', fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'id', width : 150, align:'center', title : '编号'},
				{field : 'come_time', width : 150, align : 'center', title : '操作时间'},
				{field : 'l_desc', width : 150, align : 'center', title : '操作描述'},
				{field : 'username', width : 150, title : '操作用户', align:'center'}
	        ] ],  
	    });
	}
}

	//格式化客户介绍
	function formatPermission(value, row, index){ 
		return "<a href=javascript:openPermission("+index+")>"+"权限"+"</a>";
	}
	function formatoperationLogStatus(value){
        if (value == 1){
            return '有效';
        }else if(value == 2){
        	return '<span style="color:red;">锁定</span>';
        }else {
        	return '<span style="color:#E5B717;">未知状态角色</span>';
        }
    }
	
	//根据index拿到该行值
	function onoperationLogClickRow(index) {
		var rows = $('#operationLogList').datagrid('getRows');
		return rows[index];
		
	}
	
	function getoperationLogSelectionsIds(){
    	var operationLogList = $("#operationLogList");
    	var sels = operationLogList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(","); 
    	
    	return ids;
    }
	var j;
	function  openPermission(index){ 
		var data = onoperationLogClickRow(index);
		$("#permissionWindow").window({
    		onLoad :function(){
    			//回显数据
    			$("#permissionForm").form("load", data);
    			operationLogPermissionInit();
    		}
    	}).window("open");
	};
	
	function operationLog_add(){
    	$.get("operationLog/add_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			$("#operationLogAddWindow").window("open");
       		}
       	});
    }
    
    function operationLog_edit(){
    	$.get("operationLog/edit_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			var ids = getoperationLogSelectionsIds();
            	
            	if(ids.length == 0){
            		$.messager.alert('提示','必须选择一个角色才能编辑!');
            		return ;
            	}
            	if(ids.indexOf(',') > 0){
            		$.messager.alert('提示','只能选择一个角色!');
            		return ;
            	}
            	
            	$("#operationLogEditWindow").window({
            		onLoad :function(){
            			//回显数据
            			var data = $("#operationLogList").datagrid("getSelections")[0];
            			$("#operationLogEditForm").form("load", data);
            			permissionInit();
            		}
            	}).window("open");
       		}
       	});
    }
    
    function operationLog_delete(){
    	$.get("operationLog/delete_judge",'',function(data){
      		if(data.msg != null){
      			$.messager.alert('提示', data.msg);
      		}else{
      			var ids = getoperationLogSelectionsIds();
            	if(ids.length == 0){
            		$.messager.alert('提示','未选中角色!');
            		return ;
            	}
            	$.messager.confirm('确认','确定删除ID为 '+ids+' 的日志吗？',function(r){
            	    if (r){
            	    	var params = {"ids":ids};
                    	$.post("operationLog/delete_batch",params, function(data){
                			if(data.status == 200){
                				$.messager.alert('提示','删除日志成功!',undefined,function(){
                					$("#operationLogList").datagrid("reload");
                				});
                			}
                		});
            	    }
            	});
      		}
      	});
    }
    
    function operationLog_reload(){
    	$("#operationLogList").datagrid("reload");
    }
</script>