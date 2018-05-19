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

.col1{
	text-align:center;
}

</style>
</head>
<body>
	<div class="panel-header">
		<button onclick="addKnow()" class="btn btn-primary radius" >上传</button>
	</div>
	<table class="table table-border table-bordered table-striped mt-20">
		<thead>
			<tr>
				<th class="col1">ID</th>
				<th class="col1">章</th>
				<th class="col1">节</th>
				<th class="col1">标题</th>
				<th class="col1">操作</th>
			</tr>
		</thead>
		<tbody id="knowContainer">
		</tbody>
	</table>
	
	<div id="modal-demo-add-Know" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content radius">
				<div class="modal-header">
					<h3 class="modal-title" style="text-align: center;">上传知识点</h3>
				</div>
				<div class="modal-body" style="min-height: 300px;">
					<div class="containBox">
						<div class="wap-container">
							<div class="row cl">
								<label class="form-label col-xs-3">选择章：</label>
								<div class="col-xs-8">
									<span class="select-box"> 
									<select id="know-add-cha" class="user-prop select" onchange="findKnowSections()">
									</select>
									</span>
								</div>
							</div>
							<br/>

							<div class="row clearfix">
								<label class="form-label col-xs-3">选择节：</label>
								<div class="col-xs-8">
									<span class="select-box"> 
									<select id="know-add-sec" class="user-prop select">
										<option>点击选择</option>
									</select>
									</span>
								</div>
							</div>
							<br/>
							<div class="row cl">
								<label class="form-label col-xs-3">选择文件：</label>
								<div class="formControls col-xs-8">
									<span class="btn-upload form-group">
										<input class="input-text upload-url" type="text" name="myfile"  readonly="readonly" style="width:200px">
										<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont"></i> 浏览文件</a>
										<input id="knowFileInput" type="file" name="myfile" class="input-file">
									</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button onclick="upload()" class="btn btn-primary" >确定</button>
					<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<input id="totalCountKnow" value="${totalCountknow }" type="hidden"/>
	<fieldset id="pagerKnow" class="layui-elem-field layui-field-title" style="margin-top: 30px;text-align:center;"> </fieldset>
</body>
<script type="text/javascript">
	var pageNumKnow = 0;
	$(function() {
		pageHelperKnow(callbackKnow);
	})
	
	function callbackKnow(num){
		pageNumKnow = num;
		$.ajax({
			url : "${pageContext.request.contextPath}/doc_findKnows.action",
			data : {
				"pageNum" : num,
				"pageSize":10
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				var container = $("#knowContainer");
				container.html("");
				$(data.knows).each(function(idx, obj) {
					var item = generateItemKnow(obj);
					container.append(item);
				})
			}
		})
	}
	
	var pageHelperKnow = function(callback){
		layui.use('laypage', function() {
			var laypage = layui.laypage;
			var totalCount = $("#totalCountKnow").val();
			
			laypage.render({
				elem : 'pagerKnow',
				count : totalCount,
				limit:10,
				curr: location.hash.replace('#!pageNum=', '') ,
			    hash: "pageNum" ,
				jump : function(obj) {
					//console.log(obj);
					callback(obj.curr);
				}
			});
		});
	}
	
	function generateItemKnow(obj){
		var tr = $("<tr></tr>");
		var td1 = $("<td style='text-align:center;'></td>");
		td1.html(obj.id);
		tr.append(td1);
		var td2 = $("<td style='text-align:center;'></td>");
		td2.html(obj.section.chapter.title);
		tr.append(td2);
		var td3 = $("<td style='text-align:center;'></td>");
		td3.html(obj.section.title);
		tr.append(td3);
		var td4 = $("<td style='text-align:center;'></td>");
		td4.html(obj.title);
		tr.append(td4);
		var td5 = $("<td style='text-align:center;width:30%;'></td>");

		var btn2 = createBtn("预览", "check('"+obj.url+"')", "btn btn-primary radius");
		td5.append(btn2);
		
		var btn1 = createBtn("删除", "delKnow("+obj.id+")", "btn btn-danger radius");
		td5.append(btn1);
		tr.append(td5);
		return tr;
	}
	
	function addKnow(){
		var container = $("#know-add-cha");
		findChapters(container);
		$("#modal-demo-add-Know").modal("show");
	}
	
	function findChapters(container){
		$.ajax({
			url:"${pageContext.request.contextPath}/cha_findAllChapters.action",
			type:"post",
			dataType:"json",
			success:function(data){
				container.html("");
				var defaultOp = $("<option value='0'>点击选择</option>")
				container.append(defaultOp);
				fillContainer(container,data.chapters);
			}
		})
	}
	
	function findKnowSections(){
		var container = $("#know-add-sec");
		findSections(container);
	}
	
	
	function findSections(container){
		var id = $("#know-add-cha").val();
		if(id == 0){
			$(".defOp").attr("selected","selected");
			return ;
		}
				
		$.ajax({
			url:"${pageContext.request.contextPath}/cha_findSection.action",
			type:"post",
			data:{
				"chaId":id
			},
			dataType:"json",
			success:function(data){
				container.html("");
				var defaultOp = $("<option class='defOp' value='0'>点击选择</option>");
				container.append(defaultOp);
				fillContainer(container,data.sections);
			}
		})
	}
	
	function fillContainer(container,list){
		if(list != null && list.length != 0){
			$(list).each(function(idx,obj){
				var op = generateOp(obj);
				container.append(op);
			})
		}
	}
	
	function generateOp(obj){
		var op = $("<option></option>");
		
		op.val(obj.id);
		op.html(obj.title);
		return op;
	}
	
	function upload() {
		var chaId = $("#know-add-cha").val();
		var secId = $("#know-add-sec").val();
		
        var path = document.getElementById("knowFileInput").value;
        if ($.trim(path) == "") { alert("请选择要上传的文件"); return; }

        $.ajaxFileUpload({
            url: '${pageContext.request.contextPath}/doc_uploadKnow.action',  //这里是服务器处理的代码
            type: 'post',
            //secureuri: false, //一般设置为false
            fileElementId: 'knowFileInput', // 上传文件的id、name属性名
            dataType: 'json', //返回值类型，一般设置为json、application/json
            data: {
            	"chapterId":chaId,
            	"secId":secId
            }, //传递参数到服务器
            success: function (data) {
            	if(data.success){
            		alert("上传成功");
            		window.location.reload();
            	}else{
            		alert(data.msg);
            	}
            }
        });
    }
	
	function check(url){
		window.open(url);
	}
	
	function delKnow(id){
		$.ajax({
			url:"${pageContext.request.contextPath}/doc_delKnow.action",
			type:"post",
			data:{
				"knowId":id
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
	}

</script>
</html>
