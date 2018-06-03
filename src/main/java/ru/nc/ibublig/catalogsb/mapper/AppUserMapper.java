package ru.nc.ibublig.catalogsb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import ru.nc.ibublig.catalogsb.model.AppUser;

public class AppUserMapper implements RowMapper<AppUser> {

    public static final String BASE_SQL //
            = "SELECT u.user_id, u.user_name, u.encrypted_password, u.enabled FROM App_User u ";

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long userId = rs.getLong("user_id");
        String userName = rs.getString("user_name");
        String encryptedPassword = rs.getString("encrypted_password");
        boolean enabled = rs.getBoolean("enabled");

        return new AppUser(userId, userName, encryptedPassword, enabled);
    }

}
