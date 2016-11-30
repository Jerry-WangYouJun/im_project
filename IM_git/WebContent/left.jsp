<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="css/styles.css" type="text/css" rel="stylesheet" />
<script src="resources/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#product").bind("click", function() {
			window.parent.mainFrame.location.href = "${basePath}/ProductServlet";
		});
		$("#order").bind("click", function() {
			window.parent.mainFrame.location.href  = "${basePath}/OrderServlet";
		});
	});
</script>
<!-- 引用 css/styles.css -->
       <div class="main-left">
         <ul class="menu_list">
            <li data-label="Home" id="main" class="on">
                <p></p><span>主页</span>
            </li>
            <li data-label="Publ" id="order">
                <p></p><span>生产订单</span>
            </li>
            <li data-label="Publ" id="u11">
                <p></p><span>车间计划</span>
            </li>
            <li data-label="Publ" id="u13">
                <p></p><span>进度跟踪</span>
            </li>
            <li data-label="Publ" id="u27">
                 <p></p><span>生产看板</span>
            </li>
            <li data-label="Publ">
                <p></p><span>基础信息</span>
                <div class="menu_list_main">
                    <ul class="menu_list_in">
                        <li id="u17">
                            <p></p><span><a href="${basePath}/UserServlet">用户管理</a></span>
                        </li>
                        <li data-label="Events" id="u19">
                            <p></p><span>设备管理</span>
                        </li>
                        <li data-label="Events" id="u21">
                            <p></p><span>客户管理</span>
                        </li>
                        <li data-label="Events" id="product">
                            <p></p><span>产品管理</span>
                        </li>
                        <li data-label="Events" id="u25">
                            <p></p><span>工序管理</span>
                        </li>
                        <li data-label="Events" id="u29">
                            <p></p><span>车间管理</span>
                        </li>
                    </ul>
                </div>
            </li>

          </ul>
         </div>

         <!-- <div class="main-right">
           <div><img src="images/index/blue_hand.png"/> </div>
         </div> -->

