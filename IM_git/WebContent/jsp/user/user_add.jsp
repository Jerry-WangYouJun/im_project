<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglibs.jsp" %>
<form id="userForm" action="${basePath}/UserServlet?action=insert" method="POST">
    <input type="hidden" id="userId" name="userId" value="${product.id}"/>
    <input type="hidden" id="act" name="act"/>
    <table class="table">
        <tr class="active" align="center">
            <td align="right" width="15%">用户编号</td>
            <td align="center" width="30%"><input type="text" id="userCode" name="userCode"
                                                  value="${product.prodCode}"/></td>
            <td align="right" width="15%">登陆账号</td>
            <td ><input type="text" id="loginName" name="loginName"
                       value="${product.prodName}"/></td>
        </tr>
        <tr align="center">
            <td align="right"><span>用户姓名</span></td>
            <td><input type="text" id="userName" name="userName"
                       value="${product.prodSpec}"/></td>
            <td align="right"><span>性别</span></td>
            <td><input type="text" id="sex" name="sex"
                       value="${product.prodType}"/></td>
        </tr>
        <tr class="active" align="center">
            <td align="right"><span>生日</span></td>
            <td><input type="text" id="birthday" name="birthday"
                       value="${product.price}"/></td>
            <td align="right"><span>身份证号</span></td>
            <td><input type="text" id="idNum" name="idNum"
                       value="${product.price}"/></td>
        </tr>
        <tr align="center">
            <td align="right"><span>民族</span></td>
            <td><input type="text" id="nation" name="nation"
                       value="${product.price}"/></td>
            <td align="right"><span>是否已婚</span></td>
            <td><input type="text" id="married" name="married"
                       value="${product.price}"/></td>
        </tr>
        <tr class="active" align="center">
            <td align="right"><span>雇佣日期</span></td>
            <td><input type="text" id="hireDate" name="hireDate"
                       value="${product.price}"/></td>
            <td align="right"><span>岗位</span></td>
            <td><input type="text" id="postion" name="postion"
                       value="${product.price}"/></td>
        </tr>
        <tr align="center">
            <td align="right"><span>工种</span></td>
            <td><input type="text" id="job" name="job"
                       value="${product.price}"/></td>
            <td align="right"><span>电子邮件</span></td>
            <td><input type="text" id="email" name="email"
                       value="${product.price}"/></td>
        </tr>
        <tr class="active" align="center">
            <td align="right"><span>部门名称</span></td>
            <td><input type="text" id="deptName" name="deptName"
                       value="${product.price}"/></td>
            <td align="right"><span>手机</span></td>
            <td><input type="text" id="mobile" name="mobile"
                       value="${product.price}"/></td>
        </tr>
        <tr >
            <td align="right"><span>备注</span></td>
            <td colspan="3">
                <textarea style="margin-left:16px;" id="description" name="description" cols="62" rows="5">${product.description}</textarea>
            </td>
        </tr>
    </table>

</form>
