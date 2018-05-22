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
		<button onclick="addQueTea()" class="btn btn-primary radius size-S">添加</button>
	</div>
	<table class="table table-border table-bordered table-striped mt-20">
		<thead>
			<tr>
				<th class="col1">ID</th>
				<th class="col1">问题内容</th>
				<th class="col1">答案</th>
				<th class="col1">操作</th>
			</tr>
		</thead>
		<tbody id="queContainer">
		</tbody>
	</table>
	
	<div id="tea-add-que" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align: center;">添加测试题</h3>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3">选择章：</label>
					<div class="col-xs-8">
						<span class="select-box"> 
						<select id="que-add-cha" class="user-prop select" onchange="finQueSections()">
						</select>
						</span>
					</div>
				</div>
				<br/>

				<div class="row clearfix">
					<label class="form-label col-xs-3">选择节：</label>
					<div class="col-xs-8">
						<span class="select-box"> 
						<select id="que-add-sec" class="user-prop select">
							<option>点击选择</option>
						</select>
						</span>
					</div>
				</div>
				<br/>
				<div class="row cl">
					<label class="form-label col-xs-3">问题内容：</label>
					<div class="formControls col-xs-8">
						<textarea id="que-content" rows="" cols="" style="min-height:100px;" class="textarea" placeholder=""></textarea>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3">选项A：</label>
					<div class="formControls col-xs-8">
						<input class="que-op input-text valid " placeholder="点击输入"/>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3">选项B：</label>
					<div class="formControls col-xs-8">
						<input class="que-op input-text valid" placeholder="点击输入"/>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3">选项C：</label>
					<div class="formControls col-xs-8">
						<input class="que-op input-text valid" placeholder="点击输入"/>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3">选项D：</label>
					<div class="formControls col-xs-8">
						<input class="que-op input-text valid" placeholder="点击输入"/>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="submitQue()">确定</button>
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<input id="totalCountQue" value="${totalCountQue }" type="hidden"/>
	<fieldset id="pagerQue" class="layui-elem-field layui-field-title" style="margin-top: 30px;text-align:center;"> </fieldset>
</body>
<script type="text/javascript">
	function addQueTea(){
		findChapters($("#que-add-cha"));
		$("#tea-add-que").modal("show");
	}
	function submitQue(){
		var secId = $("#que-add-sec").val();
		alert(secId);
	}
	
	function findChapters(container){
		$.ajax({
			url:"${pageContext.request.contextPath}/cha_findAllChapters.action",
			type:"post",
			dataType:"json",
			success:function(data){
				container.html("");
				var defaultOp = $("<option value='0'>点击选择</option>")
				container.append(defaultOp);
				fillContainer(container,data.chapters);
			}
		})
	}
	
	function finQueSections(){
		var container = $("#que-add-sec");
		findSections(container);
	}

	function fillContainer(container,list){
		if(list != null && list.length != 0){
			$(list).each(function(idx,obj){
				var op = generateOp(obj);
				container.append(op);
			})
		}
	}
	
	function generateOp(obj){
		var op = $("<option></option>");
		
		op.val(obj.id);
		op.html(obj.title);
		return op;
	}
	
	var pageNumNote = 0;
	$(function() {
		callbackNote(callbackNote);
	})
	
	function callbackNote(num){
		pageNumNote = num;
		$.ajax({
			url : "${pageContext.request.contextPath}/tea_findKnows.action",
			data : {
				"pageNum" : num,
				"pageSize":10
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				var container = $("#knowContainer");
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

		var btn2 = createBtn("预览", "check('"+obj.url+"')", "btn btn-primary radius");
		td5.append(btn2);
		
		var btn1 = createBtn("删除", "delKnow("+obj.id+")", "btn btn-danger radius");
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
			url:"${pageContext.request.contextPath}/tea_delKnow.action",
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

</script>
</html>
