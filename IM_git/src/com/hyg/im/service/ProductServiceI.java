package com.hyg.im.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hyg.im.beans.Product;
import com.hyg.im.common.Pagination;

public interface ProductServiceI {
	 public List<Product> getProductList(Pagination pagination , HttpServletRequest request ) throws SQLException;

	public int getTotal() throws SQLException;

	public void insertProduct(Product p) throws SQLException;

	public void updateProduct(Product p)  throws SQLException;

	public void delete(HttpServletRequest request)throws SQLException;
}
