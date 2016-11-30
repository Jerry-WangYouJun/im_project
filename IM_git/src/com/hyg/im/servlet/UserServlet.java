package com.hyg.im.servlet;

import com.hyg.im.beans.UserBean;
import com.hyg.im.service.UserServiceI;
import com.hyg.im.service.UserServiceImpl;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/11/24.
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (StringUtils.isNotBlank(action)) {
            switch (action) {
                case "add":
                    add(request, response);
                    break;
                default:
                    search(request, response);
                    break;
            }
        } else {
            search(request, response);
        }

    }

    private void search(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/user/user_list.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        String userCode = request.getParameter("userCode");
        String loginName = request.getParameter("loginName");
        String userName = request.getParameter("userName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String idNum = request.getParameter("idNum");
        String nation = request.getParameter("nation");
        String married = request.getParameter("married");
        String hireDate = request.getParameter("hireDate");
        String postion = request.getParameter("postion");
        String job = request.getParameter("job");
        String email = request.getParameter("email");
        String deptName = request.getParameter("deptName");
        String mobile = request.getParameter("mobile");
        String description = request.getParameter("description");


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        if (StringUtils.isNotBlank(userCode)) {
            UserBean userBean = new UserBean();
            userBean.setUserCode(userCode);
            userBean.setLoginName(loginName);
            userBean.setUserName(userName);
            userBean.setSex(sex);
            try {
                userBean.setBirthday(
                        StringUtils.isNotBlank(birthday) ? simpleDateFormat.parse(birthday) : null);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            userBean.setIdNum(idNum);
            userBean.setNation(nation);
            userBean.setMarried(married);
            try {
                userBean.setHireDate(
                        StringUtils.isNotBlank(hireDate) ? simpleDateFormat.parse(hireDate) : null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            userBean.setPosition(postion);
            userBean.setJob(job);
            userBean.setEmail(email);
            userBean.setDeptName(deptName);
            userBean.setMobile(mobile);
            userBean.setDescription(description);

            UserServiceI userServiceI=new UserServiceImpl();
            userServiceI.add(userBean);


        }


    }


}
