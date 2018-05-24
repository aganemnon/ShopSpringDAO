package ru.nc.ibublig.catalogsb.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.nc.ibublig.catalogsb.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    public static final String BASE_SQL = "SELECT * FROM category";

    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new Category(id, name);
    }
}
