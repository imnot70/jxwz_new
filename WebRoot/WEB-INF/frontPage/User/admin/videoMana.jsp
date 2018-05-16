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
		<button onclick="addVideo()" class="btn btn-primary radius" >上传</button>
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
		<tbody id="videoContainer">
		</tbody>
	</table>
	
	<div id="modal-demo-add-Video" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align: center;">上传知识点</h3>
				</div>
				<div class="modal-body" style="min-height: 300px;">
					<div class="containBox">
						<div class="wap-container">
							<div class="row cl">
								<label class="form-label col-xs-3">选择章：</label>
								<div class="col-xs-8">
									<span class="select-box"> 
									<select id="video-add-cha" class="user-prop select" onchange="findVideoSections()">
									</select>
									</span>
								</div>
							</div>
							<br/>

							<div class="row clearfix">
								<label class="form-label col-xs-3">选择节：</label>
								<div class="col-xs-8">
									<span class="select-box"> 
									<select id="video-add-sec" class="user-prop select">
										<option>点击选择</option>
									</select>
									</span>
								</div>
							</div>
							<br/>
							<div class="row cl">
								<label class="form-label col-xs-3">选择文件：</label>
								<div class="formControls col-xs-8">
									<span class="btn-upload form-group">
										<input class="input-text upload-url" type="text" name="uploadfile-2" id="knowFileInput" readonly="" style="width:200px">
										<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont"></i> 浏览文件</a>
										<!-- <input id="knowFileInput" type="file" name="know" class="input-file"> -->
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="submitBtn" class="btn btn-primary" >确定</button>
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<input id="totalCountVideo" value="${totalCountVideo }" type="hidden"/>
	<fieldset id="pagerVideo" class="layui-elem-field layui-field-title" style="margin-top: 30px;text-align:center;"> </fieldset>
</body>
<script type="text/javascript">
	
	var pageNumVideo = 0;

	$(function() {
		pageHelperVideo(callbackVideo);
	})
	
	function callbackVideo(num){
		pageNumVideo = num;
		$.ajax({
			url : "${pageContext.request.contextPath}/doc_findVideos.action",
			data : {
				"pageNum" : num,
				"pageSize":10
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				//alert(1);
				var container = $("#videoContainer");
				container.html("");
				$(data.videos).each(function(idx, obj) {
					var item = generateItemVideo(obj);
					container.append(item);
				})
			}
		})
	}

	var pageHelperVideo = function(callback){
		layui.use('laypage', function() {
			var laypage = layui.laypage;
			var totalCount = $("#totalCountVideo").val();
			
			laypage.render({
				elem : 'pagerVideo',
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
	function generateItemVideo(obj){
		var tr = $("<tr></tr>");
		var td1 = $("<td style='text-align:center;'></td>");
		td1.html(obj.id);
		tr.append(td1);
		var td2 = $("<td style='text-align:center;'></td>");
		if(obj!= null && obj.section != null && obj.section.chapter != null){
			td2.html(obj.section.chapter.title);
		}
		tr.append(td2);
		var td3 = $("<td style='text-align:center;'></td>");
		if(obj!= null && obj.section != null){
			td3.html(obj.section.title);
		}
		tr.append(td3);
		var td4 = $("<td style='text-align:center;'></td>");
		td4.html(obj.name);
		tr.append(td4);
		var td5 = $("<td style='text-align:center;width:30%;'></td>");
		var btn1 = createBtn("删除", "delVideo(this)", "btn btn-danger radius");
		td5.append(btn1);
		tr.append(td5);
		return tr;
	}
	
	function addVideo(){
		var container = $("#video-add-cha");
		findChapters(container);	// 此函数在knowMana.jsp
		$("#modal-demo-add-Video").modal("show");
	}
	
	function findVideoSections(){
		var container = $("#video-add-sec");
		findSections(container); // 此函数在knowMana.jsp
	}
	
</script>
</html>
