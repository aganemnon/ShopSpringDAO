package ru.nc.ibublig.catalogsb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.nc.ibublig.catalogsb.mapper.CategoryMapper;
import ru.nc.ibublig.catalogsb.model.Category;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CategoryDAO extends JdbcDaoSupport {
    @Autowired
    public CategoryDAO(@Qualifier("dataSource") DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Long getIdByName(String nameCategory) {
        String sql = CategoryMapper.BASE_SQL + " WHERE name=?";
        Object[] params = new Object[]{nameCategory};
        CategoryMapper mapper = new CategoryMapper();
        Category category = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return category.getId();
    }

    public List<Category> list() {
        String sql = CategoryMapper.BASE_SQL;
        Object[] params = new Object[]{};
        CategoryMapper categoryMapper = new CategoryMapper();
        List<Category> categories = this.getJdbcTemplate().query(sql, params, categoryMapper);
        return categories;
    }
    public Category getCategoryById(Long id){
        String sql = CategoryMapper.BASE_SQL + " WHERE id = ?";
        Object[] params = new Object[]{
                id
        };
        CategoryMapper categoryMapper = new CategoryMapper();
        Category category = this.getJdbcTemplate().queryForObject(sql,params,categoryMapper);
        return category;
    }

    public void setNameById(Long categoryId, String name) {
        String sql = "UPDATE category SET name = ? WHERE id = ?";
        Object[] params = new Object[]{
                name,
                categoryId
        };

        this.getJdbcTemplate().update(sql, params);
    }

    public void addCategory(String name) {
        String sql = "INSERT INTO category (name)"
                + " VALUES (?)";
        Object[] params = new Object[]{name};
        this.getJdbcTemplate().update(sql,params);
    }
}
