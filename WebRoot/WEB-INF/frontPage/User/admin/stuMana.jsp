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
		<button class="btn btn-primary radius" onclick="showForm()">增加</button>
		<!-- <button class="btn btn-primary radius" onclick="importUser()">导入</button> -->
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

	<div id="modal-demo-add-stu" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align: center;">新增学生</h3>
				</div>
				<div class="modal-body" style="min-height: 300px;">
					<div class="containBox">
						<div class="wap-container">
							<div class="row cl">
								<label class="form-label col-xs-3">姓名：</label>
								<div class="formControls col-xs-8">
									<input class="user-prop input-text valid" placeholder="点击输入" id="stu-add-name">
								</div>
							</div>
							<br />

							<div class="row clearfix">
								<label class="form-label col-xs-3">性别：</label>
								<div class="col-xs-8">
									<span class="select-box"> 
									<select id="stu-add-gender" class="user-prop select">
											<option value="">点击选择</option>
											<option value="1">男</option>
											<option value="0">女</option>
									</select>
									</span>
								</div>
							</div>
							<br />
							<div class="row clearfix">
								<label class="form-label col-xs-3">教师：</label>
								<div class="col-xs-8">
									<span class="select-box"> 
									<select id="stu-add-teacher" class="user-prop select">
									</select>
									</span>
								</div>
							</div>
							<br />
							<div class="row cl">
								<label class="form-label col-xs-3">学号：</label>
								<div class="formControls col-xs-8">
									<input onkeyup="checkCodeUnique(1)" id="user-userCode"
										class="user-prop input-text valid" placeholder="点击输入"
										name="user.userCode">
								</div>
							</div>
							<div id="codeCheck" style="text-align: center; color: red; display: none;">此学号已存在</div>
							<br /> 
							<input type="hidden" id="stu-userType" name="user.userType" value="1">
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

	<div id="modal-demo-pwd-stu" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
									<input id="stu-userName" readonly="readonly" class="user-prop input-text valid" value="">
								</div>
							</div>
							<br />
							<div class="row cl">
								<label class="form-label col-xs-3">密码：</label>
								<div class="formControls col-xs-8">
									<input id="stu-userPwd" class="user-prop input-text valid" name="user.password">
								</div>
							</div>
							<br /> 
							<input type="hidden" id="stu-userId" name="user.id" value="">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="submitPwd" class="btn btn-primary" >确定</button>
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<%-- <jsp:include page="../../modules/pager.jsp"></jsp:include> --%>
	<input id="totalCountStu" value="${totalCountStu }" type="hidden"/>
	<fieldset id="pager" class="layui-elem-field layui-field-title" style="margin-top: 30px;text-align:center;"> </fieldset>
