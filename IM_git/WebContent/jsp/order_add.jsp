<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript">
function getProd(){
	/* $("#selectModal").modal({backdrop:"static"}); */
	var obj = window.open("${basePath}/ProductServlet" , "_blank","toolbar=no, location=no, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=1000, height=800 , top=200 , left = 400");
}	
</script>
<style type="text/css">
.modal-dialog {
    width: 800px !important;
    margin: 30px auto;
    position: relative;
}
</style>
<form id="insertOrderForm" action="${basePath}/OrderServlet?action=insert"
	method="POST" >
	<input type="hidden" id="orderid" name="orderid" value="${order.id}" />
	<input type="hidden" id="prodid" name="prodid" />
	<input type="hidden" id="customid" name="customid" />
	<input type="hidden" id="act" name="act" />
		<table class="table" >
			<tr class="active" >
				<td align="right"  >订单编号</td>
				<td  align="center"  ><input type="text" id="orderCode" name="orderCode"
					 /></td>
			</tr>
			<tr align="center">
				<td align="right">客户</td>
				<td><input type="text" id="customerNameAdd" name="customerNameAdd"
					 /></td>
			</tr>
			<tr class="active" align="center">
				<td align="right"><span>产品编号</span></td>
				<td><input type="text" id="prodCodeAdd" name="prodCode2"
					 /><a onclick="getProd()">选择</a></td>
			</tr>
			<tr align="center">
				<td align="right"><span>产品姓名</span></td>
				<td><input type="text" id="prodNameAdd" name="prodName2"
					/></td>
			</tr>
			<tr class="active" align="center">
				<td align="right"><span>数量</span></td>
				<td><input type="text" id="quantity" name="quantity"
					 /></td>
			</tr>
			<tr class="active" align="center">
				<td align="right"><span>交货日期</span></td>
				<td><input type="date" id="deliveryDateStr" name="deliveryDateStr"
					 /></td>
			</tr>
			<tr align="center">
				<td align="right"><span>备注</span></td>
				<td><textarea id="description" name="description" cols="20"
						rows="5">${order.description}</textarea></td>
			</tr>
		</table>
</form>

	
	<%-- <div class="modal fade" id="selectModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" >
			<div class="modal-content">
				 <jsp:include page="${basePath}/ProductServlet?action=orderSelect" flush="true" />
			</div>
			<!-- /.modal-content -->
		<!-- /.modal-dialog -->
	</div>  --%>
	<!-- /.modal -->
