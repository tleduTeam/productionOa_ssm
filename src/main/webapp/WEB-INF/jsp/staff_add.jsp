<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet">
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<div style="padding:10px 10px 10px 10px">
	<form id="staffAddForm" class="staffForm" method="post">
		<table cellpadding="3" >
			<tr>
				<td>编号:</td>
				<td>
					<input class="easyui-textbox" type="text" name="id" data-options="required:true"></input>
				</td>
			</tr>
			<tr>
				<td>员工姓名:</td>
				<td>
					<input class="easyui-textbox" type="text" name="name" data-options="required:true"></input>
				</td>
			</tr>
			<tr>
				<td>性别:</td>
				<td>
					<input type="radio" id="radio" name="sex" value="男">男</input>
					<input type="radio" id="radio" name="sex" value="女">女</input>
				</td>
			</tr>
			<tr>
				<td>状态:</td>
				<td>
					<input type="radio" id="radio" name="status" value="0">禁用</input>
					<input type="radio" id="radio" name="status" value="1">启用</input>
				</td>
			</tr>

			<tr>
				<td>所属部门:</td>
				<td>
					<input type="radio" id="radio" name="dept_id" value="1">研发部</input>
					<input type="radio" id="radio" name="dept_id" value="2">人事部</input>
				</td>
			</tr>
			
			<tr>
				<td>角色类型:</td>
				<td>
					<input type="radio" id="radio" name="role_id" value="001">超级管理员</input>
					<input type="radio" id="radio" name="role_id" value="002">订单管理员</input>
					<br>
				</td>
			</tr>

		</table>
	</form>
	<div style="padding:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitstaffAddForm()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearstaffADDForm()">重置</a>
	</div>
</div>
<script type="text/javascript">

	//提交表单
	function submitstaffAddForm(){
		
		
		//有效性验证
		if(!$('#staffAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		
		
		//ajax的post方式提交表单
		//$("#staffAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("staff/insert",$("#staffAddForm").serialize(), function(data){

			if(data.status == 200){
				$.messager.alert('提示','新增订单成功!');
				clearstaffADDForm();
				$("#staffAddWindow").window('close');
				$("#staffList").datagrid("reload");
			}else{
				$.messager.alert('提示',data.msg);
				alert(data.status);

			}
		});
	}

	function clearstaffADDForm(){
		$('#staffAddForm').form('reset');
	}
</script>