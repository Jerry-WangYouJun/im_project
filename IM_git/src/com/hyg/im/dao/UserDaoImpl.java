package com.hyg.im.dao;

import com.hyg.im.beans.UserBean;
import com.hyg.im.common.DBUtil;

import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by Administrator on 2016/11/29.
 */
public class UserDaoImpl extends DBUtil implements UserDaoI {

    @Override
    public int executeSQLUpdate(String sql, Object... params) throws SQLException {
        return super.executeUpdate(sql, params);
    }
}
