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
	<!-- <div class="panel-header">
		<button class="btn btn-primary radius">增加</button>
	</div> -->
	<table class="table table-border table-bordered table-striped mt-20">
		<thead>
			<tr>
				<th class="col1">ID</th>
				<th class="col1">问题内容</th>
				<th class="col1">答案</th>
				<th class="col1">操作</th>
			</tr>
		</thead>
		<tbody id="incorContainer">
		</tbody>
	</table>

	<div id="modal-demo-preview-incor" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align:center;">错题详情</h3>
				</div>
				<div class="modal-body" style="min-height:500px;">
					<div class="containBox">
						<div class="wap-container">
							<br/>
							<div style="margin-left:20px;" class="formControls" id="incor_content">
							</div>
							<br/>
							<div style="margin-left:20px;" id="incor_answser">
							</div>
							<br/>
							<div style="margin-left:20px;" id="incor_answers">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>



	<input id="totalCountIncor" value="${totalCountIncor }" type="hidden" />
	<fieldset id="pagerIncor" class="layui-elem-field layui-field-title" style="margin-top: 30px; text-align: center;"></fieldset>
</body>
<script type="text/javascript">
	var pageNumIncor = 0;
	$(function() {
		pageHelperIncor(callbackIncor);
	})

	function callbackIncor(num) {
		pageNumIncor = num;
		$.ajax({
			url : "${pageContext.request.contextPath}/stu_getIncors.action",
			data : {
				"pageNum" : num,
				"pageSize" : 10
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				var container = $("#incorContainer");
				container.html("");
				$(data.ques).each(function(idx, obj) {
					var item = generateItemIncor(obj);
					container.append(item);
				})
			}
		})
	}

	var pageHelperIncor = function(callback) {
		layui.use('laypage', function() {
			var laypage = layui.laypage;
			var totalCount = $("#totalCountIncor").val();

			laypage.render({
				elem : 'pagerIncor',
				count : totalCount,
				limit : 10,
				curr : location.hash.replace('#!pageNum=', ''),
				hash : "pageNum",
				jump : function(obj) {
					//console.log(obj);
					callback(obj.curr);
				}
			});
		});
	}

	function generateItemIncor(obj) {
		var tr = $("<tr></tr>");
		var td1 = $("<td style='text-align:center;'></td>");
		td1.html(obj.id);
		tr.append(td1);
		var td3 = $("<td style='text-align:center;'></td>");
		td3.html(obj.content);
		tr.append(td3);
		var td4 = $("<td style='text-align:center;'></td>");
		td4.html(obj.answerCode);
		tr.append(td4);
		var td5 = $("<td style='text-align:center;width:30%;'></td>");

		var btn1 = createBtn("详情", "checkQue(" + obj.id + ")", "btn btn-primary radius");
		td5.append(btn1);
		var btn2 = createBtn("删除", "delQue(" + obj.id + ")", "btn btn-danger radius");
		td5.append(btn2);
		tr.append(td5);
		return tr;
	}

	function createBtn(value, method, sty) {
		var btn = $("<button style='margin-right:25px;'></button>");
		btn.attr("class", sty);
		btn.html(value);
		btn.attr("onclick", method);
		return btn;
	}

	function checkQue(id) {
		$.ajax({
			url:"${pageContext.request.contextPath}/stu_inocrrectDetail.action",
			type:"post",
			data:{
				"queId":id
			},
			dataType:"json",
			success:function(data){
				$("#incor_content").html(data.data.content);
				var remark = "答案："+data.data.answerCode+",分析："+ data.data.remark;
				$("#incor_answser").html(remark);
				
				var container = $("#incor_answers");
				var str = "";
				$(data.data.options).each(function(idx,obj){
					str += '<div style="display:inline-block;float:left;">'+obj.answerCode+'.'+obj.content+'</div><br/>';
				})
				container.html(str);
				
				$("#modal-demo-preview-incor").modal("show");
			}
		})
	}

	function delQue(id) {
		var res = confirm("确定要删除吗？");
		if(!res){
			return ;
		}
		$.ajax({
			url:"${pageContext.request.contextPath}/stu_delIncor.action",
			type:"post",
			data:{
				"queId":id
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
