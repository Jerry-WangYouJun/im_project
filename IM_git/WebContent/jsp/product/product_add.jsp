<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<form id="insertProdForm" action="${basePath}/ProductServlet?action=insert"
	method="POST" >
	<input type="hidden" id="proid" name="proid" value="${product.id}" />
	<input type="hidden" id="act" name="act"  />
		<table class="table" >
			<tr class="active" >
				<td align="right"  >产品编号</td>
				<td  align="center"  ><input type="text" id="prodCode" name="prodCode"
					value="${product.prodCode}" /></td>
			</tr>
			<tr align="center">
				<td align="right">产品名称</td>
				<td><input type="text" id="prodName" name="prodName"
					value="${product.prodName}" /></td>
			</tr>
			<tr class="active" align="center">
				<td align="right"><span>产品规格</span></td>
				<td><input type="text" id="prodSpec" name="prodSpec"
					value="${product.prodSpec}" /></td>
			</tr>
			<tr align="center">
				<td align="right"><span>产品型号</span></td>
				<td><input type="text" id="prodType" name="prodType"
					value="${product.prodType}" /></td>
			</tr>
			<tr class="active" align="center">
				<td align="right"><span>单价</span></td>
				<td><input type="text" id="price" name="price"
					value="${product.price}" /></td>
			</tr>
			<tr align="center">
				<td align="right"><span>备注</span></td>
				<td><textarea id="description" name="description" cols="20"
						rows="5">${product.description}</textarea></td>
			</tr>
		</table>
	
</form>
