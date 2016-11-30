package com.hyg.im.dao;

import java.sql.SQLException;
import java.util.List;

import com.hyg.im.beans.Product;
import com.hyg.im.common.Pagination;

public interface ProductDaoI {

	public List<Product> queryList(Pagination pagination, String sql,
			Object[] array) throws SQLException;

	public int getTotal(String whereSql, Object[] objects) throws SQLException;

	public void insert(Product p) throws SQLException;

	public void update(Product p) throws SQLException;

	public void delete(String id) throws SQLException;

}
