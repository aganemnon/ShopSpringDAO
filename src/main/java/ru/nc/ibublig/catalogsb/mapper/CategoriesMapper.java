package ru.nc.ibublig.catalogsb.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.nc.ibublig.catalogsb.model.Categories;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriesMapper implements RowMapper<Categories> {
    public static final String BASE_SQL = "SELECT * FROM catalog";

    @Override
    public Categories mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new Categories(id, name);
    }
}
