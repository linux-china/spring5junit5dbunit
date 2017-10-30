package org.mvnsearch.junit5.demo;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mvnsearch.junit5.DataSet;
import org.mvnsearch.junit5.DBUnitExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * Unit test for database
 *
 * @author linux_china
 */
@SpringJUnitConfig(classes = {DatabaseConfiguration.class})
@ExtendWith(DBUnitExtension.class)
@DataSet("/people-dataset.xml")
public class DataBaseOperationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testA() {
        jdbcTemplate.query("SELECT id, nick FROM people", (resultSet, i) -> {
            return resultSet.getString("nick");
        });
    }
}
