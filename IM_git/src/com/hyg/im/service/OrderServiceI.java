package com.hyg.im.service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hyg.im.beans.Order;
import com.hyg.im.common.Pagination;

public interface OrderServiceI {

	public int getTotal() throws SQLException;

	public void insertOrder(Order p) throws SQLException;

	public void updateOrder(Order p)  throws SQLException;

	public void delete(HttpServletRequest request)throws SQLException;

	public List<Order> getOrderList(Pagination pagination,
			HttpServletRequest request) throws SQLException, ParseException;
}
