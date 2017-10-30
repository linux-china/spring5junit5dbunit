package org.mvnsearch.junit5.demo;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mvnsearch.junit5.DBUnitExtension;
import org.mvnsearch.junit5.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Unit test for database
 *
 * @author linux_china
 */
@SpringJUnitConfig(classes = {DatabaseConfiguration.class})
@ExtendWith(DBUnitExtension.class)
@DataSet("/user-dataset.xml")
public class DataBaseOperationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testA() {
        jdbcTemplate.query("SELECT * FROM people", new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                System.out.println(resultSet.getString("nick"));
                return null;
            }
        });
    }
}
