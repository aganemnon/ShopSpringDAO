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
public class UserDAOImpl extends JdbcDaoSupport implements UserDAO {

    @Autowired
    public UserDAOImpl(@Qualifier("dataSource") DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public void saveUser(User user) {

        Object[] params = new Object[]{
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getBalance(),
                user.isActive(),
        };

        //Insert user
        String sql = "INSERT INTO users (username, email, password, balance, active)"
                + " VALUES (?,?,?,?,?)";
        this.getJdbcTemplate().update(sql, params);

    }

    @Override
    public void delete(Long userID) {
        String sql = "DELETE FROM users WHERE id=?";
        this.getJdbcTemplate().update(sql, userID);
    }

    @Override
    public List<User> list() {
        String sql = UserMapper.BASE_SQL;

        Object[] params = new Object[]{};
        UserMapper userMapper = new UserMapper();
        List<User> userList = this.getJdbcTemplate().query(sql, params, userMapper);

        return userList;
    }

    @Override
    public User findByUsername(String username) {
        String sql = UserMapper.BASE_SQL + "WHERE username=?";
        System.out.println(sql);
        Object[] params = new Object[]{username};
        UserMapper mapper = new UserMapper();
        try {
            User user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User get(Long userID) {
        String sql = UserMapper.BASE_SQL + "WHERE id=" + userID;

        Object[] params = new Object[]{userID};
        UserMapper mapper = new UserMapper();
        try {
            User user = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private User setUser(ResultSet resultSet) {
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
