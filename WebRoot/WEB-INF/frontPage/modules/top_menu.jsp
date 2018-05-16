<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

</head>
<body>
	<div class="containBox-bg"></div>
	<header class="navbar-wrapper">
		<div class="navbar navbar-black navbar-fixed-top">
			<div class="container cl">
				<a class="logo navbar-logo hidden-xs" href="${pageContext.request.contextPath }/home_toHome.action">《编译原理》教程网站</a>
				<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs JS-nav-toggle" href="javascript:;">&#xe667;</a>
				<nav class="nav navbar-nav nav-collapse" role="navigation" id="Hui-navbar">
					<ul class="cl">
						<li id="know">
							<a href="${pageContext.request.contextPath }/home_toHome.action" target="_self">教学资料</a>
						</li>
						<li id="video">
							<a href="${pageContext.request.contextPath }/home_toVideoList.action" target="_self">教学视频</a>
						</li>
						<li id="test">
							<a href="${pageContext.request.contextPath }/home_toTest.action" target="_self">在线测试</a>
						</li>
						<li id="discuz">
							<a href="${pageContext.request.contextPath }/home_toDiscuz.action" target="_self">热烈讨论</a>
						</li>
						<c:if test="${user == null }">
							<li id="login_li" onclick="show()">
								<a href="#" >登录</a>
							</li>
						</c:if>
						<c:if test="${user != null }">
							<li id="login_li" id="center">
								&nbsp;&nbsp;&nbsp;&nbsp;欢迎，${userName }
								<a href="${pageContext.request.contextPath}/user_center.action">个人中心</a>
								<a href="#" onclick="logout()">退出</a>
							</li>
						</c:if>
					</ul>
				</nav>
				<nav class="navbar-userbar hidden-xs"></nav>
			</div>
		</div>
	</header>
</body>

<div id="modal-demo" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content radius">
			<div class="modal-header">
				<h3 class="modal-title">登陆</h3>
			</div>
			<div class="modal-body">
				<div class="row cl">
					<label class="form-label col-xs-3">编&nbsp;&nbsp;&nbsp;&nbsp;号：</label>
					<div class="formControls col-xs-8">
						<input type="text" class="input-text" placeholder="点击输入" id="userCode">
					</div>
				</div>
				<br/>
				<div class="row cl">
					<label class="form-label col-xs-3">密&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
					<div class="formControls col-xs-8">
						<input type="text" class="input-text" placeholder="点击输入" id="pw">
					</div>
				</div>
				<br/>
				<div class="row clearfix">
					<label class="form-label col-xs-3">身&nbsp;&nbsp;&nbsp;&nbsp;份：</label>
					<div class="formControls col-xs-8">
						<div class="row clearfix" style="margin-top:0">
							<div class="col-xs-6">
								<span class="select-box">
									<select class="select" size="1" name="city" id="userType">
										<option value="">点击选择</option>
										<option value="0">管理员</option>
										<option value="2">老&nbsp;&nbsp;师</option>
										<option value="1">学&nbsp;&nbsp;生</option>
									</select>
								</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" onclick="login()">确定</button>
				<button class="btn" data-dismiss="modal" aria-hidden="true" onclick="clearData()">关闭</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

	function show(){
		$("#modal-demo").modal("show")
	}

	function login(){
		
		var userCode = $("#userCode").val();
		var pwd = $("#pw").val();
		var type= $("#userType").val();
		
		$.ajax({
			url:"${pageContext.request.contextPath }/login_loginWithCode.action",
			type:"post",
			dataType:"json",
			data:{
				"userCode":userCode,
				"password":pwd,
				"userType":type
			},
			success:function(data){
				if(data.success){
					window.location.href="${pageContext.request.contextPath }/home_toHome.action";
				}else{
					alert("登录失败");
				}
			}
		})
	}
	
	function logout(){
		var res = confirm("确定要退出吗？");
		if(!res){
			return ;
		}
		$.ajax({
			url:"${pageContext.request.contextPath }/login_logout.action",
			type:"post",
			dataType:"json",
			success:function(data){
				localStorage.setItem("tableId","0");
				alert("退出成功");
				window.location.href="${pageContext.request.contextPath }/home_toHome.action";
			}
		})
	}
	
	function clearData(){
		$("#userCode").val("");
		$("#pw").val("");
		$("#userType").children().eq(0).prop("selected","selected");
	}
	
</script>


</html>