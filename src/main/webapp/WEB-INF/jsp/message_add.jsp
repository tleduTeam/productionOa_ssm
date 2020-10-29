<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">

<link href="css/uploadfile.css" rel="stylesheet">
<script src="js/jquery.uploadfile.js"></script>
<script src="js/malsup.github.iojquery.form.js"></script>

<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<div style="padding:10px 10px 10px 10px">
	<form id="messageAddForm" class="messageForm" method="post">
		<table cellpadding="6" >
			<tr>
				<td>消息编号:</td>
				<td>
					<input class="easyui-textbox" type="text" name="id" data-options="required:true"></input>
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
					<!-- <input class="easyui-datetimebox" name="record_time" data-options="required:true,showSeconds:false" 
					style="width:150px"> -->
					<input id="databox" class="easyui-datetimebox" class="easyui-datebox" name="record_time"  type="text"
					/>
				</td>
			</tr>

		</table>
	</form>
	<div style="padding:5px">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitmessageAddForm()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearmessageADDForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	//提交表单
	function submitmessageAddForm(){
		//有效性验证
		if(!$('#messageAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}

		//ajax的post方式提交表单
		//$("#messageAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("message/insert",$("#messageAddForm").serialize(), function(data){

			if(data.status == 200){
				$.messager.alert('提示','新增消息成功!');
				clearmessageADDForm();
				$("#messageAddWindow").window('close');
				$("#messageList").datagrid("reload");
			}else{
				$.messager.alert('提示',data.msg);
				alert(data.status);

			}
		});
	}

	function clearmessageADDForm(){
		$('#messageAddForm').form('reset');
	}
</script>