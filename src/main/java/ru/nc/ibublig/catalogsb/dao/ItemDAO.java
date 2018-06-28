package ru.nc.ibublig.catalogsb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.nc.ibublig.catalogsb.mapper.ItemMapper;
import ru.nc.ibublig.catalogsb.model.Item;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemDAO extends JdbcDaoSupport {

    @Autowired
    public ItemDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Item> list() {
        String sql = ItemMapper.BASE_SQL;
        Object[] params = new Object[]{};
        ItemMapper itemMapper = new ItemMapper();
        List<Item> items = this.getJdbcTemplate().query(sql, params, itemMapper);
        return items;
    }

    public void addItem(Item item) {
        String sql = "INSERT INTO item (name, description, cost, image, category_id)"
                + " VALUES (?,?,?,?,?)";
        Object[] params = new Object[]{
                item.getName(),
                item.getDescription(),
                item.getCost(),
                item.getImage(),
                item.getCategoryId()
        };

        this.getJdbcTemplate().update(sql, params);
    }

    public List<Item> getByIdCategory(Long idCategory){
        String sql = ItemMapper.BASE_SQL + " WHERE category_id = ?";
        Object[] params = new Object[]{
                idCategory
        };
        ItemMapper itemMapper = new ItemMapper();
        List<Item> items = this.getJdbcTemplate().query(sql,params,itemMapper);
        return  items;
    }

    public Item getById(Long itemId) {
        String sql = ItemMapper.BASE_SQL + " WHERE id = ?";
        Object[] params = new Object[]{
                itemId
        };
        ItemMapper itemMapper = new ItemMapper();
        Item item = this.getJdbcTemplate().queryForObject(sql,params,itemMapper);
        return  item;
    }

    public void saveItem(Item item) {
        String sql = "UPDATE item SET name = ?, description = ?, cost = ?, image = ?, category_id = ? WHERE id = ?";
        Object[] params = new Object[]{
                item.getName(),
                item.getDescription(),
                item.getCost(),
                item.getImage(),
                item.getCategoryId(),
                item.getId()
        };

        this.getJdbcTemplate().update(sql, params);
    }

    public void removeById(Long itemId) {
        String sql = "DELETE FROM item WHERE id = ?";
        Object[] params = new Object[]{itemId};
        this.getJdbcTemplate().update(sql,params);
    }

    public void setNewCategoryById(Long categoryId, String newCategory) {
        String sql = "UPDATE item SET category_id = ? WHERE category_id = ?";
        Object[] params = new Object[]{newCategory, categoryId};
        this.getJdbcTemplate().update(sql, params);
    }

    public List<Item> getByName(String name) {
        String sql = "SELECT * FROM item WHERE name LIKE ?";
        Object[] params = new Object[]{"%"+name+"%"};
        ItemMapper itemMapper = new ItemMapper();
        List<Item> items = this.getJdbcTemplate().query(sql, params, itemMapper);
        return items;
    }
}
