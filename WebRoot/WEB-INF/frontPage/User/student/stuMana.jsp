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
<style type="text/css">

.col1{
	text-align:center;
}

</style>
</head>
<body>
	<div class="panel-header">
		<button class="btn btn-primary radius" >增加</button>
		<button class="btn btn-primary radius" >导入</button>
	</div>
	<table class="table table-border table-bordered table-striped mt-20">
		<thead>
			<tr>
				<th class="col1">ID</th>
				<th class="col1">姓名</th>
				<th class="col1">性别</th>
				<th class="col1">学号</th>
				<th class="col1">操作</th>
			</tr>
		</thead>
		<tbody id="stuContainer">
		</tbody>
	</table>
	<jsp:include page="../../modules/pager.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(function() {
		pageHelper(callback);
	})
	
	function callback(num){
		$.ajax({
			url : "${pageContext.request.contextPath}/user_findUserByType.action",
			data : {
				"pageNum" : num,
				"type":1
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				//alert(1);
				var container = $("#stuContainer");
				container.html("");
				$(data.users).each(function(idx, obj) {
					var item = generateItem(obj);
					container.append(item);
				})
			}
		})
	}

	function generateItem(obj) {
		var tr=$("<tr></tr>");
		var td1=$("<td style='text-align:center;'></td>");
		td1.html(obj.id);
		tr.append(td1);
		var td2=$("<td style='text-align:center;'></td>");
		td2.html(obj.name);
		tr.append(td2);
		var td3=$("<td style='text-align:center;'></td>");
		td3.html(obj.gender == 0 ? "男" : "女");
		tr.append(td3);
		var td4=$("<td style='text-align:center;'></td>");
		td4.html(obj.userCode);
		tr.append(td4);
		var td5=$("<td style='text-align:center;width:30%;'></td>");
		
		var btn1 = createBtn("修改密码","alert1()","btn btn-primary radius");
		td5.append(btn1);
		var btn2 = createBtn("禁用用户","alert2()","btn btn-danger radius");
		td5.append(btn2);
		tr.append(td5);
		return tr;
	}

	function createBtn(value,method,sty){
		var btn = $("<button style='margin-right:25px;'></button>");
		btn.attr("class",sty);
		btn.html(value);
		btn.attr("onclick",method);
		return btn;
	}
	
	function alert1(){
		alert(1);
	}
	
	function alert2(){
		alert(2);
	}
	
	
	$(function() {
		$.Huitab("#tab_demo .tabBar span", "#tab_demo .tabCon", "current", "click", "0");
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
</script>
</html>
