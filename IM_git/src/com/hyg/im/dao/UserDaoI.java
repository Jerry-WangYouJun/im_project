package com.hyg.im.dao;

import com.hyg.im.beans.UserBean;

import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by Administrator on 2016/11/29.
 */
public interface UserDaoI {

    public int executeSQLUpdate(String sql,Object...params) throws SQLException;
}
