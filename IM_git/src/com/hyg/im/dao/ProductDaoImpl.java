package com.hyg.im.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hyg.im.beans.Product;
import com.hyg.im.common.DBUtil;
import com.hyg.im.common.Pagination;

public class ProductDaoImpl extends DBUtil implements ProductDaoI {

	protected final String SQL_INSERT = "INSERT INTO SYS_PRODUCT ( ProdCode, ProdName, ProdSpec, ProdType, Price, Description) VALUES ( ?, ?, ?, ?, ?, ?)";

	protected final String SQL_UPDATE = "UPDATE SYS_PRODUCT SET ProdCode = ?, ProdName= ? , ProdSpec= ? , ProdType= ?, Price = ?, Description = ? WHERE ID = ?";

	protected final String SQL_DELETE = "DELETE FROM SYS_PRODUCT where ID = ?";

	@Override
	public List<Product> queryList(Pagination pagination, String sql,
			Object[] array) throws SQLException {
		List<Map<String, Object>> resultSetList = queryByPage(pagination, sql,
				array);
		List<Product> list = new ArrayList<>();
		for (Map<String, Object> map : resultSetList) {
			Product product = new Product();
			product.setId(map.get("ID").toString());
			product.setProdCode(map.get("PRODCODE").toString());
			product.setProdName(map.get("PRODNAME").toString());
			product.setProdSpec(map.get("PRODSPEC").toString());
			product.setProdType(map.get("PRODTYPE").toString());
			product.setPrice(map.get("PRICE").toString());
			product.setDescription(map.get("DESCRIPTION").toString());
			list.add(product);
		}
		return list;

	}

	public int getTotal(String whereSql, Object[] array) throws SQLException {

		// final String SQL = " select count(1) from  sys_product  where 1=1 " ;

		int count = this.getResultTotal(whereSql, array);
		return count;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void insert(Product p) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		params.add(p.getProdCode());
		params.add(p.getProdName());
		params.add(p.getProdSpec());
		params.add(p.getProdType());
		params.add(p.getPrice());
		params.add(p.getDescription());
		// 注意executeUpdate传参数params的时候 不能用List集合类型的，需要将该List集合转成数组
		int rows = this.executeUpdate(SQL_INSERT, params.toArray());
	}

	@Override
	public void update(Product p) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		params.add(p.getProdCode());
		params.add(p.getProdName());
		params.add(p.getProdSpec());
		params.add(p.getProdType());
		params.add(p.getPrice());
		params.add(p.getDescription());
		params.add(p.getId());
		// 注意executeUpdate传参数params的时候 不能用List集合类型的，需要将该List集合转成数组
		int rows = this.executeUpdate(SQL_UPDATE, params.toArray());

	}

	@Override
	public void delete(String id) throws SQLException {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		params.add(id);
		// 注意executeUpdate传参数params的时候 不能用List集合类型的，需要将该List集合转成数组
		int rows = this.executeUpdate(SQL_DELETE, params.toArray());
	}

}
