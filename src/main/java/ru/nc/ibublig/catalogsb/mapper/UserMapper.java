package ru.nc.ibublig.catalogsb.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.nc.ibublig.catalogsb.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    public static final String BASE_SQL
            = "SELECT u.id, u.username, u.email, u.password, u.balance, u.active FROM users u ";

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        Long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        Long balance = resultSet.getLong("balance");
        boolean active = resultSet.getBoolean("active");

        return new User(id,username,password,email,balance,active);
    }
}
