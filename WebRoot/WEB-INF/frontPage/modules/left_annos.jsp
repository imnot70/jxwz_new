<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<style type="text/css">
.sidebar {
	position: fixed;
	right: 0;
	top: 120px;
	bottom: 0;
	width: 225px;
	font-size:1.25rem;
	list-style-type:none;
	padding-left:20px;
}

.anno_li {
	cursor: pointer;
	margin-top:15px;
	margin-bottom:15px;
	padding-left:10px;
}
</style>
<script type="text/javascript">
	function annoDetail(annoId){
		$.ajax({
			url:"${pageContext.request.contextPath}/anno_annoDetail.action",
			type:"post",
			data:{
				"annoId":annoId
			},
			dataType:"json",
			success:function(data){
				$("#anno-container").html("");
				$("#anno-title").html("");
				var str = "<p>"+data.anno.content+"</p>"
				$("#anno-container").html(str);
				$("#anno-title").html(data.anno.title);
			}
		})
		$("#modal-demo-anno").modal("show");
	}
</script>
</head>
<body>
	<div class="sidebar container ui-sortable panel-default">
		<div class="panel-header" style="text-align:center;">公告</div>
		<c:forEach items="${annos }" var="item" varStatus="vs">
			<li class="anno_li Huialert-info" onclick="annoDetail('${item.id}')">${item.title}</li>
		</c:forEach>
	</div>
	
	<div id="modal-demo-anno" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" id="anno-title" style="text-align:center;"></h3>
					<!-- <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a> -->
				</div>
				<div class="modal-body" id="anno-container" style="min-height:500px;">
					
				</div>
				<div class="modal-footer">
					<!-- <button class="btn btn-primary">确定</button> -->
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>

