<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet">
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="pediting:10px 10px 10px 10px">
	<form id="staffEditForm" class="staffForm" method="post">
		<table cellpediting="3" >
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
	<div style="pediting:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitstaffEditForm()">提交</a>
	</div>
</div>
<script type="text/javascript">


	function submitstaffEditForm(){
		if(!$('#staffEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}

		$.post("staff/update_all",$("#staffEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改订单成功!','info',function(){
					$("#staffEditWindow").window('close');
					$("#staffList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
</script>