</body>
<script type="text/javascript">

	//存储当前页码
	var pageNumStu = 0;

	// 添加学生
	$("#submitBtn").bind("click",function(){
		var name = $("#stu-add-name").val();
		var gender = $("#stu-add-gender").val();
		var teacherId = $("#stu-add-teacher").val();
		var code = $("#user-userCode").val();
		var type=$("#stu-userType").val();
		
		if(name == null|| ""== name || typeof(name) == "undefined"){
			alert("请填写姓名");
			return ;
		}
		if(gender == null|| ""== gender || typeof(gender) == "undefined"){
			alert("请选择性别");
			return ;
		}
		if(teacherId == null|| ""== teacherId || typeof(teacherId) == "undefined"){
			alert("请选择老师");
			return;
		}
		if(code == null|| ""== code || typeof(code) == "undefined"){
			alert("请填写编号");
			return;
		}
		
		$.ajax({
			url:"${pageContext.request.contextPath}/user_saveUser.action",
			type:"post",
			data:{
				"user.name":name,
				"user.gender":gender,
				"user.userType":type,
				"user.userCode":code,
				"teacherId":teacherId
			},
			dataType:"json",
			success:function(data){
				if(data.success){
					addAlert("新增成功");
					callBackStu(pageNumStu);
					// loadData();
				}
			}
		})
	})
	
	// 添加学生成功后的提示
	function addAlert(msg){
		$.Huimodalalert(msg,1000);
		$("#modal-demo-add-stu").modal("hide");
		$("#stu-add-name").val("");
		$("#stu-add-gender").val("");
		$("#stu-add-teacher").val("");
		$("#user-userCode").val("");
	}

	// 显示添加学生窗口
	function showForm() {
		$.ajax({
			url : "${pageContext.request.contextPath}/user_findAllTeacher.action",
			data : {},
			type : "post",
			dataType : "json",
			success : function(data) {
				var container = $("#stu-add-teacher");
				container.html("<option value=''>点击选择</option>")
				$(data.teachers).each(function(idx, obj) {
					var op = $("<option></option>");
					op.val(obj.id);
					op.html(obj.name);
					container.append(op);

				})
				$("#modal-demo-add-stu").modal("show");
			}
		})
	}

	// 检测学号唯一性
	function checkCodeUnique(type) {
		var code = $("#user-userCode").val();
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
					$("#codeCheck") .attr("style", "text-align:center;color:red;display:block;");
					$("#submitBtn").addClass("disabled");
				} else {
					$("#codeCheck") .attr("style", "text-align:center;color:red;display:none;");
					$("#submitBtn").removeClass("disabled");
				}
			}
		})
	}

	// 导入学生
	function importUser() {
		
	}

	// 页面加载后查询数据
	$(function() {
		pageHelper(callBackStu);
	})
	
	// 分页功能回调函数
	var callBackStu = function(num) {
		pageNumStu = num;
		$.ajax({
			url : "${pageContext.request.contextPath}/user_findUserByType.action",
			data : {
				"pageNum" : num,
				"type" : 1,
				"pageSize":10
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				//alert(1);
				var container = $("#stuContainer");
				container.html("");
				$(data.users).each(function(idx, obj) {
					var item = generateItemStu(obj);
					container.append(item);
				})
			}
		})
	}

	// 生成学生列表
	function generateItemStu(obj) {
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

		var btn1 = createBtn("修改密码", "modifyPwdStu(this)", "btn btn-primary radius");
		td5.append(btn1);
		var btn2 = createBtn("禁用用户", "delUser(this)", "btn btn-danger radius");
		td5.append(btn2);
		tr.append(td5);
		return tr;
	}

	// 生成操作按钮
	function createBtn(value, method, sty) {
		var btn = $("<button style='margin-right:25px;'></button>");
		btn.attr("class", sty);
		btn.html(value);
		btn.attr("onclick", method);
		return btn;
	}
	
	// 删除学生
	function delUser(obj){
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
						modalalertdemo("删除成功");
						callBackStu(pageNumStu);
						// loadData();
					}else{
						modalalertdemo("删除失败");
					}
				}
			})
		}
	}
	
	// 显示修改密码窗口
	function modifyPwdStu(obj){
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
				$("#stu-userName").val(data.user.name);
				$("#stu-userId").val(data.user.id);
				$("#modal-demo-pwd-stu").modal("show");
			}
		})
	}
	
	// 提交修改密码
	$("#submitPwd").bind("click",function(){
		var pwd = $("#stu-userPwd").val();
		var id = $("#stu-userId").val();
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
				modalalertdemo("修改成功");
			}
		})		
	})
	
	// 修改密码成功提示
	function modalalertdemo(msg){
		$.Huimodalalert(msg,1000);
		$("#modal-demo-pwd-stu").modal("hide");
	}

	var pageHelper = function(callback){
		layui.use('laypage', function() {
			var laypage = layui.laypage;
			var totalCount = $("#totalCountStu").val();
			
			laypage.render({
				elem : 'pager',
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
	
</script>
</html>
