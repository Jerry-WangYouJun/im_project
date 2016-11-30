package com.hyg.im.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.hyg.im.beans.Order;
import com.hyg.im.common.Pagination;

public interface OrderDaoI {
	public List<Order> queryList(Pagination pagination, String sql,
			Object[] array) throws SQLException, ParseException;

	public int getTotal(String whereSql, Object[] objects) throws SQLException;

	public void insert(Order p) throws SQLException;

	public void update(Order p) throws SQLException;

	public void delete(String id) throws SQLException;
}
