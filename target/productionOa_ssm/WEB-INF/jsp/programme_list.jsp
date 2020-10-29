<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<table class="easyui-datagrid" id="programmeList" title="日程列表" data-options="singleSelect:false,collapsible:true,
	pagination:true,rownumbers:true,url:'programme/list',method:'get',pageSize:10,fitColumns:true,toolbar:toolbar_programme">
	<thead>
	<tr>
		<th data-options="field:'ck',checkbox:true"></th>
		<th data-options="field:'id',align:'center',width:100">日程编号</th>
		<th data-options="field:'title',align:'center',width:100">日程标题</th>
		<th data-options="field:'address',align:'center',width:100">会议地址</th>
		<th data-options="field:'begin_time',align:'center',width:100">会议开始时间</th>
		<th data-options="field:'end_time',align:'center',width:100">会议结束时间</th>
		<th data-options="field:'content',align:'center',width:100">会议内容</th>
		<th data-options="field:'user_id',align:'center',width:100">创建者</th>
		<th data-options="field:'create_date',align:'center',width:100">创建时间</th>
	</tr>
	</thead>
</table>

<div  id="toolbar_programme" style=" height: 22px; padding: 3px 11px; background: #fafafa;">

	<c:forEach items="${sessionScope.sysPermissionList}" var="per" >
		<c:if test="${per=='programme:add' }" >
			<div style="float: left;">
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="programme_add()">新增</a>
			</div>
		</c:if>
		<c:if test="${per=='programme:edit' }" >
			<div style="float: left;">
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="programme_edit()">编辑</a>
			</div>
		</c:if>
		<c:if test="${per=='programme:delete' }" >
			<div style="float: left;">
				<a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel" onclick="programme_delete()">删除</a>
			</div>
		</c:if>
	</c:forEach>

	<div class="datagrid-btn-separator"></div>

	<div style="float: left;">
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="programme_reload()">刷新</a>
	</div>

	<!-- 模糊查询 -->
	<div id="search_programme" style="float: right;">
		<input id="search_text_programme" class="easyui-searchbox"
			   data-options="searcher:doSearch_programme,prompt:'请输入...',menu:'#menu_programme'"
			   style="width:250px;vertical-align: middle;">
		</input>
		<div id="menu_programme" style="width:120px">
			<div data-options="name:'id'">日程编号</div>
			<div data-options="name:'title'">日程标题</div>
			<div data-options="name:'content'">会议内容</div>
		</div>
	</div>
</div>

<div id="programmeEditWindow" class="easyui-window" title="编辑日程" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'programme/edit'" style="width:65%;height:80%;padding:10px;">
</div>
<div id="programmeAddWindow" class="easyui-window" title="添加日程" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'programme/add'" style="width:65%;height:80%;padding:10px;">
</div>


<script>

	function doSearch_programme(value,name){ //用户输入用户名,点击搜素,触发此函数
		if(value == null || value == ''){
			$("#programmeList").datagrid({
				title:'日程列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
				nowrap:true, toolbar:"toolbar_programme", url:'programme/list', method:'get', loadMsg:'数据加载中......',
				fitColumns:true,//允许表格自动缩放,以适应父容器
				columns : [ [
					{field : 'ck', checkbox:true },
					{field : 'id', width : 100, align:'center', title : '日程编号'},
					{field : 'title', width : 100, align:'center', title : '日程标题'},
					{field : 'address', width : 100, align:'center', title : '会议地址'},
					{field : 'begin_time', width : 100, align:'center', title : '会议开始时间'},
					{field : 'end_time', width : 100, align:'center', title : '会议结束时间'},
					{field : 'content', width : 100, align:'center', title : '会议内容'},
					{field : 'user_id', width : 100, align:'center', title : '创建者'},
					{field : 'create_date', width : 100, align:'center', title : '创建时间'},
				] ],
			});
		}else{
			$("#programmeList").datagrid({
				title:'日程列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true, method:'get',
				nowrap:true, toolbar:"toolbar_programme", url:'programme/search_programme_by_'+name+'?searchValue='+value,
				loadMsg:'数据加载中......', fitColumns:true,//允许表格自动缩放,以适应父容器
				columns : [ [
					{field : 'ck', checkbox:true },
					{field : 'id', width : 100, align:'center', title : '日程编号'},
					{field : 'title', width : 100, align:'center', title : '日程标题'},
					{field : 'address', width : 100, align:'center', title : '会议地址'},
					{field : 'begin_time', width : 100, align:'center', title : '会议开始时间'},
					{field : 'end_time', width : 100, align:'center', title : '会议结束时间'},
					{field : 'content', width : 100, align:'center', title : '会议内容'},
					{field : 'user_id', width : 100, align:'center', title : '创建者'},
					{field : 'create_date', width : 100, align:'center', title : '创建时间'},
				] ],
			});
		}
	}


	//根据index拿到该行值
	function onProgrammeClickRow(index) {
		var rows = $('#programmeList').datagrid('getRows');
		return rows[index];

	}


	function getProgrammeSelectionsIds(){
		var programmeList = $("#programmeList");
		var sels = programmeList.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].id);
		}
		ids = ids.join(",");

		return ids;
	}

	function programme_add(){
		$.get("programme/add_judge",'',function(data){
			if(data.msg != null){
				$.messager.alert('提示', data.msg);
			}else{
				$("#programmeAddWindow").window("open");
			}
		});
	}

	function programme_edit(){
		$.get("programme/edit_judge",'',function(data){
			if(data.msg != null){
				$.messager.alert('提示', data.msg);
			}else{
				var ids = getProgrammeSelectionsIds();

				if(ids.length == 0){
					$.messager.alert('提示','必须选择一个日程才能编辑!');
					return ;
				}
				if(ids.indexOf(',') > 0){
					$.messager.alert('提示','只能选择一个日程!');
					return ;
				}

				$("#programmeEditWindow").window({
					onLoad :function(){
						//回显数据
						var data = $("#programmeList").datagrid("getSelections")[0];
						$("#programmeEditForm").form("load", data);
						programmeEditEditor.html(data.note);
					}
				}).window("open");
			}
		});
	}

	function programme_delete(){
		$.get("programme/delete_judge",'',function(data){
			if(data.msg != null){
				$.messager.alert('提示', data.msg);
			}else{
				var ids = getProgrammeSelectionsIds();
				if(ids.length == 0){
					$.messager.alert('提示','未选中日程!');
					return ;
				}
				$.messager.confirm('确认','确定删除ID为 '+ids+' 的日程吗？',function(r){
					if (r){
						var params = {"ids":ids};
						$.post("programme/delete_batch",params, function(data){
							if(data.status == 200){
								$.messager.alert('提示','删除日程成功!',undefined,function(){
									$("#programmeList").datagrid("reload");
								});
							}
						});
					}
				});
			}
		});
	}

	function programme_reload(){
		$("#programmeList").datagrid("reload");
	}
</script>