package ru.nc.ibublig.catalogsb.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.nc.ibublig.catalogsb.model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {

    public static final String BASE_SQL
            = "SELECT * FROM item ";

    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Long cost = resultSet.getLong("cost");
        String image = resultSet.getString("image");
        Long categoryId = resultSet.getLong("category_id");

        return new Item(id, name, description, cost, image, categoryId);
    }
}
