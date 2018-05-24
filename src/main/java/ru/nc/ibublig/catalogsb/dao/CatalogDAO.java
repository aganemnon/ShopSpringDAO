package ru.nc.ibublig.catalogsb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import ru.nc.ibublig.catalogsb.mapper.CategoriesMapper;
import ru.nc.ibublig.catalogsb.model.Categories;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CatalogDAO extends JdbcDaoSupport {
    @Autowired
    public CatalogDAO(@Qualifier("dataSource") DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Long getIdByName(String nameCategory) {
        String sql = "SELECT * FROM catalog WHERE name=?";
        Object[] params = new Object[]{nameCategory};
        CategoriesMapper mapper = new CategoriesMapper();
        Categories categories = this.getJdbcTemplate().queryForObject(sql, params, mapper);
        return categories.getId();
    }

    public List<Categories> list() {
        String sql = CategoriesMapper.BASE_SQL;
        Object[] params = new Object[]{};
        CategoriesMapper categoriesMapper = new CategoriesMapper();
        List<Categories> categories = this.getJdbcTemplate().query(sql, params, categoriesMapper);
        return categories;
    }
}
