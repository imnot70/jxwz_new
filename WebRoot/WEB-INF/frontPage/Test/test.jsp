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
<jsp:include page="../modules/common.jsp"></jsp:include>

<style type="text/css">

</style>
<title>编译原理教程网站</title>
<meta name="keywords" content="关键词,5个左右,单个8汉字以内">
<meta name="description" content="网站描述，字数尽量空制在80个汉字，160个字符以内！">
</head>
<body>

	<div class="containBox">
		<jsp:include page="../modules/top_menu.jsp"></jsp:include>
		<div class="wap-container">
			<nav class="breadcrumb">
				<div class="container">
					<i class="Hui-iconfont">&#xe67f;</i> <a href="/jxwz_new/home_toHome.action" class="c-primary">首页</a>
					<span class="c-gray en">&gt;</span> <span class="c-gray">教学资料</span>
					<span class="c-gray en">&gt;</span> <span class="c-gray">在线测试</span>
				</div>
			</nav>
			<div class="container">
				<div class="panel panel-default" style="width:50%;margin-left:25%; min-height: 720px;">
					<div class="panel-header" >
						<span>章节测试</span>
					</div>
					<br/>
					<div style="font-size:1rem;" class="panel-body">
						<c:forEach items="${ques }" var="item"  varStatus="vs">
							<div style="overflow: hidden;">
								<div class="Huialert Huialert-info" style="overflow: hidden;">
									<input class="queId" type="hidden" value="${item.id}" />
									<div style="float:left;display:inline-block;"> ${item.content} </div>
									<c:if test="${user != null }">
										<button onclick="addIncr(${item.id})" style="float:right;display:none;" class="btn btn-primary radius errQue">添加到错题本</button>
									</c:if>
								</div>
								<div style="display:none" class="Huialert Huialert-danger"></div>
								<c:forEach items="${item.options }" var="op">
									<div style="overflow: hidden;">
										<div style="display:inline-block;float:left;">
											<input value="${op.answerCode }" type="radio" name="op${vs.index }">
										</div>
										<div style="display:inline-block;float:left;">${op.answerCode }.${op.content }</div>
									</div>
								</c:forEach>
							</div>
						</c:forEach>
						
						<div class="modal-footer">
							<span style="float:left;margin-left:20px;" id="point"></span>
							<button id="submitBtn" class="btn btn-primary" >提交</button>
						</div>
					</div>
				</div>
			</div>
			
			<jsp:include page="../modules/footer.jsp"></jsp:include>
			
		</div>
	</div>
</body>
<script type="text/javascript">

$(function(){
	$("#test").prop("class","current");
})

$("#submitBtn").bind("click",function(){

	// 校验是否有为题未被选择
	for(var i=0;i<5;i++){
		var temp = $("input[name='"+"op"+i+"']:checked").val()
		if(typeof(temp) == "undefined"){
			alert("请选择第"+(i+1)+"题的答案");
			return false;
		}
	}
	
	var answer = "";
	var inputs = $(".queId");
	for(var i=0;i<5;i++){
		answer += inputs.eq(i).val();
		answer += ",";
		answer += $("input[name='"+"op"+i+"']:checked").val();
	}
	
	getResule(answer);
})


function getResule(answer){
	$.ajax({
		url:"${pageContext.request.contextPath}/test_getResult.action",
		type:"post",
		data:{
			"resultStr":answer
		},
		dataType:"json",
		success:function(data){
			if(!data.result){
				alert("系统异常")
			}else{
				var point = 100;
				if(data.result != null && data.result.length != 0){
					$(data.result).each(function(idx,obj){
						$("#submitBtn").css("display","none");
						point -= 20;
						var target = $("input[value='"+obj.queId+"']");
						target.parent().attr("class","Huialert Huialert-danger");
						var content = "答案："+ obj.code+"，分析："+obj.remark;
						var tip = target.parent().parent().children().eq(1);
						tip.html(content);
						tip.css("display","block");
					})
				}
				$("#point").html("得分："+point);
				$(".errQue").css("display","inline-block");
			}
		}
	})
}

function addIncr(id){
	alert(id);
}

</script>
</html>
