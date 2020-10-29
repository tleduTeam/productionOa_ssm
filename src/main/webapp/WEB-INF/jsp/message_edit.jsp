<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet">
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="pediting:10px 10px 10px 10px">
	<form id="messageEditForm" class="messageForm" method="post">
		<table cellpediting="6" >
			<tr>
				<td>消息编号:</td>
				<td>
					<input class="easyui-textbox" type="text" name="id" data-options="required:true" readonly="readonly"></input>
				</td>
			</tr>
			<tr>
				<td>消息标题:</td>
				<td>
					<input id="institution" class="easyui-textbox"  type="text" name="title"
						   data-options="required:true" />
				</td>
			</tr>
			<tr>
				<td>消息内容:</td>
				<td>
					<input id="simple" class="easyui-textbox" name="content"  type="text"
					/>
				</td>
			</tr>
			<tr>
				<td>消息类型:</td>
				<td>
					<!-- <input id="simple" class="easyui-textbox" name="messageType_id"  type="text"
					/> -->
			<input data-toggle="topjui-radiobutton" name="messageType_id" value="1"  type="radio">公告消息
      
            <input data-toggle="topjui-radiobutton" name="messageType_id" value="2"  type="radio">新闻消息
      
            <input data-toggle="topjui-radiobutton" name="messageType_id" value="3"  type="radio">奖惩消息
				</td>
			</tr>
			<tr>
				<td>发布者:</td>
				<td>
					<!-- <input id="simple" class="easyui-textbox" name="User_id"  type="text"
					/> -->
					<input data-toggle="topjui-radiobutton" name="user_id" value="admin"  type="radio" checked="checked">
					admin （不可更改）
				</td>
			</tr>
			<tr>
				<td>发布时间:</td>
				<td>
					<!-- <input id="simple" class="easyui-textbox" name="record_time"  type="text"
					/> -->
					<input id="databox" class="easyui-datetimebox" class="easyui-datebox" name="record_time"  type="text"
					/>
				</td>
			</tr>
		</table>
	</form>
	<div style="pediting:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitmessageEditForm()">提交</a>
	</div>
</div>
<script type="text/javascript">


	function submitmessageEditForm(){
		if(!$('#messageEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}

		$.post("message/update_all",$("#messageEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改消息成功!','info',function(){
					$("#messageEditWindow").window('close');
					$("#messageList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
</script>
