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
        String sql = "SELECT * FROM category WHERE name=?";
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
}
