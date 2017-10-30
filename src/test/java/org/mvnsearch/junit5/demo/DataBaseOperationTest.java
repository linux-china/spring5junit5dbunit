package org.mvnsearch.junit5.demo;


import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mvnsearch.junit5.DBUnitExtension;
import org.mvnsearch.junit5.DataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.FileOutputStream;

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

    //@Test
    public void generateDTD() throws Exception {
        IDatabaseConnection connection = new DatabaseConnection(jdbcTemplate.getDataSource().getConnection());
        FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("lazada-address-server/src/test/resource/dataset/lazada-address.dtd"));
    }
}
