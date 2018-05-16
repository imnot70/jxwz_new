<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="res/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.min.css" />
<!--[if lt IE 9]>
<link href="res/h-ui/css/H-ui.ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<style type="text/css">
.ui-sortable .panel-header{ cursor:move}
</style>
<title>编译原理教程网站</title>
<meta name="keywords" content="关键词,5个左右,单个8汉字以内">
<meta name="description" content="网站描述，字数尽量空制在80个汉字，160个字符以内！">
</head>
<body>

<div class="containBox">
    <div class="containBox-bg"></div>
	<header class="navbar-wrapper">
		<div class="navbar navbar-black navbar-fixed-top">
			<div class="container cl">
				<a class="logo navbar-logo hidden-xs" href="#">《编译原理》教程网站</a>
				<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs JS-nav-toggle" href="javascript:;">&#xe667;</a>
				<nav class="nav navbar-nav nav-collapse" role="navigation" id="Hui-navbar">
					<ul class="cl">
						<li>
							<a href="${pageContext.request.contextPath }/home_toHome.action" target="_self">教学资料</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath }/home_toVidoList.action" target="_self">教学视频</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath }/home_toTest.action" target="_self">在线测试</a>
						</li>
						<li>
							<a href="#" target="_self">登录</a>
						</li>
					</ul>
				</nav>
				<nav class="navbar-userbar hidden-xs"></nav>
			</div>
		</div>
	</header>
	<div class="wap-container">
		<nav class="breadcrumb">
			<div class="container">
				<i class="Hui-iconfont">&#xe67f;</i>
				<a href="/" class="c-primary">首页</a>
				<span class="c-gray en">&gt;</span>
				<span class="c-gray en">&gt;</span>
				<span class="c-gray">当前页面</span>
			</div>
		</nav>
		<div class="container ui-sortable">
			<div class="panel panel-default">
				<div class="panel-header">章节列表</div>
				<div class="panel-body">
					<c:forEach items="${chapters }" var="item" varStatus="vs">
						<li>
							${item.title}
						</li>
					</c:forEach>
				</div>
			</div>
			
		</div>
		
		<footer class="footer mt-20">
			<div class="container">
				<p>《编译原理》教程网站 作者：萌萌哒孙国冉</p>
			</div>
		</footer>
	</div>
</div>
<!--普通弹出层-->
<div id="modal-demo" class="modal fade middle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content radius">
			<div class="modal-header">
				<h3 class="modal-title">对话框标题</h3>
				<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a>
			</div>
			<div class="modal-body">
				<p>对话框内容…</p>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary">确定</button>
				<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/jquery-ui/1.9.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="res/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="lib/jquery.SuperSlide/2.1.1/jquery.SuperSlide.min.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.min.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.min.js"></script>
<script>
//弹窗
function modaldemo(){
	$("#modal-demo").modal("show");
}
//消息框
function modalalertdemo(){
	$.Huimodalalert('我是消息框，2秒后我自动滚蛋！',2000);
}
$(function(){
	$(".input-text,.textarea").Huifocusblur();
	
	//幻灯片
	/* 
		jQuery("#slider-3 .slider").slide({mainCell:".bd ul",titCell:".hd li",trigger:"click",effect:"leftLoop",autoPlay:true,delayTime:700,interTime:3000,pnLoop:false,titOnClassName:"active"});
	 */
	$(".panel").Huifold({
		titCell:'.panel-header',
		mainCell:'.panel-body',
		type:1,
		trigger:'click',
		className:"selected",
		speed:"first",
	});
	
	//邮箱提示	
	$("#email").emailsuggest();
	
	//checkbox 美化
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	//日期插件
	$("#datetimepicker").datetimepicker({
    	format: 'yyyy-mm-dd',
		minView: "month",
		todayBtn:  1,
		autoclose: 1,
		endDate : new Date()
	}).on('hide',function(e) {
		//此处可以触发日期校验。
	});
	
	/*+1 -1效果*/
	$("#spinner-demo").Huispinner({
		value:1,
		minValue:1,
		maxValue:99,
		dis:1
	});
	
	$(".textarea").Huitextarealength({
		minlength:10,
		maxlength:200.
	});
	
	$("#demoform").validate({
		rules:{
			email:{
				required:true,
				email:true,
			},
			username:{
				required:true,
				minlength:4,
				maxlength:16
			},
			telephone:{
				required:true,
				isMobile:true,
			},
			password:{
				required:true,
				isPwd:true,
			},
			password2:{
				required:true,
				equalTo: "#password"
			},
			sex:{
				required:true,
			},
			datetimepicker:{
				required:true,	
			},
			checkbox2:{
				required:true,
			},
			city:{
				required:true,
			},
			website:{
				required:true,
				url:true,
			},
			beizhu:{
				maxlength:500,
			}
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$("#modal-shenqing-success").modal("show");
			$(form).ajaxSubmit();
		}
	});

	//选项卡
	$("#HuiTab-demo1").Huitab({
		index:0
	});
	
	$("#Huitags-demo1").Huitags();
	
	//返回顶部
	$.Huitotop();
	
	//hover效果
	$('.maskWraper').Huihover();
	
	//星级评价
	$("#star-1").raty({
		hints: ['1','2', '3', '4', '5'],//自定义分数
		starOff: 'iconpic-star-S-default.png',//默认灰色星星
		starOn: 'iconpic-star-S.png',//黄色星星
		path: 'res/h-ui/images/star',//可以是相对路径
		number: 5,//星星数量，要和hints数组对应
		showHalf: true,
		targetKeep : true,
		click: function (score, evt) {//点击事件
			//第一种方式：直接取值
			$("#result-1").html('你的评分是'+score+'分');
		}
	});

	$( ".ui-sortable" ).sortable({
		//connectWith: ".panel",
		items:".panel",
		handle: ".panel-header",
		//delay: 300, //时间延迟
		//distance: 15, //距离延迟
		placeholder: "ui-state-highlight", //占位符样式
		update: function(event, ui){
			
		}
	}).disableSelection();

 	var _bodyHeight = $(window).height();
	var _doch = $(document).height();
	$(".containBox").height(_bodyHeight);

	/*左右滑动菜单*/
    $(".JS-nav-toggle").click(function() {
		$("body").addClass('sideBox-open');
        $(".containBox-bg").height(_bodyHeight).show();
    });
    $(".containBox-bg").click(function() {
		$(this).hide();
		$("body").removeClass('sideBox-open');
    });
});
</script>
</body>
</html>
<!--H-ui前端框架提供前端技术支持 h-ui.net @2017-01-01 -->