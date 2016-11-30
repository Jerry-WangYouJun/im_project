package com.hyg.im.common;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet {

	public Pagination pagination = new Pagination();

	public void initPagination(Pagination pagination , HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		pagination.setPageSize(StringUtils.isEmpty(pageSize) ? 10 : Integer.valueOf(pageSize) );
		pagination.setPageNo(StringUtils.isEmpty(pageNo) ? 1 : Integer.valueOf(pageNo) );
		if ((pagination.getTotal() % pagination.getPageSize()) == 0) {
			pagination.setPageIndex(pagination.getTotal() / pagination.getPageSize());
		} else {
			pagination.setPageIndex(pagination.getTotal() / pagination.getPageSize() + 1);
		}

	}

}
