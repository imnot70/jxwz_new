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
		<button onclick="addWords()" class="btn btn-primary radius" >给老师留言</button>
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
		<tbody id="WordsContainer">
		</tbody>
	</table>
	
	<div id="modal-demo-add-word" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align:center;">添加留言</h3>
				</div>
				<div class="modal-body" style="min-height:500px;">
					<div class="containBox">
						<div class="wap-container">
						<br/>
							<div class="formControls">
								<input id="words_title" class="input-text" placeholder="留言标题">
							</div>
						</div>
						<br/>
						<div>
							<textarea id="words_content" rows="" cols="" style="min-height:400px;" class="textarea" placeholder="想对老师说什么呢？"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="addWordsFinish()">确定</button>
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="modal-demo-preview-word" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align:center;">查看回复</h3>
				</div>
				<div class="modal-body" style="min-height:500px;">
					<div class="containBox">
						<div class="wap-container">
						<br/>
							<div class="formControls">
								<input id="words_preview_title" class="input-text" placeholder="">
							</div>
						</div>
						<br/>
						<div>
							<textarea id="words_preview_content" rows="" cols="" style="min-height:200px;" class="textarea" placeholder=""></textarea>
						</div>
						<br/>
						<div>
							<textarea id="words_preview_reply" rows="" cols="" style="min-height:200px;" class="textarea" placeholder=""></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<input id="totalCountWords" value="${totalCountWords }" type="hidden"/>
	<fieldset id="pagerWords" class="layui-elem-field layui-field-title" style="margin-top: 30px;text-align:center;"> </fieldset>
</body>
<script type="text/javascript">
	var pageNumWords = 0;
	$(function() {
		pageHelperWords(callbackWords);
	})
	
	function callbackWords(num){
		pageNumWords = num;
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
					var item = generateItemWords(obj);
					container.append(item);
				})
			}
		})
	}
	
	var pageHelperWords = function(callback){
		layui.use('laypage', function() {
			var laypage = layui.laypage;
			var totalCount = $("#totalCountWords").val();
			
			laypage.render({
				elem : 'pagerWords',
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
	
	function generateItemWords(obj){
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
		var btn2 = createBtn("查看", "check("+obj.id+")", "btn btn-primary radius");
		td5.append(btn2);
		tr.append(td5);
		return tr;
	}
	
	function addWords(){
		$("#modal-demo-add-word").modal("show");
	}
	
	function addWordsFinish(){
		var title = $("#words_title").val();
		var content = $("#words_content").val();
		$.ajax({
			url:"${pageContext.request.contextPath}/stu_addWords.action",
			type:"post",
			data:{
				"words.title":title,
				"words.content":content
			},
			dataType:"json",
			success:function(data){
				if(data.success){
					alert("添加成功");
					window.location.reload();
				}else{
					alert("添加失败");
				}
			}
		})
	}

	function check(id){
		$.ajax({
			url:"${pageContext.request.contextPath}/stu_wordsDetail.action",
			type:"post",
			data:{
				"wordsId":id
			},
			dataType:"json",
			success:function(data){
				$("#words_preview_title").val(data.words.title);
				$("#words_preview_content").val(data.words.content);
				if(data.wp!= null){
					$("#words_preview_reply").val(data.wp.content);
				}
				$("#modal-demo-preview-word").modal("show");
			}
		})
		
		
	}
	
</script>
</html>
