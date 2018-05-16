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
				<div class="panel panel-default" style="min-height: 720px;">
					<div class="panel-header">
						帖子列表
						<button onclick="newPost()" class="btn btn-primary radius size-S" style="float:right;">发帖</button>
					</div>
					<br/>
					<c:forEach items="${posts }" var ="item" varStatus="vs">
						<li style="font-size:1rem; width:60%;margin-left:20%;margin-bottom:15px;">
							<a href="${pageContext.request.contextPath }/discuz_toDetail.action?postId=${item.id}"><span>${item.theme}</span><span style="float:right;">${item.createDateStr }</span></a>
						</li>
					</c:forEach>
				</div>
			<input type="hidden" id="totalCountPost" value="${totalCountPost }" />
			<input type="hidden" id="pageNum" value="${pageNum }" />
			<fieldset id="pagerPost" class="layui-elem-field layui-field-title" style="margin-top: 30px;text-align:center;"> </fieldset>
			</div>
			
			<jsp:include page="../Discuz/newPost.jsp"></jsp:include>
			
			<jsp:include page="../modules/left_annos.jsp"></jsp:include>
			<jsp:include page="../modules/footer.jsp"></jsp:include>
			
		</div>
	</div>
</body>
<script type="text/javascript">
var pageNum = $("#pageNum").val();

layui.use('laypage', function() {
	var laypage = layui.laypage;
	var totalCount = $("#totalCountPost").val();
	laypage.render({
		elem : 'pagerPost',
		count : totalCount,
		limit : 10,
		curr : pageNum,
		jump : function(obj) {
			currentPage = obj.curr;
			$(".layui-laypage").bind("click",function(){
				window.location.href="${pageContext.request.contextPath}/home_toDiscuz.action?pageNum="+obj.curr;
			})
		}
	});
});

$(function(){
	$("#discuz").prop("class","current");
})
function toDocList(secId){
	window.location.href="${pageContext.request.contextPath}/home_toKonwledges.action?secId="+secId;
}

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
