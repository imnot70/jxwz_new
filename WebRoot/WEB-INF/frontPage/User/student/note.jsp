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
	<table class="table table-border table-bordered table-striped mt-20">
		<thead>
			<tr>
				<th class="col1">ID</th>
				<th class="col1">章</th>
				<th class="col1">节</th>
				<th class="col1">标题</th>
				<th class="col1">操作</th>
			</tr>
		</thead>
		<tbody id="noteContainer">
		</tbody>
	</table>
	
	<div id="modal-demo-preview-note" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align:center;">添加笔记</h3>
				</div>
				<div class="modal-body" style="min-height:500px;">
					<div class="containBox">
						<div class="wap-container">
						<br/>
							<div class="formControls">
								<input id="note_preview_theme" class="input-text" placeholder="笔记题目">
							</div>
						</div>
						<br/>
						<div>
							<textarea id="note_preview_content" rows="" cols="" style="min-height:400px;" class="textarea" placeholder="这里是笔记内容哦~"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="addNoteFinish()">确定</button>
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<input id="totalCountNote" value="${totalCountNote }" type="hidden"/>
	<fieldset id="pagerNote" class="layui-elem-field layui-field-title" style="margin-top: 30px;text-align:center;"> </fieldset>
</body>
<script type="text/javascript">
	var pageNumNote = 0;
	$(function() {
		pageHelperNote(callbackNote);
	})
	
	function callbackNote(num){
		pageNumNote = num;
		$.ajax({
			url : "${pageContext.request.contextPath}/stu_findNotes.action",
			data : {
				"pageNum" : num,
				"pageSize":10
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				var container = $("#noteContainer");
				container.html("");
				$(data.notes).each(function(idx, obj) {
					var item = generateItemNote(obj);
					container.append(item);
				})
			}
		})
	}
	
	var pageHelperNote = function(callback){
		layui.use('laypage', function() {
			var laypage = layui.laypage;
			var totalCount = $("#totalCountNote").val();
			
			laypage.render({
				elem : 'pagerNote',
				count : totalCount,
				limit:10,
				curr: location.hash.replace('#!pageNum=', '') ,
			    hash: "pageNum" ,
				jump : function(obj) {
					//console.log(obj);
					callback(obj.curr);
				}
			});
		});
	}
	
	function generateItemNote(obj){
		var tr = $("<tr></tr>");
		var td1 = $("<td style='text-align:center;'></td>");
		td1.html(obj.id);
		tr.append(td1);
		var td2 = $("<td style='text-align:center;'></td>");
		td2.html(obj.section.chapter.title);
		tr.append(td2);
		var td3 = $("<td style='text-align:center;'></td>");
		td3.html(obj.section.title);
		tr.append(td3);
		var td4 = $("<td style='text-align:center;'></td>");
		td4.html(obj.title);
		tr.append(td4);
		var td5 = $("<td style='text-align:center;width:30%;'></td>");

		var btn2 = createBtn("预览", "checkNote('"+obj.id+"')", "btn btn-primary radius");
		td5.append(btn2);
		
		var btn1 = createBtn("删除", "delNote("+obj.id+")", "btn btn-danger radius");
		td5.append(btn1);
		tr.append(td5);
		return tr;
	}
	
	function delKnow(id){
		var res = confirm("确定要删除吗？");
		if(!res){
			return false;
		}
		
		$.ajax({
			url:"${pageContext.request.contextPath}/doc_delKnow.action",
			type:"post",
			data:{
				"knowId":id
			},
			dataType:"json",
			success:function(data){
				if(data.success){
					alert("操作成功");
					window.location.reload();
				}else{
					alert("操作失败");
				}
			}
		})
	}
	
	function checkNote(id){
		$.ajax({
			url:"${pageContext.request.contextPath}/stu_noteDetail.action",
			type:"post",
			data:{
				"noteId":id
			},
			dataType:"json",
			success:function(data){
				$("#note_preview_theme").val(data.note.title);
				$("#note_preview_content").val(data.note.content);
				$("#modal-demo-preview-note").modal("show");
			}
		})
		
	}
	
	function delNote(id){
		var res = confirm("确定要删除吗？");
		if(!res){
			return;
		}
		
		$.ajax({
			url:"${pageContext.request.contextPath}/stu_delNote.action",
			type:"post",
			data:{
				"noteId":id
			},
			dataType:"json",
			success:function(data){
				if(data.success){
					alert("删除成功");
					window.location.reload();
				}else{
					alert("删除失败");
				}
			}
		})
	}

</script>
</html>
