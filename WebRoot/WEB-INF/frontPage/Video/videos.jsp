<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
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
	width: 50%;
	margin-left: 25%;
	cursor: pointer;
}

.sub_li{
	font-size: 1rem;
	width: 40%;
	margin-left: 30%;
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
					<span class="c-gray en">&gt;</span> <span class="c-gray">视频列表</span>
				</div>
			</nav>
			<div class="container ui-sortable">
				<div class="panel panel-default">
					<div class="panel-header">视频列表</div>
					<%-- <c:if test="${videoList != null && fn:length(list) != 0}"> --%>
						<div class="panel-body" style="min-height: 720px;">
							<%-- <c:forEach items="${chapters }" var="item" varStatus="vs">
								<li class="Huialert Huialert-success doc_li">${item.title}</li>
							</c:forEach> --%>
							<c:forEach items="${videoList}" var="item" varStatus="vs">
								<li class="Huialert Huialert-info sub_li" onclick="showDetail('${item.videoUrl}')">${item.name}</li>
							</c:forEach>
						</div>
					<%-- </c:if> --%>
				</div>

			</div>

			<jsp:include page="../modules/left_annos.jsp"></jsp:include>
			<jsp:include page="../modules/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
<script type="text/javascript">
function showDetail(url){
	window.open(url);
}
</script>
</html>
