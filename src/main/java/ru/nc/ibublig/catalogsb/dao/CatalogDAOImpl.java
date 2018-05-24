package ru.nc.ibublig.catalogsb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CatalogDAOImpl extends JdbcDaoSupport implements CatalogDAO {
    @Autowired
    public CatalogDAOImpl(@Qualifier("dataSource") DataSource dataSource){
        this.setDataSource(dataSource);
    }
}
