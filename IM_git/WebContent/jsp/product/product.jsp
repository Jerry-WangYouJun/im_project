<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品管理</title>
<link href="${basePath}/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${basePath}/css/styles.css" type="text/css" rel="stylesheet" />
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="${basePath}/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("tr:odd").css("background","#bbffff");
	});
	
	function query(pageNo) {
		if (pageNo != undefined) {
			$("#pageNo").val(pageNo);
		}
		$("#prodForm").submit();
	}

		
	function queryLast(){
		 var pageNo = $("#pageNo").val();
		 if (pageNo != undefined) {
				$("#pageNo").val(pageNo);
			}
		 if(parseInt(pageNo) > 1 ){
			 query(parseInt(pageNo) - 1);
		 }
	}
	
	function queryNext(){
		 var pageNo = $("#pageNo").val();
		 if (pageNo != undefined) {
				$("#pageNo").val(pageNo);
			}
		 if(parseInt(pageNo) < parseInt( "${pagination.pageIndex}")){
			 query(parseInt(pageNo) + 1);
		 }
	}
	
	function  editbtn(){
		 var act =  $("#act").val();
		 if(act=="insert")
			 insertData();
		 else
			 updateData();
	}
	
	function insertData(){
		var path = "${basePath}/ProductServlet?action=insert&pageNo="+$("#pageNo").val() + "&pageSize="+ $("#pageSize").val();
		$('#insertProdForm').attr("action", path).submit();
	}
	
	function updateData(){
		var path = "${basePath}/ProductServlet?action=update&pageNo="+$("#pageNo").val() + "&pageSize="+ $("#pageSize").val();
		$('#insertProdForm').attr("action", path).submit();
	}
	
	function deleteProd(){
		var id;
		var checkTotal = 0;
		$("input[type=checkbox]").each(function() {
			if (this.checked) {
				id = $(this).val();
				checkTotal++;
			}
		});
		if (checkTotal == 0) {
			alert("请选中一条数据！");
			return;
		} else if (checkTotal > 1) {
			alert("只能选择一条数据！");
			return;
		}
		/* $.ajax({
			type:'POST',
			url : '${basePath}/ProductServlet?action=delete&id=' + id,
			success: function(){
				query($("#pageNo").val());
	      	},
	      	error: function(){
	      		alert("添加数据失败，请联系管理员！");
	      	}
		}); */
		var path = "${basePath}/ProductServlet?action=delete&id="+ id+"&pageNo="+$("#pageNo").val() + "&pageSize="+ $("#pageSize").val();
		window.parent.mainFrame.location.href=path;
	}
		
	function editInit(act){
		if(act == "update"){
			var id = getChecked();
			if(id){
				$("#proid").val(id);
				$("#prodCode").val($("#"+id+"_prodCode").html());
				$("#prodName").val($("#"+id+"_prodName").html());
				$("#prodSpec").val($("#"+id+"_prodSpec").html());
				$("#prodType").val($("#"+id+"_prodType").html());
				$("#price").val($("#"+id+"_price").html());
				$("#description").val($("#"+id+"_description").html());
			}else{
				return ; 
			}
		}else{
			$("#proid").val("");
			$("#prodCode").val("");
			$("#prodName").val("");
			$("#prodSpec").val("");
			$("#prodType").val("");
			$("#price").val("");
			$("#description").val("");
		}
			$("#act").val(act);
			$("#myModal").modal({backdrop:"static"});
	}
	
	function getChecked(){
		var id;
		var checkTotal = 0;
		$("input[type=checkbox]").each(function() {
			if (this.checked) {
				id = $(this).val();
				checkTotal++;
			}
		});
		if (checkTotal == 0) {
			alert("请选中一条数据！");
			return;
		} else if (checkTotal > 1) {
			alert("只能选择一条数据！");
			return;
		}
		return id ;
	}
	
	function  getSelect(){
			var id = getChecked();
		if(id){
			window.opener.document.getElementById('prodCodeAdd').value=$("#"+id+"_prodName").html();
			window.opener.document.getElementById('prodNameAdd').value=$("#"+id+"_prodCode").html();
			window.opener.document.getElementById('prodid').value = id ;
			window.close();
		}else{
			return ;
		}
	}
</script>
</head>
<body>
<!-- 	<div class="base">

		头部开始

		<div class="top">
			<a class="logo" href="javascript:void(0)"> <img
				src="images/index/logo_01.png" />
			</a> <span class="top-name">智能制造系统</span>
		</div>

		<div class="top-line"></div>

	</div> -->

	<!-- 头部结束 -->

	<!-- 中间开始 -->

	<div class="main">
		<%-- <jsp:include page="../../left.jsp" flush="true" /> --%>
		<form action="${basePath}/ProductServlet" method="POST" id="prodForm">
			<div class="main-right">
				<div class="content">
					<div class="main-button">
						<input class="bta" type="button" value="查询" onclick="query()">
						<input class="btd" type="button" value="新建" onclick="editInit('insert')">
						<input class="btc" type="button" value="修改" onclick="editInit('update')">
						<input class="bta" type="button" value="删除" onclick="deleteProd()">
						<input class="btd" type="button" value = "选择" onclick="getSelect()">
					</div>

					<div class="main-list">
						<div class="main-list-top">
							<table width="915px" cellspacing="0" cellpadding="0">
								<thead>
									<tr>
										<td></td>
										<td width="150">产品编号</td>
										<td width="150">产品名称</td>
										<td width="150">产品规格</td>
										<td width="150">产品型号</td>
										<td width="150">单价</td>
										<td width="135">备注</td>
									</tr>
								</thead>
							</table>
						</div>
						<div class="main-list-cont">
							<table width="915px" cellspacing="0" cellpadding="0">
								<tbody>
									<c:forEach var="product" items="${list}">
										<tr>
											<td><input type="checkbox" id="${product.id}_id" value="${product.id}"></td>
											<td id="${product.id}_prodCode" width="150">${product.prodCode}</td>
											<td id="${product.id}_prodName" width="150">${product.prodName}</td>
											<td id="${product.id}_prodSpec" width="150">${product.prodSpec}</td>
											<td id="${product.id}_prodType" width="150">${product.prodType}</td>
											<td id="${product.id}_price" width="150">${product.price}</td>
											<td id="${product.id}_description" width="135">${product.description}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<jsp:include page="../../pagination.jsp" flush="true" />
				</div>
			</div>
		</form>
	</div>

	<!-- 
	<div class="bottom">©青岛英谷教育科技股份有限公司 版权所有 电话：0532-88979016</div> 底部结束 -->
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">新增</h4>
				</div>
				<jsp:include page="product_add.jsp" flush="true" />
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
        			<button type="button" class="btn btn-primary" onclick = "editbtn()">提交更改</button>
					
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
</html>