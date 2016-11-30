package com.hyg.im.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hyg.im.beans.Order;
import com.hyg.im.common.Pagination;
import com.hyg.im.common.StringUtils;
import com.hyg.im.dao.OrderDaoI;
import com.hyg.im.dao.OrderDaoImpl;

public class OrderServiceImpl implements OrderServiceI {
	
	String selectSQL = "select o.id oid ,c.id cid ,p.id pid ,o.OrderCode ,"
			+ "c.CustomName ,p.ProdCode ,p.ProdName ,o.Quantity ,o.DeliveryDate ,o.Description"
			+ " from  mm_prod_order o join sys_customer c on o.CustomID = c.id"
			+ " join sys_product p on o.productid = p.id  where 1=1 ";
	String countSQL = "  from  mm_prod_order o join sys_customer c on o.CustomID = c.id  join sys_product p on o.productid = p.id  where 1=1 " ;
	OrderDaoI orderDao = new OrderDaoImpl();
	
	public List<Order> getOrderList(Pagination pagination , HttpServletRequest request) throws SQLException, ParseException {
		List<String> params = new ArrayList<String>() ;
		String id = request.getParameter("id");
		if(StringUtils.isNotEmpty(id)){
			selectSQL +=  " and  o.id =  ? " ; 
			params.add(id);
		}
		String customerName = request.getParameter("customerName");
		if(StringUtils.isNotEmpty(customerName)){
			selectSQL +=  " and  c.customname  like ? "; 
			params.add(customerName);
			request.setAttribute("customerName", customerName);
		}
		String prodName = request.getParameter("prodName");
		if(StringUtils.isNotEmpty(prodName)){
			selectSQL +=  " and  p.prodname  like ? "; 
			params.add(prodName);
			request.setAttribute("prodName", prodName);
		}
		String deliveryDateBegin = request.getParameter("deliveryDateBegin");
		if(StringUtils.isNotEmpty(deliveryDateBegin)){
			selectSQL +=  " and  o.deliveryDate  > ? "; 
			params.add(deliveryDateBegin);
			request.setAttribute("deliveryDateBegin", deliveryDateBegin);
		}
		String deliveryDateEnd = request.getParameter("deliveryDateEnd");
		if(StringUtils.isNotEmpty(deliveryDateEnd)){
			selectSQL +=  " and  o.deliveryDate  < ? "; 
			params.add(deliveryDateEnd);
			request.setAttribute("deliveryDateEnd", deliveryDateEnd);
		}
//		System.out.println(selectSQL);
		return orderDao.queryList(pagination , selectSQL  , params.toArray());
	}

	public int getTotal() throws SQLException {
		return orderDao.getTotal( countSQL ,  new Object[]{});
	}

	public void insertOrder(Order p) throws SQLException {
		orderDao.insert(p);
	}

	public void updateOrder(Order p) throws SQLException {
		orderDao.update(p);
		
	}

	public void delete(HttpServletRequest request) throws SQLException {
		String id = request.getParameter("id");
		orderDao.delete(id);
		
	}


}
