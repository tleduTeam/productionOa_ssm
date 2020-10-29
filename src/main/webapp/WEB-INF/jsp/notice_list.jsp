<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<table class="easyui-datagrid" id="noticeList" title="消息列表" data-options="singleSelect:false,collapsible:true,
	pagination:true,rownumbers:true,url:'notice/list',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_notice">
    <thead>
        <tr>
			<!-- <th data-options="field:'ck',checkbox:true"></th> -->
			<th data-options="field:'type',align:'center',width:100">消息类型</th>
			<th data-options="field:'content',align:'center',width:100">消息内容</th>
			<th data-options="field:'record_time',align:'center',width:100">发布时间</th>
        </tr>
    </thead>
</table> 

<div  id="toolbar_notice" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
	<%-- <c:forEach items="${sessionScope.sysPermissionList}" var="per" >
		<c:if test="${per=='notice:add' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="notice_add()">新增</a>  
		    </div>  
		</c:if>
		<c:if test="${per=='notice:edit' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="notice_edit()">编辑</a>  
		    </div>  
		</c:if>
		<c:if test="${per=='notice:delete' }" >
		    <div style="float: left;">  
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="notice_delete()">删除</a>  
		    </div>  
		</c:if>
	</c:forEach> --%>
	
	<!-- <div class="datagrid-btn-separator"></div>   -->
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="notice_reload()">刷新</a>  
	</div>  
	
	<!-- 模糊查询 -->
    <!-- <div id="search_notice" style="float: right;">
        <input id="search_text_notice" class="easyui-searchbox"  
            data-options="searcher:doSearch_notice,prompt:'请输入...',menu:'#menu_notice'"  
            style="width:250px;vertical-align: middle;">
        </input>
        <div id="menu_notice" style="width:120px"> 
			<div data-options="name:'id'">编号</div> 
			<div data-options="name:'title'">消息标题</div> 
			<div data-options="name:'content'">消息内容</div> 
			<div data-options="name:'type'">消息类型</div>
			<div data-options="name:'user_id'">发布者</div>
			<div data-options="name:'record_time'">发布时间</div>
		</div>     
    </div>  --> 
</div>  

<!-- <div id="noticeEditWindow" class="easyui-window" title="编辑消息" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'notice/edit'" style="width:65%;height:80%;padding:10px;">
</div>
<div id="noticeAddWindow" class="easyui-window" title="添加消息" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'notice/add'" style="width:65%;height:80%;padding:10px;">
</div> -->


<script>

/* function doSearch_notice(value,name){ //用户输入用户名,点击搜素,触发此函数  
	if(value == null || value == ''){
		$("#noticeList").datagrid({
	        title:'消息列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_notice", url:'notice/list', method:'get', loadMsg:'数据加载中......',
			fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
				{field : 'ck', checkbox:true },
				{field : 'id', width : 100, align:'center', title : '消息编号'},
				{field : 'title', width : 100, align:'center', title : '消息标题'},
	        ] ],  
	    });
	}else{
		$("#noticeList").datagrid({  
	        title:'消息列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
			nowrap:true, toolbar:"toolbar_notice", url:'notice/search_notice_by_'+name+'?searchValue='+value,
			loadMsg:'数据加载中......', fitColumns:true,//允许表格自动缩放,以适应父容器
	        columns : [ [ 
	             	{field : 'ck', checkbox:true }, 
	             	{field : 'id', width : 100, title : '编号', align:'center'},
	            	{field : 'title', width : 100, align:'center', title : '消息标题'},
					{field : 'content', width : 100, align:'center', title : '消息内容'},
					{field : 'type', width : 100, align:'center', title : '消息类型'},
					{field : 'user_id', width : 100, align:'center', title : '发布者'},
					{field : 'record_time', width : 100, align:'center', title : '发布时间'},
	        ] ],  
	    });
	}
}
	

	//根据index拿到该行值
	function onnoticeClickRow(index) {
		var rows = $('#noticeList').datagrid('getRows');
		return rows[index];
		
	}

	
    function getnoticeSelectionsIds(){
    	var noticeList = $("#noticeList");
    	var sels = noticeList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(","); 
    	
    	return ids;
    }
    
    function notice_add(){
    	$.get("notice/add_judge",'',function(data){
       		if(data.msg != null){
       			$.noticer.alert('提示', data.msg);
       		}else{
       			$("#noticeAddWindow").window("open");
       		}
       	});
    }
    
    function notice_edit(){
    	$.get("notice/edit_judge",'',function(data){
       		if(data.msg != null){
       			$.noticer.alert('提示', data.msg);
       		}else{
       			var ids = getnoticeSelectionsIds();
               	
               	if(ids.length == 0){
               		$.noticer.alert('提示','必须选择一个消息才能编辑!');
               		return ;
               	}
               	if(ids.indexOf(',') > 0){
               		$.noticer.alert('提示','只能选择一个消息!');
               		return ;
               	}
               	
               	$("#noticeEditWindow").window({
               		onLoad :function(){
               			//回显数据
               			var data = $("#noticeList").datagrid("getSelections")[0];
               			$("#noticeEditForm").form("load", data);
               			noticeEditEditor.html(data.note);
               		}
               	}).window("open");
       		}
       	});
    }
    
    function notice_delete(){
    	$.get("notice/delete_judge",'',function(data){
      		if(data.msg != null){
      			$.noticer.alert('提示', data.msg);
      		}else{
      			var ids = getnoticeSelectionsIds();
              	if(ids.length == 0){
              		$.noticer.alert('提示','未选中消息!');
              		return ;
              	}
              	$.noticer.confirm('确认','确定删除ID为 '+ids+' 的消息吗？',function(r){
              	    if (r){
              	    	var params = {"ids":ids};
                      	$.post("notice/delete_batch",params, function(data){
                  			if(data.status == 200){
                  				$.noticer.alert('提示','删除消息成功!',undefined,function(){
                  					$("#noticeList").datagrid("reload");
                  				});
                  			}
                  		});
              	    }
              	});
      		}
      	});
    }
    
    function notice_reload(){
    	$("#noticeList").datagrid("reload");
    } */
</script>