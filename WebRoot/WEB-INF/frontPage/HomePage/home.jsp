﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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
	font-size: 1.25rem;
	list-style-type: none;
	width: 75%;
	margin-left: 12.5%;
	cursor: pointer;
}

.sub_li{
	font-size: 1rem;
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
					
				</div>
			</nav>
			<div class="container">
				<div class="panel panel-default">
					<div class="panel-header">知识点列表</div>
					<div class="panel-body" style="min-height: 720px;">
						<c:forEach items="${chapters }" var="item" varStatus="vs">
							<details >
								<summary class="Huialert Huialert-success doc_li">${item.title }</summary>
								<c:forEach items="${item.sections }" var="subItem">
									<li class="Huialert Huialert-info sub_li" onclick="toDocList('${subItem.id}')">${subItem.title }&nbsp;&nbsp;&nbsp;&nbsp;${subItem.subTitle}</li>
								</c:forEach>
							</details>
						</c:forEach>
					</div>
				</div>
			</div>
			<form action="${pageContext.request.contextPath }/doc_uploadDoc.action" method="post" enctype="multipart/form-data">
				<input name="myfile" type="file"/>
				<input value="上传" type="submit"/>
			</form>

			<jsp:include page="../modules/left_annos.jsp"></jsp:include>
			<jsp:include page="../modules/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$("#know").prop("class","current");
})
function toDocList(secId){
	window.location.href="${pageContext.request.contextPath}/home_toKonwledges.action?secId="+secId;
}
</script>
</html>
<!--H-ui前端框架提供前端技术支持 h-ui.net @2017-01-01 -->