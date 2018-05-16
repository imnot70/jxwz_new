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
		<button onclick="addTeacher()" class="btn btn-primary radius" >增加</button>
		<!-- <button class="btn btn-primary radius" >导入</button> -->
	</div>
	<table class="table table-border table-bordered table-striped mt-20">
		<thead>
			<tr>
				<th class="col1">ID</th>
				<th class="col1">姓名</th>
				<th class="col1">编别</th>
				<th class="col1">学号</th>
				<th class="col1">操作</th>
			</tr>
		</thead>
		<tbody id="teaContainer">
		</tbody>
	</table>
	
	<div id="modal-demo-add-tea" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"> 
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align: center;">新增教师</h3>
				</div>
				<div class="modal-body" style="min-height: 300px;">
					<div class="containBox">
						<div class="wap-container">
							<div class="row cl">
								<label class="form-label col-xs-3">姓名：</label>
								<div class="formControls col-xs-8">
									<input class="user-prop input-text valid" placeholder="点击输入" id="tea-userName">
								</div>
							</div>
							<br />
							<div class="row clearfix">
								<label class="form-label col-xs-3">性别：</label>
								<div class="col-xs-8">
									<span class="select-box"> 
									<select id="tea-gender" name="tea-gender" class="user-prop select">
										<option value="">点击选择</option>
										<option value="1">男</option>
										<option value="0">女</option>
									</select>
									</span>
								</div>
							</div>
							<br />
							<div class="row cl">
								<label class="form-label col-xs-3">编号：</label>
								<div class="formControls col-xs-8">
									<input onkeyup="checkCodeUniqueTea(2)" id="tea-userCode" class="user-prop input-text valid" placeholder="点击输入" name="user.userCode">
								</div>
							</div>
							<div id="codeCheckTea" style="text-align: center; color: red; display: none;">此编号已存在</div>
							<br /> 
							<input type="hidden" id="tea-userType" value="2">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="submitBtnTea" class="btn btn-primary" type="submit">确定</button>
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="modal-demo-pwd-tea" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align: center;">修改密码</h3>
				</div>
				<div class="modal-body" style="min-height: 300px;">
					<div class="containBox">
						<div class="wap-container">
							<div class="row cl">
								<label class="form-label col-xs-3">姓名：</label>
								<div class="formControls col-xs-8">
									<input readonly="readonly" id="tea-pwd-userName" class="user-prop input-text valid" value="">
								</div>
							</div>
							<br />
							<div class="row cl">
								<label class="form-label col-xs-3">密码：</label>
								<div class="formControls col-xs-8">
									<input id="user-userPwdTea" id="tea-pwd-userPwd" class="user-prop input-text valid" >
								</div>
							</div>
							<br /> 
							<input type="hidden" id="tea-userId" value="">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="submitPwdTea" class="btn btn-primary">确定</button>
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<input id="totalCountTea" value="${totalCountTea }" type="hidden"/>
	<fieldset id="pagerTea" class="layui-elem-field layui-field-title" style="margin-top: 30px;text-align:center;"> </fieldset>
