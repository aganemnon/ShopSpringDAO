package ru.nc.ibublig.catalogsb.dao;

import java.util.List;
import java.util.Map;

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

    public void updateRole(Long userId, Long role_id) {
        Object[] params = new Object[]{
                role_id,
                userId
        };

        //Update user
        String sql = "UPDATE user_role"
                + " SET role_id = ?"
                + " WHERE user_id = ?";
        this.getJdbcTemplate().update(sql, params);
    }

    public Map<String,String> getListUserRole() {
        String sql = "SELECT user_id, role_id FROM user_role";
        String test = this.getJdbcTemplate().queryForObject(sql,String.class);
        System.out.println(test);
        Map<String, String> roles = null;
        return roles;
    }
}
