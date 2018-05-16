<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script>
	$(function(){
		var dest=$("#dest").val();
		window.location.href="${pageContext.request.contextPath}/user_center.action?dest="+dest;
	});
</script>
</head>
<body>
	<input type="hidden" value="" id="dest"/>
</body>
</html>



