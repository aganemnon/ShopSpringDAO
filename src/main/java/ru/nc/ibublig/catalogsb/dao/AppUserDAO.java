package ru.nc.ibublig.catalogsb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nc.ibublig.catalogsb.mapper.AppUserMapper;
import ru.nc.ibublig.catalogsb.model.AppUser;
import ru.nc.ibublig.catalogsb.utils.EncrytedPasswordUtils;

import javax.sql.DataSource;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Repository
@Transactional
public class AppUserDAO extends JdbcDaoSupport {

    @Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public AppUser findUserAccount(String userName) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = AppUserMapper.BASE_SQL + " where u.user_name = ? ";

        Object[] params = new Object[] { userName };
        AppUserMapper mapper = new AppUserMapper();
        try {
            AppUser userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void saveUser(AppUser user){
        Object[] params = new Object[]{
                user.getUserName(),
                user.getEncryptedPassword(),
                user.isEnabled()
        };

        //Insert user
        String sql = "INSERT INTO app_user (user_name, encrypted_password, enabled)"
                + " VALUES (?,?,?)";
        this.getJdbcTemplate().update(sql, params);
    }

    public List<AppUser> listUserAccount() {
        String sql = AppUserMapper.BASE_SQL;

        Object[] params = new Object[]{};
        AppUserMapper userMapper = new AppUserMapper();
        List<AppUser> userList = this.getJdbcTemplate().query(sql, params, userMapper);

        return userList;
    }

    public AppUser findUserById(Long userId) {
        String sql = AppUserMapper.BASE_SQL + "WHERE u.user_id=?";

        Object[] params = new Object[]{userId};
        AppUserMapper mapper = new AppUserMapper();
        try {
            AppUser user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateUser(Long userId, String username, String password) {
        Object[] params = new Object[]{
                username,
                EncrytedPasswordUtils.encryptedPassword(password),
                userId
        };

        //Update user
        String sql = "UPDATE app_user"
                + " SET user_name = ?,encrypted_password = ?"
                + " WHERE user_id = ?";
        this.getJdbcTemplate().update(sql, params);
    }

    public void updateUser(Long userId, String username) {
        Object[] params = new Object[]{
                username,
                userId
        };

        //Update user
        String sql = "UPDATE app_user"
                + " SET user_name = ?"
                + " WHERE user_id = ?";
        this.getJdbcTemplate().update(sql, params);
    }
}
