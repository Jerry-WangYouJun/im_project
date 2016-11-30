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
	
	function clearData(){
		 $("#customerName").val("")  ;
		 $("#prodName").val("") ;
		 $("#deliveryDateBegin").val("") ;
		 $("#deliveryDateEnd").val("") ; 
	}
	
	function query(pageNo) {
		if (pageNo != undefined) {
			$("#pageNo").val(pageNo);
		}
		var customerName= $("#customerName").val()  ;
		var prodName = $("#prodName").val() ;
		var deliveryDateBegin =  $("#deliveryDateBegin").val() ;
		var deliveryDateEnd = $("#deliveryDateEnd").val() ; 
		var action = "${basePath}/OrderServlet?customerName=" + customerName 
				+ "&prodName=" + prodName + "&deliveryDateBegin=" + deliveryDateBegin + "&deliveryDateEnd=" + deliveryDateEnd;
		$("#prodForm").attr("action",action).submit();
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
		var path = "${basePath}/OrderServlet?action=insert&pageNo="+$("#pageNo").val() + "&pageSize="+ $("#pageSize").val();
		$('#insertOrderForm').attr("action", path).submit();
	}
	
	function updateData(){
		var path = "${basePath}/OrderServlet?action=update&pageNo="+$("#pageNo").val() + "&pageSize="+ $("#pageSize").val();
		$('#insertOrderForm').attr("action", path).submit();
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
			url : '${basePath}/OrderServlet?action=delete&id=' + id,
			success: function(){
				query($("#pageNo").val());
	      	},
	      	error: function(){
	      		alert("添加数据失败，请联系管理员！");
	      	}
		}); */
		var path = "${basePath}/OrderServlet?action=delete&id="+ id+"&pageNo="+$("#pageNo").val() + "&pageSize="+ $("#pageSize").val();
		window.location.href=path;
	}
		
	function editInit(act){
		if(act == "update"){
			var id = getChecked();
			if(id){
				$("#orderid").val(id);
				$("#customid").val($("#"+id+"_cid").val());
				$("#prodid").val($("#"+id+"_pid").val());
				$("#orderCode").val($("#"+id+"_orderCode").html());
				$("#customerNameAdd").val($("#"+id+"_customer_customerName").html());
				$("#prodCodeAdd").val($("#"+id+"_product_prodCode").html());
				$("#prodNameAdd").val($("#"+id+"_product_prodName").html());
				$("#quantity").val($("#"+id+"_quantity").html());
				$("#deliveryDateStr").val($("#"+id+"_deliveryDateStr").html());
				$("#description").val($("#"+id+"_description").html());
			}else{
				return ; 
			}
		}else{
			$("#orderid").val("");
			$("#customid").val("");
			$("#prodid").val("");
			$("#orderCode").val("");
			$("#customerNameAdd").val("");
			$("#prodCodeAdd").val("");
			$("#quantity").val("");
			$("#deliveryDateStr").val("");
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
	
</script>
</head>
<body>
	<!-- 中间开始 -->

	<div class="main">
		<form action="${basePath}/OrderServlet" method="POST" id="prodForm">
			<div class="main-right">
				<div class="content">
					<p class="content-top">
						<span>客户</span><input type="text" id = "customerName"  value= "${customerName}"/>
						<span>产品</span><input type="text" id = "prodName" value= "${prodName}"/>
						<span>交货日期</span>
						<input class="top-data" type="date" id="deliveryDateBegin" value= "${deliveryDateBegin}"/>
						 - <input type="date" id="deliveryDateEnd" value= "${deliveryDateEnd}"/>
						   <input class="bta" type="button" value="查询" onclick="query()"/>
						   <input class="btd" type="button" value="清除" onclick="clearData()"/>
					</p>
					<div class="main-button">
						<input class="btd" type="button" value="新建" onclick="editInit('insert')">
						<input class="btc" type="button" value="修改" onclick="editInit('update')">
						<input class="bta" type="button" value="删除" onclick="deleteProd()">
					</div>

					<div class="main-list">
						<div class="main-list-top">
							<table width="100%"  cellspacing="0" cellpadding="0">
								<thead>
									<tr>
										<td width="5%"></td>
										<td width="15%">订单编号</td>
										<td width="15%">客户</td>
										<td width="15%">产品编号</td>
										<td width="15%">产品名称</td>
										<td width="10%">数量</td>
										<td width="10%">交货日期</td>
										<td width="15%">备注</td>
									</tr>
								</thead>
							</table>
						</div>
						<div class="main-list-cont">
							<table  width="100%" cellspacing="0" cellpadding="0">
								<tbody>
									<c:forEach var="order" items="${orderList}">
										<tr>
											<td width="5%"><input type="checkbox" id="${order.id}_id" value="${order.id}">
											<input type = "hidden" id="${order.id}_cid"  value="${order.customer.id}"/>
											<input type = "hidden" id="${order.id}_pid" value="${order.product.id}" />
											</td>
											<td id="${order.id}_orderCode" width="15%">${order.orderCode}</td>
											<td id="${order.id}_customer_customerName" width="15%">${order.customer.customerName}</td>
											<td id="${order.id}_product_prodCode" width="15%">${order.product.prodCode}</td>
											<td id="${order.id}_product_prodName" width="15%">${order.product.prodName}</td>
											<td id="${order.id}_quantity" width="10%">${order.quantity}</td>
											<td id="${order.id}_deliveryDateStr" width="10%">${order.deliveryDateStr}</td>
											<td id="${order.id}_description" width="15%">${order.description}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<jsp:include page="/pagination.jsp" flush="true" />
				</div>
			</div>
		
		</form>
	</div>
	
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
				<jsp:include page="/jsp/order_add.jsp" flush="true" />
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

</body>
</html>