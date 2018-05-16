<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<jsp:include page="../modules/common.jsp"></jsp:include>

<style type="text/css">

</style>

</head>
<body>
	<div id="modal-demo-post" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align:center;">发帖</h3>
					<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
				</div>
				<div class="modal-body" style="min-height:500px;">
					<div class="containBox">
						<div class="wap-container">
						<br/>
							<div class="formControls">
								<input id="post_theme" class="input-text" placeholder="帖子主题">
							</div>
						</div>
						<br/>
						<div>
							<textarea id="post_content" rows="" cols="" style="min-height:400px;" class="textarea" placeholder="在这输入内容，你要说点什么呢？"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="add()">确定</button>
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function add(){
		var theme = $("#post_theme").val();
		var content = $("#post_content").val();
		$.ajax({
			url:"${pageContext.request.contextPath }/discuz_newPost.action",
			data:{
				"post.theme":theme,
				"post.content":content
			},
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.success){
					window.location.reload();
				}else{
					alert("发帖失败");
				}
			}
		})
	}
</script>
</html>
