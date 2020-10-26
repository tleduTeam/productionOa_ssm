<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet">
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="pediting:10px 10px 10px 10px">
	<form id="branchEditForm" class="branchForm" method="post">
		<table cellpediting="3" >
			<tr>
				<td>机构编号:</td>
				<td>
					<input class="easyui-textbox" type="text" name="id" data-options="required:true" readonly="readonly"></input>
				</td>
			</tr>
			<tr>
				<td>机构名称:</td>
				<td>
					<input id="institution" class="easyui-textbox"  type="text" name="name"
						   data-options="required:true" />
				</td>
			</tr>
			<tr>
				<td>机构简称:</td>
				<td>
					<input id="simple" class="easyui-textbox" name="short_name"  type="text"
					/>
				</td>
			</tr>


		</table>
	</form>
	<div style="pediting:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitBranchEditForm()">提交</a>
	</div>
</div>
<script type="text/javascript">


	function submitBranchEditForm(){
		if(!$('#branchEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}

		$.post("branch/update_all",$("#branchEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改订单成功!','info',function(){
					$("#branchEditWindow").window('close');
					$("#branchList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
</script>
