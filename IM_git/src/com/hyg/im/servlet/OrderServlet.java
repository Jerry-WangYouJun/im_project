package com.hyg.im.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.hyg.im.beans.Order;
import com.hyg.im.common.BaseServlet;
import com.hyg.im.common.StringUtils;
import com.hyg.im.service.OrderServiceI;
import com.hyg.im.service.OrderServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			String action = request.getParameter("action");
			request.setAttribute("action", action);
			if ("insert".equals(action)) {
				insert(request, response);
			} else if ("update".equals(action)) {
				 update(request, response);
			} else if ("delete".equals(action)) {
				// delete(request, response);	
			} else {
				search(request, response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
		OrderServiceI service = new OrderServiceImpl();
		Order order = new Order();
		Integer id = Integer.valueOf(request.getParameter("orderid"));
		if(id > 0){
			order.setId(id);
		}
		Integer prodId = Integer.valueOf(request.getParameter("prodid")); 
		if(prodId > 0){
			order.setProductId(prodId);
		}
		//TODO  客户模块完成后添加从页面获取客户id相关代码
		Integer customerId = 1 ;
		if(customerId > 0 ){
			order.setCustomId(customerId); 
		}
		Integer quantity = Integer.valueOf(request.getParameter("quantity")==null ? "0":request.getParameter("quantity"));
		if(quantity > 0 ){
			order.setQuantity(quantity);
		}
		String deliveryDateStr = request.getParameter("deliveryDateStr");
		if(StringUtils.isNotEmpty(deliveryDateStr)){
			order.setDeliveryDateStr(deliveryDateStr);
		}
		String description  = request.getParameter("description");
		if(StringUtils.isNotEmpty(description)){
			order.setDescription(description);
		}
		String orderCode = request.getParameter("orderCode");
		if(StringUtils.isNotEmpty(orderCode)){
			order.setOrderCode(orderCode);
		}
		service.insertOrder(order);
		search(request, response);
		
	}

	private void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		List<Order> list = searchData(request, response);
		request.setAttribute("pagination", pagination);
		request.setAttribute("orderList", list);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/order.jsp");
		rd.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ParseException {
		OrderServiceI service = new OrderServiceImpl();
		Order order = new Order();
		Integer prodId = Integer.valueOf(request.getParameter("prodid")); 
		if(prodId > 0){
			order.setProductId(prodId);
		}
		//TODO  客户模块完成后添加从页面获取客户id相关代码
		Integer customerId = 1 ;
		if(customerId > 0 ){
			order.setCustomId(customerId); 
		}
		Integer quantity = Integer.valueOf(request.getParameter("quantity")==null ? "0":request.getParameter("quantity"));
		if(quantity > 0 ){
			order.setQuantity(quantity);
		}
		String deliveryDateStr = request.getParameter("deliveryDateStr");
		if(StringUtils.isNotEmpty(deliveryDateStr)){
			order.setDeliveryDateStr(deliveryDateStr);
		}
		String description  = request.getParameter("description");
		if(StringUtils.isNotEmpty(description)){
			order.setDescription(description);
		}
		String orderCode = request.getParameter("orderCode");
		if(StringUtils.isNotEmpty(orderCode)){
			order.setOrderCode(orderCode);
		}
		service.insertOrder(order);
		search(request, response);
	}
	
	private List<Order> searchData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ParseException {
		OrderServiceI service = new OrderServiceImpl();
		List<Order> list = null;
		try {
			pagination.setTotal(service.getTotal());
			initPagination(pagination, request);
			list = service.getOrderList(pagination , request);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
