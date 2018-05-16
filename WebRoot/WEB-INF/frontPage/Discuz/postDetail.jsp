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
.ui-sortable .panel-header {
	cursor: move
}

.doc_li {
	font-size: 1.75rem;
	list-style-type: none;
	width: 75%;
	margin-left: 12.5%;
	cursor: pointer;
}

.sub_li{
	font-size: 1.25rem;
	width: 60%;
	margin-left: 20%;
	list-style-type: none;
	cursor:pointer;
}
</style>
<title>编译原理教程网站</title>
<meta name="keywords" content="关键词,5个左右,单个8汉字以内">
<meta name="description" content="网站描述，字数尽量空制在80个汉字，160个字符以内！">
</head>
<body>

	<div class="containBox">
		<jsp:include page="../modules/top_menu.jsp"></jsp:include>
		<div class="wap-container">
			<nav class="breadcrumb">
				<div class="container">
					<i class="Hui-iconfont">&#xe67f;</i> <a href="/jxwz_new/home_toHome.action" class="c-primary">首页</a>
					<span class="c-gray en">&gt;</span> <span class="c-gray">教学资料</span>
					<span class="c-gray en">&gt;</span> <span class="c-gray">热烈讨论</span>
				</div>
			</nav>
			<div class="container ui-sortable">
				<div class="panel panel-default" style="width:50%;margin-left:25%; min-height: 720px;">
					<div class="panel-header" >
						<span>主题：${detail.theme }</span>
						<span style="float:right;">发帖人：${detail.user.name }</span>
					</div>
					<br/>
					<div class="panel-body">
						<div>
							<div style="display:none;">占位用div</div>
							<span>${detail.content}</span>
							<span style="float:right;">
								<input id="postIdIpt" type="hidden" value="${detail.id }" />
								<input type="hidden" value="1" />
								<a class="replyBtn" href="#" style="color:blue;">回复</a>
							</span>
						</div>
						<br/>
						<br/>
						<hr/>
						
						<c:forEach items="${replys }" var="item" >
							<div class="Huialert Huialert-info" style="overflow: hidden;">
								<div> ${item.floorNum}L </div>
								<div> ${item.content } </div>
								<div> ${item.user.name } </div>
								<span style="float:right;">
									<input type="hidden" value="${item.id}" />
									<input type="hidden" value="2" />
									<a class="replyBtn" href="#" style="color:blue;">回复</a>
								</span>
							</div>
						</c:forEach>
						
					</div>
				</div>
			</div>
			
			<div id="post-reply" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content radius">
						<div class="modal-header">
							<h3 class="modal-title" style="text-align: center;">回复贴子</h3>
						</div>
						<div class="modal-body" style="max-height: 550px;">
							<div class="containBox">
								<div class="panel-body">回复的贴子内容</div>
								<div>
									<textarea id="target_content" rows="" cols="" style="min-height: 150px;" class="textarea" readonly="readonly"></textarea>
								</div>
								<br />
								<div class="panel-body">你要说的</div>
								<div>
									<textarea id="post_content" rows="" cols="" style="min-height: 250px;" class="textarea" placeholder="输入回复内容"></textarea>
								</div>
							</div>
							<input type="hidden" id="replyType"/>
							<input type="hidden" id="rid"/>
						</div>
						<div class="modal-footer">
							<button id="submitBtnPost" class="btn btn-primary" type="submit">确定</button>
							<button class="btn btn-primary" data-dismiss="modal"
								aria-hidden="true">关闭</button>
						</div>
					</div>
				</div>
			</div>
			
			<jsp:include page="../modules/left_annos.jsp"></jsp:include>
			<jsp:include page="../modules/footer.jsp"></jsp:include>
			
		</div>
	</div>
</body>
<script type="text/javascript">

$(".replyBtn").bind("click",function(){
	var obj = $(this);
	$.ajax({
		url:"${pageContext.request.contextPath}/login_checkLogin.action",
		type:"post",
		dataType:"json",
		success:function(data){
			if(!data.login){
				alert("请先登陆再进行回复");
			}else{
				var replyType = obj.parent().children().eq(1).val();
				var id = obj.parent().children().eq(0).val();
				$("#replyType").val(replyType);
				$("#rid").val(id);
				$("#target_content").html(obj.parent().parent().children().eq(1).html());
				$("#post-reply").modal("show");
			}
		}
	})
})

$("#submitBtnPost").bind("click",function(){
	var replyType = $("#replyType").val();
	var rid = $("#rid").val();
	var content = $("#post_content").val();
	var userId = "${userId}";
	var postId = $("#postIdIpt").val();
	if(content == null || "" == content || typeof(content) == "undefined"){
		alert("请输入内容");
		return;
	}
	$.ajax({
		url:"${pageContext.request.contextPath}/discuz_savePostReply.action",
		type:"post",
		data:{
			"replyType":replyType,
			"post.id":postId,
			"targetReply.id":rid,
			"postReply.content":content,
			"user.id":userId
		},
		dataType:"json",
		success:function(data){
			if(data.success){
				modalalertreply("回复成功");
				window.location.reload();
			}else{
				modalalertreply("回复失败");
			}
		}
	})
})

function modalalertreply(msg){
	$.Huimodalalert(msg,1000);
	$("#post-reply").modal("hide");
}

$(function(){
	$("#discuz").prop("class","current");
})

function newPost(){
	$.ajax({
		url:"${pageContext.request.contextPath}/login_checkLogin.action",
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.login == false){
				alert("请登录后再发帖");
				return;
			}
			
			$("#modal-demo-post").modal("show")
		}
	})
}
</script>
</html>
