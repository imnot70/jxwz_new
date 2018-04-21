<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/thirdparty/mainpage/css/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/res/thirdparty/mainpage/css/component.css" />
<script src="${pageContext.request.contextPath }/res/thirdparty/mainpage/js/modernizr.custom.js"></script>
<script src="${pageContext.request.contextPath }/res/thirdparty/mainpage/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/res/thirdparty/mainpage/js/jquery.cbpNTAccordion.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="main">
			<ul id="cbp-ntaccordion" class="cbp-ntaccordion">
				<c:forEach items="${chapters }" var="item" varStatus="vs" >
					<li>
					<h3 class="cbp-nttrigger">${item.title }</h3>
					<div class="cbp-ntcontent">
						<p></p>
						<ul class="cbp-ntsubaccordion">
							<c:forEach items="${item.sections }" var="sec" varStatus="cvs">
								<li onclick="alertId(this)" value="${sec.id }">
									<h4 class="cbp-nttrigger">${sec.title}</h4>
									<div class="cbp-ntcontent">
										<p></p>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<script>
		$(function() {
			$('#cbp-ntaccordion').cbpNTAccordion();

		});
		
		function alertId(obj){
			obj = $(obj);
			alert(obj.val());
		}
	</script>
</body>
</html>