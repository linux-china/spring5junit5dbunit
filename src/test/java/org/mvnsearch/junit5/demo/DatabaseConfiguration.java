package org.mvnsearch.junit5.demo;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

/**
 * database configuration
 *
 * @author linux_china
 */
@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() throws Exception {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:public;DATABASE_TO_UPPER=false;INIT=CREATE SCHEMA IF NOT EXISTS public");
        ds.setUser("sa");
        ScriptUtils.executeSqlScript(ds.getConnection(), new ClassPathResource("init.sql"));
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}

