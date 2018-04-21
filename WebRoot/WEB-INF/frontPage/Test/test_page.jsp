<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/layer/layer.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/homePage.css"/> --%>
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<!-- <link href="http://cdn.bootcss.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"> -->
<script type="text/javascript">
	function showContent(obj){
		obj = $(obj);
		alert(obj.val());
	}
	
</script>
</head>
<body>
	
	<div id="top_menu" style="z-index:100;">
		<%@ include file="../modules/top_menu.jsp" %>
	</div>
	
	<div id="left_side" style="width:15%;float:left;margin:4% 4%;font-size:2rem;">
		<%@ include file="../modules/left_annos.jsp" %>
	</div>

	<div id="main_page">
		<%@ include file="question_list.jsp" %>
	</div>
	
</body>
</html>