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
		<button onclick="addPost()" class="btn btn-primary radius" >发帖</button>
	</div>
	<table class="table table-border table-bordered table-striped mt-20">
		<thead>
			<tr>
				<th class="col1">ID</th>
				<th class="col1">时间</th>
				<th class="col1">标题</th>
				<th class="col1">状态</th>
				<th class="col1">操作</th>
			</tr>
		</thead>
		<tbody id="PostStuContainer">
		</tbody>
	</table>
	
	<input id="totalCountPostStu" value="${totalCountPostStu }" type="hidden"/>
	<fieldset id="pagerPostStu" class="layui-elem-field layui-field-title" style="margin-top: 30px;text-align:center;"> </fieldset>
</body>
<script type="text/javascript">
	var pageNumPostStu = 0;
	$(function() {
		pageHelperPostStu(callbackPostStu);
	})
	
	function callbackPostStu(num){
		pageNumPostStu = num;
		$.ajax({
			url : "${pageContext.request.contextPath}/stu_findWords.action",
			data : {
				"pageNum" : num,
				"pageSize":10
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				var container = $("#WordsContainer");
				container.html("");
				$(data.words).each(function(idx, obj) {
					var item = generateItemPostStu(obj);
					container.append(item);
				})
			}
		})
	}
	
	var pageHelperPostStu = function(callback){
		layui.use('laypage', function() {
			var laypage = layui.laypage;
			var totalCount = $("#totalCountWords").val();
			
			laypage.render({
				elem : 'pagerPostStu',
				count : totalCount,
				limit:10,
				curr: location.hash.replace('#!pageNum=', '') ,
			    hash: "pageNum" ,
				jump : function(obj) {
					callback(obj.curr);
				}
			});
		});
	}
	
	function generateItemPostStu(obj){
		var tr = $("<tr></tr>");
		var td1 = $("<td style='text-align:center;'></td>");
		td1.html(obj.id);
		tr.append(td1);
		var td2 = $("<td style='text-align:center;'></td>");
		td2.html(obj.createDateStr);
		tr.append(td2);
		var td3 = $("<td style='text-align:center;'></td>");
		td3.html(obj.title);
		tr.append(td3);
		var td4 = $("<td style='text-align:center;'></td>");
		td4.html(obj.state == 1 ? "已回复" : "未回复");
		tr.append(td4);
		var td5 = $("<td style='text-align:center;width:30%;'></td>");

		var btn2 = createBtn("查看", "check('"+obj.url+"')", "btn btn-primary radius");
		td5.append(btn2);
		tr.append(td5);
		return tr;
	}

	function addPost(){
		window.location.href="${pageContext.request.contextPath}/home_toDiscuz.action?fromCenter=true";
	}
	
</script>
</html>
