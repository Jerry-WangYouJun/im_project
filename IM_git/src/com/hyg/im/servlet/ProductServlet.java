package com.hyg.im.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hyg.im.beans.Product;
import com.hyg.im.common.BaseServlet;
import com.hyg.im.common.StringUtils;
import com.hyg.im.service.ProductServiceI;
import com.hyg.im.service.ProductServiceImpl;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
			request.setAttribute("action",action);
			if ("insert".equals(action)) {
				insert(request, response);
			} else if("update".equals(action)){
				update(request, response);
			} else if ("delete".equals(action)){
				delete(request, response);
			} else if("orderSelect".equals(action)) {
				searchSelectList(request,response);
			}
			else {
				search(request,response);
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


	private void search(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		List<Product> list = searchData(request, response);
		String action = request.getParameter("action");
		request.setAttribute("pagination", pagination);
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("jsp/product/product.jsp");
		if(StringUtils.isEmpty(action)){
			rd.forward(request, response);
		}else{
			rd.include(request, response);
		}
		
	}


	private void insert(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProductServiceI service = new ProductServiceImpl();
		
		String prodCode = request.getParameter("prodCode");
		String prodName = request.getParameter("prodName");
		String prodSpec = request.getParameter("prodSpec");
		String prodType = request.getParameter("prodType");
		String price = request.getParameter("price");
		String description = request.getParameter("description");

		Product p = new Product();
		if (StringUtils.isNotEmpty(prodCode)) {
			p.setProdCode(prodCode);
		}
		if (StringUtils.isNotEmpty(prodName)) {                  
			p.setProdName(prodName);
		}
		if (StringUtils.isNotEmpty(prodSpec)) {
			p.setProdSpec(prodSpec);
		}
		if (StringUtils.isNotEmpty(prodType)) {
			p.setProdType(prodType);
		}
		if (StringUtils.isNotEmpty(price)) {
			p.setPrice(price);
		}
		if (StringUtils.isNotEmpty(description)) {
			p.setDescription(description);
		}
		try {
			service.insertProduct(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		search(request, response);
	}
	
	private void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProductServiceI service = new ProductServiceImpl();
		String id = request.getParameter("proid");
		String prodCode = request.getParameter("prodCode");
		String prodName = request.getParameter("prodName");
		String prodSpec = request.getParameter("prodSpec");
		String prodType = request.getParameter("prodType");
		String price = request.getParameter("price");
		String description = request.getParameter("description");

		Product p = new Product();
		if(StringUtils.isNotEmpty(id)){
			p.setId(id);
		}
		if (StringUtils.isNotEmpty(prodCode)) {
			p.setProdCode(prodCode);
		}
		if (StringUtils.isNotEmpty(prodName)) {
			p.setProdName(prodName);
		}
		if (StringUtils.isNotEmpty(prodSpec)) {
			p.setProdSpec(prodSpec);
		}
		if (StringUtils.isNotEmpty(prodType)) {
			p.setProdType(prodType);
		}
		if (StringUtils.isNotEmpty(price)) {
			p.setPrice(price);
		}
		if (StringUtils.isNotEmpty(description)) {
			p.setDescription(description);
		}
		try {
				service.updateProduct(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		search(request, response);
	}
	
	private void delete(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ServletException, IOException {
		ProductServiceI service = new ProductServiceImpl();
		service.delete(request);
		search(request, response);
	}
	private void searchSelectList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		ProductServiceI service = new ProductServiceImpl();
		pagination.setPageSize(99999);
		List<Product> list =  service.getProductList(pagination , request);
		request.setAttribute("list", list);
		RequestDispatcher rd = request
				.getRequestDispatcher("jsp/product/product.jsp");
		rd.forward(request, response);
	}
	
	private List<Product> searchData(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProductServiceI service = new ProductServiceImpl();
		List<Product> list = null;
		try {
			pagination.setTotal(service.getTotal());
			initPagination(pagination, request);
			list = service.getProductList(pagination , request);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
