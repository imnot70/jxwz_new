<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<style type="text/css">
		.anno_li {
			margin-top:20px;
			margin-bottom:20px;
			cursor : pointer;
		}
	</style>
</head>
<body>
	<ul style="background-color: #52565D;padding:20px;">
		<c:forEach items="${annos }" varStatus="vs" var="item">
			<li class="anno_li" value="${item.id }" onclick="showContent(this)">${item.title }</li>
		</c:forEach>
	</ul>
</body>

