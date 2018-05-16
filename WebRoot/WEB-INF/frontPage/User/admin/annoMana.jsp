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
.col1 {
	text-align: center;
}
</style>
</head>
<body>
	<div class="panel-header">
		<button onclick="showAnno(1,null)" class="btn btn-primary radius">增加</button>
	</div>
	<table class="table table-border table-bordered table-striped mt-20">
		<thead>
			<tr>
				<th class="col1">ID</th>
				<th class="col1">标题</th>
				<th class="col1">操作</th>
			</tr>
		</thead>
		<tbody id="annoContainer">
		</tbody>
	</table>

	<div id="modal-demo-add-anno" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 id="win-title-anno" class="modal-title" style="text-align: center;"></h3>
				</div>
				<div class="modal-body" style="min-height: 300px;">
					<div class="containBox">
						<div class="wap-container">
							<br />
							<div class="formControls">
								<input id="anno_title" class="input-text" placeholder="公告标题">
							</div>
						</div>
						<br />
						<div>
							<textarea id="anno_content" rows="" cols="" style="min-height: 400px;" class="textarea" placeholder="公告内容"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="addAnnoBtn" class="btn btn-primary" type="submit">确定</button>
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<input id="totalCountAnno" value="${totalCountAnno }" type="hidden"/>
	<fieldset id="pagerAnno" class="layui-elem-field layui-field-title" style="margin-top: 30px;text-align:center;"></fieldset>
</body>
<script type="text/javascript">

	var pageNumAnno = 0;
	$(function() {
		pageHelperAnno(callbackAnno);
	})

	function callbackAnno(num) {
		pageNumAnno = num;
		$.ajax({
			url : "${pageContext.request.contextPath}/anno_getAnnos.action",
			data : {
				"pageNum" : num,
				"pageSize":10
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				var container = $("#annoContainer");
				container.html("");
				$(data.annos).each(function(idx, obj) {
					var item = generateItemAnno(obj);
					container.append(item);
				})
			}
		})
	}
	
	var pageHelperAnno = function(callback){
		layui.use('laypage', function() {
			var laypage = layui.laypage;
			var totalCount = $("#totalCountAnno").val();
			
			laypage.render({
				elem : 'pagerAnno',
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
	
	function generateItemAnno(obj){
		var tr = $("<tr></tr>");
		var td1 = $("<td style='text-align:center;'></td>");
		td1.html(obj.id);
		tr.append(td1);
		var td2 = $("<td style='text-align:center;'></td>");
		td2.html(obj.title);
		tr.append(td2);
		var td5 = $("<td style='text-align:center;width:30%;'></td>");

		var btn1 = createBtn("查看", "showAnno(2,"+obj.id+")", "btn btn-primary radius");
		td5.append(btn1);
		var btn2 = createBtn("删除", "delAnno("+obj.id+")", "btn btn-danger radius");
		td5.append(btn2);
		tr.append(td5);
		return tr;
	}
	
	function showAnno(type,id){
		if(type == 2){
			$("#anno_content").attr("readonly","readonly");
			$("#anno_title").attr("readonly","readonly");
			$("#addAnnoBtn").css("display","none");
			$("#win-title-anno").html("公告内容");
			$.ajax({
				url:"${pageContext.request.contextPath}/anno_annoDetail.action",
				type:"post",
				data:{
					"annoId":id
				},
				dataType:"json",
				success:function(data){
					$("#anno_content").val(data.anno.content);
					$("#anno_title").val(data.anno.title);
					
					$("#modal-demo-add-anno").modal("show");
				}
			})
			
		}else{
			
			$("#anno_title").val("");
			$("#anno_content").val("");
			
			$("#anno_content").removeAttr("readonly");
			$("#addAnnoBtn").css("display","inline-block");
			$("#win-title-anno").html("发布公告");
			
			$("#modal-demo-add-anno").modal("show");
		}
		
	}
	
	$("#addAnnoBtn").on("click",function(){
		var title = $("#anno_title").val();
		var content = $("#anno_content").val();
		if(title == null || "" == title || typeof(title) == "undefined"){
			alert("请填写标题");
			return false;
		}
		
		if(content == null || "" == content || typeof(content) == "undefined"){
			alert("请填写内容");
			return false;
		}
		
		
		$.ajax({
			url:"${pageContext.request.contextPath}/anno_saveAnno.action",
			type:"post",
			data:{
				"anno.title":title,
				"anno.content":content
			},
			dataType:"json",
			success:function(data){
				if(data.success){
					alert("发布成功");
					window.location.reload();
				}
			}
		})
		
	})
	
	function delAnno(id){
		$.ajax({
			url:"${pageContext.request.contextPath}/anno_delAnno.action",
			type:"post",
			data:{
				"annoId":id
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
