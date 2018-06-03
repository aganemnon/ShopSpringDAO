package ru.nc.ibublig.catalogsb.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AppRoleDAO extends JdbcDaoSupport {

    @Autowired
    public AppRoleDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public String getRoleNames(Long userId) {
        String sql = "Select r.Role_Name " //
                + " from User_Role ur, App_Role r " //
                + " where ur.Role_Id = r.Role_Id and ur.User_Id = ? ";

        Object[] params = new Object[] { userId };

        String role = this.getJdbcTemplate().queryForObject(sql, params, String.class);

        return role;
    }

    public void setRole(Long userId, Long roleUser){
        Object[] params = new Object[]{
                userId,
                roleUser,
        };

        //Insert user
        String sql = "INSERT INTO user_role (user_id, role_id)"
                + " VALUES (?,?)";
        this.getJdbcTemplate().update(sql, params);
    }

}