</body>
<script type="text/javascript">

	var pageNumTea = 0;

	$(function() {
		pageHelperTea(callBackTea);
	})
	
	var callBackTea = function(num){
		pageNumTea = num;
		$.ajax({
			url : "${pageContext.request.contextPath}/user_findUserByType.action",
			data : {
				"pageNum" : num,
				"type":2,
				"pageSize":10
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				var container = $("#teaContainer");
				container.html("");
				$(data.users).each(function(idx, obj) {
					var item = generateItemTea(obj);
					container.append(item);
				})
			}
		})
	}
	
	function generateItemTea(obj){
		var tr = $("<tr></tr>");
		var td1 = $("<td style='text-align:center;'></td>");
		td1.html(obj.id);
		tr.append(td1);
		var td2 = $("<td style='text-align:center;'></td>");
		td2.html(obj.name);
		tr.append(td2);
		var td3 = $("<td style='text-align:center;'></td>");
		td3.html(obj.gender == 0 ? "男" : "女");
		tr.append(td3);
		var td4 = $("<td style='text-align:center;'></td>");
		td4.html(obj.userCode);
		tr.append(td4);
		var td5 = $("<td style='text-align:center;width:30%;'></td>");

		var btn1 = createBtn("修改密码", "modifyPwdTea(this)", "btn btn-primary radius");
		td5.append(btn1);
		var btn2 = createBtn("禁用用户", "delTea(this)", "btn btn-danger radius");
		td5.append(btn2);
		tr.append(td5);
		return tr;
	}
	
	function addTeacher(){
		$("#modal-demo-add-tea").modal("show");
	}
	
	$("#submitBtnTea").bind("click",function(){
		//alert("submitBtnTea");
		var userName = $("#tea-userName").val();
		var gender = $("#tea-gender").val();
		var code = $("#tea-userCode").val();
		var type = $("#tea-userType").val();
		$.ajax({
			url:"${pageContext.request.contextPath}/user_saveUser.action",
			type:"post",
			data:{
				"user.name":userName,
				"user.gender":gender,
				"user.userType":type,
				"user.userCode":code
			},
			dataType:"json",
			success:function(data){
				if(data.success){
					addAlert("新增成功");
					$("#modal-demo-add-tea").modal("hide");
					$("#tea-userName").val("");
					$("#tea-gender").val("");
					$("#tea-userCode").val("");
					callBackTea(pageNumTea);
				}
			}
		})
	})
	
	// 检测编号唯一性
	function checkCodeUniqueTea(type) {
		var code = $("#tea-userCode").val();
		$.ajax({
			url : "${pageContext.request.contextPath}/user_checkCodeUnique.action",
			type : "post",
			data : {
				"code" : code,
				"type" : type
			},
			dataType : "json",
			success : function(data) {
				if (!data.unique) {
					$("#codeCheckTea") .attr("style", "text-align:center;color:red;display:block;");
					$("#submitBtnTea").addClass("disabled");
				} else {
					$("#codeCheckTea") .attr("style", "text-align:center;color:red;display:none;");
					$("#submitBtnTea").removeClass("disabled");
				}
			}
		})
	}
	
	var pageHelperTea = function(callback){
		layui.use('laypage', function() {
			var laypage = layui.laypage;
			var totalCount = $("#totalCountTea").val();
			
			laypage.render({
				elem : 'pagerTea',
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
	
	function modifyPwdTea(obj){
		var obj = $(obj);
		var id = obj.parent().parent().children().eq(0).html();
		$.ajax({
			url:"${pageContext.request.contextPath}/user_findUserById.action",
			type:"post",
			data:{
				"userId":id
			},
			dataType:"json",
			success:function(data){
				$("#tea-pwd-userName").val(data.user.name);
				$("#tea-userId").val(data.user.id);
				$("#modal-demo-pwd-tea").modal("show");
			}
		})
	}
	
	$("#submitPwdTea").bind("click",function(){
		var pwd = $("#user-userPwdTea").val();
		var id = $("#tea-userId").val();
		if(pwd == null || "" == pwd || typeof(pwd) == "undefined"){
			alert("请填写密码");
			return;
		}
		$.ajax({
			url:"${pageContext.request.contextPath}/user_modifyPwd.action",
			type:"post",
			data:{
				"user.id":id,
				"user.password":pwd
			},
			dataType:"json",
			success:function(data){
				modalalertTea("修改成功");
			}
		})		
	})
	
	// 修改密码成功提示
	function modalalertTea(msg){
		$.Huimodalalert(msg,1000);
		$("#modal-demo-pwd-tea").modal("hide");
	}
	
	
	function delTea(obj){
		var obj = $(obj);
		var id = obj.parent().parent().children().eq(0).html();
		var res = confirm("确定要删除此用户吗？");
		if(res){
			$.ajax({
				url:"${pageContext.request.contextPath}/user_delUser.action",
				type:"post",
				data:{
					"userId":id
				},
				dataType:"json",
				success:function(data){
					if(data.success){
						modalalertTea("删除成功");
						callBackTea(pageNumTea);
						// loadData();
					}else{
						modalalertTea("删除失败");
					}
				}
			})
		}
	}
	

</script>
</html>
