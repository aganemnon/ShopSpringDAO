package ru.nc.ibublig.catalogsb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.nc.ibublig.catalogsb.mapper.UserMapper;
import ru.nc.ibublig.catalogsb.model.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO{

    @Autowired
    public UserDAOImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public void saveOrUpdate(User user) {
        if (user.getId() > 0) {
            //Update user
            String sql = "UPDATE user SET login=?, email=?, password=?, balance=?, active=? WHERE userID=?";
            this.getJdbcTemplate().update(sql, user.getUsername(), user.getEmail(), user.getPassword(), user.getBalance(), user.isActive(), user.getId());
        } else {
            //Insert user
            String sql = "INSERT INTO user (login, email, password, balance, active)"
                    + " VALUES (?,?,?,?,?)";
            this.getJdbcTemplate().update(sql,user.getUsername(),user.getEmail(),user.getPassword(),user.getBalance(), user.isActive());
        }
    }

    @Override
    public void delete(Long userID) {
        String sql = "DELETE FROM user WHERE userID=?";
        this.getJdbcTemplate().update(sql,userID);
    }

    @Override
    public List<User> list() {
        String sql = UserMapper.BASE_SQL;

        Object[] params = new Object[]{};
        UserMapper userMapper = new UserMapper();
        List<User> userList = this.getJdbcTemplate().query(sql,params,userMapper);

        return userList;
    }

    @Override
    public User findByUsername(String username) {
        String sql = UserMapper.BASE_SQL + "WHERE u.username=" + username;

        Object[] params = new Object[]{ username };
        UserMapper mapper = new UserMapper();
        try {
            User user = this.getJdbcTemplate().queryForObject(sql,params,mapper);
            return user;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public User get(Long userID) {
        String sql = UserMapper.BASE_SQL + "WHERE u.id=" + userID;

        Object[] params = new Object[]{ userID };
        UserMapper mapper = new UserMapper();
        try {
            User user = this.getJdbcTemplate().queryForObject(sql,params,mapper);
            return user;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    private User setUser(ResultSet resultSet){
        User user = new User();
        try {
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setBalance(resultSet.getLong("balance"));
            user.setActive(resultSet.getBoolean("active"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
