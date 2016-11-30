package com.hyg.im.service;

import com.hyg.im.beans.UserBean;
import com.hyg.im.dao.UserDaoI;
import com.hyg.im.dao.UserDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */
public class UserServiceImpl implements UserServiceI {

    UserDaoI userDaoI = null;

    @Override
    public int add(UserBean userBean) {
        int result = 0;
        String sql = "insert into sys_user " +
                "(UserCode,LoginName,UserName,Sex,Birthday,IDNum," +
                "Nation,Married,HireDate,Postion,Job,Email.DeptName,Mobile,Description) values " +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        List paramsList = new ArrayList();
        paramsList.add(userBean.getUserCode());
        paramsList.add(userBean.getLoginName());
        paramsList.add(userBean.getUserName());
        paramsList.add(userBean.getSex());
        paramsList.add(userBean.getBirthday());
        paramsList.add(userBean.getIdNum());
        paramsList.add(userBean.getNation());
        paramsList.add(userBean.getMarried());
        paramsList.add(userBean.getHireDate());
        paramsList.add(userBean.getPosition());
        paramsList.add(userBean.getJob());
        paramsList.add(userBean.getEmail());
        paramsList.add(userBean.getDeptName());
        paramsList.add(userBean.getMobile());
        paramsList.add(userBean.getDescription());
        userDaoI = new UserDaoImpl();
        try {
            result = userDaoI.executeSQLUpdate(sql, paramsList.toArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
