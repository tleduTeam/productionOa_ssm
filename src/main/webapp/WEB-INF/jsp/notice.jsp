<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>





<style>
.book_class {
	font-size:15px;
	/* 	绝对定位 */
	position: absolute;
	left: 40%;
	/* 	文字水平居中 */
	text-align: center;
	/* 	字体白色 */
	width: 50%;
	color: #fff;
	/* 	行高45px,和高度一样,这样文字就能默认垂直居中  */
	line-height: 100px;
	height: 100px;
	/* 	超出隐藏 */
	overflow: hidden;
}
</style>
<script>
	$(function() {
		/**
			Ajax加载公告数据
		 */
		$.post("message/getMessage", function(obj) {
			for (var i = 0; i < obj.length; i++) {
				$("#express").append(
						"<li>【公告】" + obj[i].type + ":"
								+ obj[i].content + "</li>");
			}
		});
		/**
			滚动字,确保设置了绝对定位
		 */
		// 距上距离
		var top = 0;
		// 创建定时器
		var t = setInterval(start, 50);

		// 开始滚动
		function start() {
			//先设置margin-top为0
			$("#express li:first").css("margin-top", top);
			//ul的高度为25，故这里以25判断第一行是否走出ul。
			if (top < -100) {
				//如果第一行已经走出ul，将top归零，为下个li做准备
				top = 0;
				//这句话的意思是，先将首行的top设为0，拷贝该行，放置最后一行
				//注意，如果这里不将第一行的top归零，拷贝后他将保持top=-45的状态，效果就是叠加在最后一行上。
				$("#express li:first").css("margin-top", top).clone(true)
						.appendTo('#express');
				//移除首行，这时第二行变为首行
				$("#express li:first").remove();
			} else {
				//如果第一行还未走出ul，top自减
				top -= 1;
			}
		}
		/**
			当鼠标放置后停止，松开继续走
		 */

		$("#express").delegate('li', 'mousemove', function() {
			// 鼠标移入 清除定时
			clearInterval(t);
		});
		$("#express").delegate('li', 'mouseout', function() {
			// 鼠标移出 设置定时器
			t = setInterval(start, 50);
		});
	});
</script>

<div class="book_class">
	<ul id="express">
	</ul>
</div>
