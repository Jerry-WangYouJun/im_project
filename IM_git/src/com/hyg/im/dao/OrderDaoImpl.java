package com.hyg.im.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hyg.im.beans.Order;
import com.hyg.im.common.DBUtil;
import com.hyg.im.common.DateUtils;
import com.hyg.im.common.Pagination;

public class OrderDaoImpl  extends DBUtil implements OrderDaoI {

	protected final String SQL_INSERT = "INSERT INTO mm_prod_order"
			+ " (OrderCode, CustomID, ProductID, Quantity, DeliveryDate, RFIDCode, CreateUserID, CreateDate, Description) "
			+ "  VALUES (?,?,?,?,?,?,?,?,?)";

	protected final String SQL_UPDATE = "UPDATE SYS_Order SET OrderCode = ?, CustomID= ? , ProductID= ?"
			+ " , Quantity= ?, DeliveryDate = ?, RFIDCode = ? ,Description=? WHERE ID = ?";

	protected final String SQL_DELETE = "DELETE FROM SYS_Order where ID = ?";
	
	
	public static void main(String[] args) throws ParseException {
		String dateString = "2012-12-06 ";  
		
System.out.println(DateUtils.toDate("yyyy-MM-dd", dateString));
	}
	
    public static Date parse(String strDate, String pattern)  
            throws ParseException  
    {  
        return  new SimpleDateFormat(  
                pattern).parse(strDate);  
    }
	@Override
	public List<Order> queryList(Pagination pagination, String sql,
			Object[] array) throws SQLException, ParseException {
		List<Map<String, Object>> resultSetList = queryByPage(pagination, sql,
				array);
		List<Order> list = new ArrayList<>();
		for (Map<String, Object> map : resultSetList) {
			Order order = new Order();
			order.setId(Integer.valueOf(map.get("OID").toString()));
			order.getCustomer().setId(Integer.valueOf(map.get("CID").toString()));
			order.getProduct().setId(map.get("PID").toString());
			order.setOrderCode(map.get("ORDERCODE").toString());
			order.getCustomer().setCustomerName(map.get("CUSTOMNAME").toString());
			order.getProduct().setProdCode(map.get("PRODCODE").toString());
			order.getProduct().setProdName(map.get("PRODNAME").toString());
			order.setQuantity(Integer.valueOf(map.get("QUANTITY").toString()));
			order.setDeliveryDateStr(map.get("DELIVERYDATE").toString());
			order.setDescription(map.get("DESCRIPTION").toString());
			list.add(order);
		}
		return list;

	}

	public int getTotal(String whereSql, Object[] array) throws SQLException {

		// final String SQL = " select count(1) from  sys_Order  where 1=1 " ;

		int count = this.getResultTotal(whereSql, array);
		return count;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void insert(Order order) throws SQLException {
		List params = new ArrayList();
		params.add(order.getOrderCode());
		params.add(order.getCustomId());
		params.add(order.getProductId());
		params.add(order.getQuantity());
		params.add(order.getDeliveryDateStr());
		params.add(order.getRFIDCode());
		//TODO 用户session
		params.add(1);
		params.add(new Date());
		params.add(order.getDescription());
		// 注意executeUpdate传参数params的时候 不能用List集合类型的，需要将该List集合转成数组
		int rows = this.executeUpdate(SQL_INSERT, params.toArray());
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void update(Order order) throws SQLException {
		String SQL_UPDATE = "UPDATE SYS_Order SET OrderCode = ?, CustomID= ? , ProductID= ?"
				+ " , Quantity= ?, DeliveryDate = ?, RFIDCode = ? ,Description=? WHERE ID = ?";
		// TODO Auto-generated method stub
		List params = new ArrayList();
		params.add(order.getOrderCode());
		params.add(order.getCustomId());
		params.add(order.getProductId());
		params.add(order.getQuantity());
		params.add(order.getDeliveryDateStr());
		params.add(order.getRFIDCode());
		params.add(order.getDescription());
		params.add(order.getId());
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
