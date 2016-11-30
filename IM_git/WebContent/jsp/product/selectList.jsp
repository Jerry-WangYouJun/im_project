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

	$(function() {
		$("#prodCode2").val("");
		$("#prodName2").val("");
		$("tr:odd").css("background", "#bbffff");
	});
	
	function selectData(){
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
		$("#prodCode2").val($("#"+id+"_prodCode").html());
		$("#prodName2").val($("#"+id+"_prodName").html());
		$('#selectModal').modal('hide')
	}

</script>
<title>Insert title here</title>
</head>
<body>
	<div >
		<form action="${basePath}/ProductServlet" method="POST" id="prodForm">
			<div >
				<div >
					<div class="main-button">
						<input class="bta" type="button" value="选择" onclick="selectData()">
					</div>

					<div class="main-list"  style="width: 500px; margin-left: 30px ; margin-right: 30px" >
						<div class="main-list-top">
							<table  cellspacing="0" cellpadding="0">
								<thead>
									<tr>
										<td width="50"></td>
										<td width="100">产品编号</td>
										<td width="100">产品名称</td>
										<td width="50">产品规格</td>
										<td width="50">产品型号</td>
										<td width="60">单价</td>
										<td width="100">备注</td>
									</tr>
								</thead>
							</table>
						</div>
						<div class="main-list-cont" stlye="width:">
							<table cellspacing="0" cellpadding="0">
								<tbody>
									<c:forEach var="product" items="${list}">
										<tr>
											<td width="50"><input type="checkbox" id="${product.id}_id" value="${product.id}" ></td>
											<td id="${product.id}_prodCode" width="100">${product.prodCode}</td>
											<td id="${product.id}_prodName" width="100">${product.prodName}</td>
											<td id="${product.id}_prodSpec" width="50">${product.prodSpec}</td>
											<td id="${product.id}_prodType" width="50">${product.prodType}</td>
											<td id="${product.id}_price" width="50">${product.price}</td>
											<td id="${product.id}_description" width="100">${product.description}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>