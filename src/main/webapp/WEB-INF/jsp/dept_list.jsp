<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<table class="easyui-datagrid" id="deptList" title="部门列表" data-options="singleSelect:false,collapsible:true,
	pagination:true,rownumbers:true,url:'dept/list',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_dept">
    <thead>
        <tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'id',align:'center',width:100">编号</th>
			<th data-options="field:'name',align:'center',width:100">部门名称</th>
			<th data-options="field:'phone',align:'center',width:100">移动电话</th>
			<th data-options="field:'tel',align:'center',width:100">部门电话</th>
			<th data-options="field:'username',align:'center',width:100">部门负责人</th>
			<th data-options="field:'name1',align:'center',width:100">负责机构</th>
        </tr>
    </thead>
</table> 

<div  id="toolbar_dept" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
	<c:forEach items="${sessionScope.sysPermissionList}" var="per" >
		<c:if test="${per=='dept:add' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="dept_add()">新增</a>  
		    </div>  
		</c:if>
		<c:if test="${per=='dept:edit' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="dept_edit()">编辑</a>  
		    </div>  
		</c:if>
		<c:if test="${per=='dept:delete' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="dept_delete()">删除</a>  
		    </div>  
		</c:if>
	</c:forEach>
	
	<div class="datagrid-btn-separator"></div>  
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="dept_reload()">刷新</a>  
	</div>  
	
	<!-- 模糊查询 -->
    <div id="search_dept" style="float: right;">
        <input id="search_text_dept" class="easyui-searchbox"  
            data-options="searcher:doSearch_dept,prompt:'请输入...',menu:'#menu_dept'"  
            style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_dept" style="width:120px"> 
			<div data-options="name:'id'">编号</div> 
			<div data-options="name:'name'">部门名称</div> 
			<div data-options="name:'phone'">移动电话</div> 
			<div data-options="name:'tel'">部门电话</div> 
			<div data-options="name:'username'">部门负责人</div> 
			<div data-options="name:'name1'">负责机构</div> 
		</div>     
    </div>  
</div>  

<div id="deptEditWindow" class="easyui-window" title="编辑机构" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'dept/edit'" style="width:65%;height:80%;padding:10px;">
</div>
<div id="deptAddWindow" class="easyui-window" title="添加机构" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'dept/add'" style="width:65%;height:80%;padding:10px;">
</div>


<script>

function doSearch_dept(value,name){ //用户输入用户名,点击搜素,触发此函数  
	if(value == null || value == ''){
		$("#deptList").datagrid({
	        title:'机构列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_dept", url:'dept/list', method:'get', loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'id', width : 100, align:'center', title : '编号'},
				{field : 'name', width : 100, align:'center', title : '部门名称'},
				{field : 'phone', width : 100, align:'center', title : '移动电话'},
				{field : 'tel', width : 100, align:'center', title : '部门电话'},
				{field : 'username', width : 100, align:'center', title : '部门负责人'},
				{field : 'name1', width : 100, align:'center', title : '部门机构'}
	        ] ],  
	    });
	}else{
		$("#deptList").datagrid({  
	        title:'机构列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_dept", url:'dept/search_dept_by_'+name+'?searchValue='+value,
			loadMsg:'数据加载中......', fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
	            {field : 'ck', checkbox:true },
				{field : 'id', width : 100, align:'center', title : '编号'},
				{field : 'name', width : 100, align:'center', title : '部门名称'},
				{field : 'phone', width : 100, align:'center', title : '移动电话'},
				{field : 'tel', width : 100, align:'center', title : '部门电话'},
				{field : 'username', width : 100, align:'center', title : '部门负责人'},
				{field : 'name1', width : 100, align:'center', title : '部门机构'}
	        ] ],  
	    });
	}
}
	

	//根据index拿到该行值
	function ondeptClickRow(index) {
		var rows = $('#deptList').datagrid('getRows');
		return rows[index];
		
	}

	
    function getdeptSelectionsIds(){
    	var deptList = $("#deptList");
    	var sels = deptList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(","); 
    	
    	return ids;
    }
    
    function dept_add(){
    	$.get("dept/add_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			$("#deptAddWindow").window("open");
       		}
       	});
    }
    
    function dept_edit(){
    	$.get("dept/edit_judge",'',function(data){
       		if(data.msg != null){
       			$.messager.alert('提示', data.msg);
       		}else{
       			var ids = getdeptSelectionsIds();
               	
               	if(ids.length == 0){
               		$.messager.alert('提示','必须选择一个机构才能编辑!');
               		return ;
               	}
               	if(ids.indexOf(',') > 0){
               		$.messager.alert('提示','只能选择一个机构!');
               		return ;
               	}
               	
               	$("#deptEditWindow").window({
               		onLoad :function(){
               			//回显数据
               			var data = $("#deptList").datagrid("getSelections")[0];
               			$("#deptEditForm").form("load", data);
               			deptEditEditor.html(data.note);
               		}
               	}).window("open");
       		}
       	});
    }
    
    function dept_delete(){
    	$.get("dept/delete_judge",'',function(data){
      		if(data.msg != null){
      			$.messager.alert('提示', data.msg);
      		}else{
      			var ids = getdeptSelectionsIds();
              	if(ids.length == 0){
              		$.messager.alert('提示','未选中机构!');
              		return ;
              	}
              	$.messager.confirm('确认','确定删除ID为 '+ids+' 的机构吗？',function(r){
              	    if (r){
              	    	var params = {"ids":ids};
                      	$.post("dept/delete_batch",params, function(data){
                  			if(data.status == 200){
                  				$.messager.alert('提示','删除机构成功!',undefined,function(){
                  					$("#deptList").datagrid("reload");
                  				});
                  			}
                  		});
              	    }
              	});
      		}
      	});
    }
    
    function dept_reload(){
    	$("#deptList").datagrid("reload");
    }
</script>