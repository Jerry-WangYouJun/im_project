package com.hyg.im.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hyg.im.beans.Product;
import com.hyg.im.common.Pagination;
import com.hyg.im.common.StringUtils;
import com.hyg.im.dao.ProductDaoI;
import com.hyg.im.dao.ProductDaoImpl;

public class ProductServiceImpl implements ProductServiceI {
	
	String selectSQL = "select * from  sys_product where 1=1 ";
	String countSQL = "  from sys_product" ;
	ProductDaoI productDao = new ProductDaoImpl();
	
	@Override
	public List<Product> getProductList(Pagination pagination , HttpServletRequest request) throws SQLException {
		return productDao.queryList(pagination , selectSQL  , new Object[]{});
	}

	@Override
	public int getTotal() throws SQLException {
		return productDao.getTotal( countSQL ,  new Object[]{});
	}

	@Override
	public void insertProduct(Product p) throws SQLException {
		productDao.insert(p);
		
	}

	@Override
	public void updateProduct(Product p) throws SQLException {
		productDao.update(p);
		
	}

	@Override
	public void delete(HttpServletRequest request) throws SQLException {
		String id = request.getParameter("id");
		productDao.delete(id);
		
	}

}
