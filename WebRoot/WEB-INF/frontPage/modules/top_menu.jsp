<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/css/top_menu/htmleaf-demo.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/css/top_menu/bootsnav.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/res/js/top_menu/bootsnav.js"></script>
<style type="text/css">
.navbar-brand {
	padding: 29px 15px;
	height: auto;
}

nav.navbar.bootsnav {
	border: none;
	margin-bottom: 150px;
}

.navbar-nav {
	float: left;
}

nav.navbar.bootsnav ul.nav>li>a {
	color: #474747;
	text-transform: uppercase;
	padding: 30px;
}

nav.navbar.bootsnav ul.nav>li:hover {
	background: #f4f4f4;
}

.nav>li:after {
	content: "";
	width: 0;
	height: 5px;
	background: #34c9dd;
	position: absolute;
	bottom: 0;
	left: 0;
	transition: all 0.5s ease 0s;
}

.nav>li:hover:after {
	width: 100%;
}

nav.navbar.bootsnav ul.nav>li.dropdown>a.dropdown-toggle:after {
	content: "+";
	font-family: 'FontAwesome';
	font-size: 16px;
	font-weight: 500;
	position: absolute;
	top: 35%;
	right: 10%;
	transition: all 0.4s ease 0s;
}

nav.navbar.bootsnav ul.nav>li.dropdown.on>a.dropdown-toggle:after {
	content: "\f105";
	transform: rotate(90deg);
}

.dropdown-menu.multi-dropdown {
	position: absolute;
	left: -100% !important;
}

nav.navbar.bootsnav li.dropdown ul.dropdown-menu {
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
	border: none;
}

@media only screen and (max-width:990px) {
	nav.navbar.bootsnav ul.nav>li.dropdown>a.dropdown-toggle:after, nav.navbar.bootsnav ul.nav>li.dropdown.on>a.dropdown-toggle:after
		{
		content: " ";
	}
	.dropdown-menu.multi-dropdown {
		left: 0 !important;
	}
	nav.navbar.bootsnav ul.nav>li:hover {
		background: transparent;
	}
	nav.navbar.bootsnav ul.nav>li>a {
		margin: 0;
	}
}
</style>
</head>
<body>
	<div class="htmleaf-container">
		<header class="htmleaf-header">
			<h1>《编译原理》教程网站</h1>
			<div class="htmleaf-links">
			</div>
		</header>
		<div class="demo" style="padding: 2em 0;">
	        <div class="container">
	            <div class="row">
	                <div class="col-md-12" style="height:30px;">
	                    <nav class="navbar navbar-default navbar-mobile bootsnav">
	                        <div class="navbar-header">
	                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-menu">
	                                <i class="fa fa-bars"></i>
	                            </button>
	                        </div>
	                        <div class="collapse navbar-collapse" id="navbar-menu">
	                            <ul class="nav navbar-nav" data-in="fadeInDown" data-out="fadeOutUp">
	                                <!-- <li><a href="#">Home</a></li>
	                                <li><a href="#">About Us</a></li> -->
	                                <li class="dropdown">
	                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">教学资源</a>
	                                    <ul class="dropdown-menu">
	                                        <li><a href="${pageContext.request.contextPath }/home_home.action">教学资料</a></li>
	                                        <li><a href="${pageContext.request.contextPath }/home_toVideo.action">教学视频</a></li>
	                                    </ul>
	                                </li>
	                                <li class="dropdown">
	                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">问题解答</a>
	                                    <ul class="dropdown-menu">
	                                        <li><a href="${pageContext.request.contextPath }/home_toWords.action">学生留言</a></li>
	                                        <li><a href="${pageContext.request.contextPath }/home_toDiscuz.action">发帖讨论</a></li>
	                                    </ul>
	                                </li>
	                                <li><a href="${pageContext.request.contextPath }/home_toTest.action" target="main_page">在线测试</a></li>
	                            </ul>
	                        </div>
	                    </nav>
	                </div>
	            </div>
	        </div>
	    </div>
		
	</div>
	
</body>
</html>