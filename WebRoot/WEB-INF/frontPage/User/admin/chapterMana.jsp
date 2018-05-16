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

.doc_li {
	font-size: 1.25rem;
	list-style-type: none;
	width: 75%;
	margin-left: 12.5%;
	cursor: pointer;
}
.sub_li{
	font-size: 1rem;
	width: 60%;
	margin-left: 20%;
	list-style-type: none;
	cursor:pointer;
}

.right_btn{
	float:right;
	margin-right:10px;
}

/* details summary::-webkit-details-marker { display:none; } */
</style>
</head>
<body>
	<div class="panel-header">
		<button onclick="addChapter(1,1,null,null)" class="btn btn-primary radius">增加</button>
	</div>
	<div id="containerCha" class="panel-body">
		<c:forEach items="${chapters }" var="item" varStatus="vs">
			<details >
				<summary class="Huialert Huialert-success doc_li">
					<%-- <span style="float:left;display:inline-block;">排序：${item.sort }</span>
					&nbsp;&nbsp;&nbsp;&nbsp; --%>
					<span>
						排序：${item.sort }${item.title }
					</span>
					<span>
						<button onclick="addChapter(1,2,null,${item.id})" class="btn btn-primary rdius right_btn">增加节</button>
						<button onclick="addChapter(2,1,${item.id},null)" class="btn btn-primary rdius right_btn">修改</button>
						<button onclick="delObj(1,${item.id})" class="btn btn-danger rdius  right_btn">删除</button>
					</span>
				</summary>
				<c:forEach items="${item.sections }" var="subItem">
					<li style="overflow: hidden;" class="Huialert Huialert-info sub_li">						
						<span style="float:left;display:inline-block;">排序：${subItem.sort }</span>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<div style="display:inline-block;">
							${subItem.title }&nbsp;&nbsp;&nbsp;&nbsp;${subItem.subTitle}
						</div>
						<button onclick="addChapter(2,2,${subItem.id},${subItem.chapter.id })" class="btn btn-primary right_btn">修改</button>
						<button onclick="delObj(2,${subItem.id})" class="btn btn-danger  right_btn">删除</button>
					</li>
				</c:forEach>
			</details>
		</c:forEach>
	</div>

	<div id="modal-demo-add-cha" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 id="win-add-cha-title" class="modal-title" style="text-align: center;">添加章</h3>
				</div>
				<div class="modal-body" style="min-height: 200px;">
					<div class="containBox">
						<div class="wap-container">
							<br />
							<div class="formControls">
								<input id="cha_title" class="input-text" placeholder="标题">
							</div>
						</div>
						<br />
						<div class="wap-container">
							<br />
							<div class="formControls">
								<input id="cha_sub_title" class="input-text" placeholder="副标题">
							</div>
						</div>
						<br />
						<div class="wap-container">
							<br />
							<div class="formControls">
								<input id="cha_sort" class="input-text" placeholder="序号 将根据序号进行排序">
							</div>
						</div>
						<input type="hidden" id="cha_add_id" />
						<input type="hidden" id="obj_id" />
						<input type="hidden" id="obj_level" />
					</div>
				</div>
				<div class="modal-footer">
					<button id="submitBtnCha" class="btn btn-primary" type="submit">确定</button>
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	
	// type = 1:添加,2:修改
	// level = 1:章,2:节
	function addChapter(type,level,id,chapterId){
		if(type == 1){
			$("#cha_title").val("");
			$("#cha_sub_title").val("");
			$("#cha_sort").val("");
			$("#obj_id").val("");
			$("#obj_level").val(level);
			if(level == 1){
				$("#win-add-cha-title").html("添加章");
			}else{
				$("#cha_add_id").val(chapterId);
				$("#win-add-cha-title").html("添加节");
			}
			$("#modal-demo-add-cha").modal("show");
		}else{
			modify(level,id,chapterId);
		}
	}
	
	function modify(level,id,chapterId){
		var url = "";
		if(level == 1){
			url="${pageContext.request.contextPath}/cha_getChaDetail.action";
		}else{
			$("#cha_add_id").val(chapterId);
			url="${pageContext.request.contextPath}/cha_getSecDetail.action";
		}
		$.ajax({
			url:url,
			tyep:"post",
			data:{
				"chaId":id,
				"secId":id
			},
			dataType:"json",
			success:function(data){
				if(level == 1){
					$("#win-add-cha-title").html("修改章");
					$("#cha_title").val(data.chapter.title);
					$("#cha_sub_title").val(data.chapter.subTitle);
					$("#cha_sort").val(data.chapter.sort);
					$("#obj_id").val(data.chapter.id);
				}else{
					$("#win-add-cha-title").html("修改节");
					$("#cha_title").val(data.section.title);
					$("#cha_sub_title").val(data.section.subTitle);
					$("#cha_sort").val(data.section.sort);
					$("#obj_id").val(data.section.id);
				}
				$("#obj_level").val(level);
				
				$("#modal-demo-add-cha").modal("show");
			}
			
		})
		
	}
	
	// level = 1:章,2:节
	function delObj(level,id){
		var msg = "";
		if(level == 1){
			msg = "确定要删除本章和其下的节吗？";
		}else{
			msg = "确定要删除吗？";
		}
		var result = confirm(msg);
		if(!result){
			return;
		}
		var url="";
		if(level == 1){
			url="${pageContext.request.contextPath}/cha_deleteCha.action";
		}else{
			url="${pageContext.request.contextPath}/cha_deleteSec.action";
		}
		$.ajax({
			url:url,
			type:"post",
			data:{
				"chaId":id,
				"secId":id
			},
			dataType:"json",
			success:function(data){
				if(data.success){
					alert("删除成功");
					window.location.reload();
				}else{
					alert("删除失败");
				}
			}
		})
	}
	
	$("#submitBtnCha").bind("click",function(){
		var level = $("#obj_level").val();
		var id = $("#obj_id").val();
		var url="${pageContext.request.contextPath}/";
		var title=$("#cha_title").val();
		var subTitle = $("#cha_sub_title").val();
		var chaId = $("#cha_add_id").val();
		var sort =$("#cha_sort").val();
		
		if(title == null || ""== title || typeof(title) == "undefined"){
			alert("请填写标题");
			return false;
		}
		if(sort == null || ""== sort || typeof(sort) == "undefined"){
			alert("请设置序号");
			return false;
		}
		
		
		// 新增或修改
		if(level == 1){
			url=url+"cha_saveCha.action";
		}else{
			url=url+"cha_saveSec.action";
		}
		
		$.ajax({
			url:url,
			type:"post",
			data:{
				"chapter.id":id,
				"section.id":id,
				"chapter.title":title,
				"section.title":title,
				"chapter.subTitle":subTitle,
				"section.subTitle":subTitle,
				"chapter.sort":sort,
				"section.sort":sort,
				"chaId":chaId
			},
			dataType:"json",
			success:function(data){
				
				if(data.success){
					alert("操作成功");
					window.location.reload();
				}else{
					alert("操作失败");
				}
			}
		})
	})
	
</script>
</html>
