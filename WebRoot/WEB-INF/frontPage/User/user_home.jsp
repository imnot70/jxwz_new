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
					<i class="Hui-iconfont">&#xe67f;</i> <a
						href="/jxwz_new/home_toHome.action" class="c-primary">首页</a> <span
						class="c-gray en">&gt;</span> <span class="c-gray">个人中心</span>
				</div>
			</nav>
			<div class="container">
				<div class="panel panel-default">
					<div class="panel-body" style="min-height: 720px;">
						<c:if test="${userType == 1 }">
							<div id="tab_demo" class="HuiTab">
								<div class="tabBar clearfix">
									<span class="tabSpan" id="stu1">错题收集</span>
									<span class="tabSpan" id="stu2">学习笔记</span>
									<span class="tabSpan" id="stu3">我要提问</span>
								</div>
								<div class="tabCon" id="stuDiv1">错题</div>
								<div class="tabCon" id="stuDiv2">笔记</div>
								<div class="tabCon" id="stuDiv3">提问</div>
							</div>
						</c:if>

						<c:if test="${userType == 0 }">
							<div id="tab_demo" class="HuiTab">
								<div class="tabBar clearfix">
									<span class="tabSpan" id="admin1">学生管理</span>
									<span class="tabSpan" id="admin2">教师管理</span>
									<span class="tabSpan" id="admin3">资料管理</span>
									<span class="tabSpan" id="admin4">视频管理</span>
									<span class="tabSpan" id="admin5">章节管理</span>
									<span class="tabSpan" id="admin6">公告管理</span>
								</div>
								<div class="tabCon" id="adminDiv1">
									<jsp:include page="./admin/stuMana.jsp"></jsp:include>
								</div>
								<div class="tabCon" id="adminDiv2">
									<jsp:include page="./admin/teaMana.jsp"></jsp:include>
								</div>
								<div class="tabCon" id="adminDiv3">
									<jsp:include page="./admin/knowMana.jsp"></jsp:include>
								</div>
								<div class="tabCon" id="adminDiv4">
									<jsp:include page="./admin/videoMana.jsp"></jsp:include>
								</div>
								<div class="tabCon" id="adminDiv5">
									<jsp:include page="./admin/chapterMana.jsp"></jsp:include>
								</div>
								<div class="tabCon" id="adminDiv6">
									<jsp:include page="./admin/annoMana.jsp"></jsp:include>
								</div>
							</div>
						</c:if>

						<c:if test="${userType == 2 }">
							<div id="tab_demo" class="HuiTab">
								<div class="tabBar clearfix">
									<span class="tabSpan" id="tea1">文档管理</span>
									<span class="tabSpan" id="tea2">习题管理</span>
									<span class="tabSpan" id="tea3">答疑解惑</span>
								</div>
								<div class="tabCon" id="teaDiv1">学生管理</div>
								<div class="tabCon" id="teaDiv2">教师管理</div>
								<div class="tabCon" id="teaDiv3">资料管理</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>

			<jsp:include page="../modules/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
<script type="text/javascript">

	$(function() {
		var tabId = sessionStorage.getItem("tableId");
		var tabIndex = 0;
		switch(tabId){
			case "0":
				tabIndex = 0;
				break;
			case "stu1":
				tabIndex = 0;
				break;
			case "stu2":
				tabIndex = 1;
				break;
			case "stu3":
				tabIndex = 2;
				break;
			case "admin1":
				tabIndex = 0;
				break;
			case "admin2":
				tabIndex = 1;
				break;
			case "admin3":
				tabIndex = 2;
				break;
			case "admin4":
				tabIndex = 3;
				break;
			case "admin5":
				tabIndex = 4;
				break;
			case "admin6":
				tabIndex = 5;
				break;
			case "tea1":
				tabIndex = 0;
				break;
			case "tea2":
				tabIndex = 1;
				break;
			case "tea3":
				tabIndex = 2;
				break;
		}
		$.Huitab("#tab_demo .tabBar span", "#tab_demo .tabCon", "current", "click", tabIndex);
	});

	jQuery.Huitab = function(tabBar, tabCon, class_name, tabEvent, i) {
		var $tab_menu = $(tabBar);
		// 初始化操作
		$tab_menu.removeClass(class_name);
		$(tabBar).eq(i).addClass(class_name);
		$(tabCon).hide();
		$(tabCon).eq(i).show();

		$tab_menu.bind(tabEvent, function() {
			$tab_menu.removeClass(class_name);
			$(this).addClass(class_name);
			var index = $tab_menu.index(this);
			$(tabCon).hide();
			$(tabCon).eq(index).show()
		})
	}
	
	/* $(function(){
		var tab = $(".selectedTab");
		var id = tab.attr("id");
		queryData(id);
	}) */
	
	$("span").on("click",function(){
		var id = $(this).attr("id");
		sessionStorage.setItem("tableId",id);
		//queryData(id);
	})
	
	
	/* function queryData(id){
		if(id == "admin1"){
			//callBackStu(1);
			pageHelper(callBackStu);
		}
		if(id == "admin2"){
			//callBackTea(1);
			pageHelper(callBackTea);
		}
		if(id == "admin3"){
			
		}
		if(id == "admin4"){
			
		}
		if(id == "admin5"){
			
		}
	} */
	
</script>
</html>
