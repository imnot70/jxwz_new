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
		<button class="btn btn-primary radius size-S">上传</button>
	</div>
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
		<tbody id="incorContainer">
		</tbody>
	</table>
	
	<div id="tea-add-doc" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align: center;">上传教案</h3>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3">选择文件：</label>
					<div class="formControls col-xs-8">
						<span class="btn-upload form-group">
							<input class="input-text upload-url" type="text" name="myfile"  readonly="readonly" style="width:200px">
							<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont"></i> 浏览文件</a>
							<input id="knowFileInput" type="file" name="myfile" class="input-file" accept=".pdf">
						</span>
					</div>
				</div>
				<div class="modal-footer">
					<button id="submitBtn" class="btn btn-primary" >确定</button>
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
			url : "${pageContext.request.contextPath}/tea_getIncors.action",
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

	}

	function delQue(id) {

	}
</script>
</html>
