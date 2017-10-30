package org.mvnsearch.junit5;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * DbUnit extension
 *
 * @author linux_china
 */
public class DBUnitExtension implements BeforeAllCallback, BeforeEachCallback {

    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        Optional<Class<?>> testClass = extensionContext.getTestClass();
        if (testClass.isPresent()) {
            DataSet dataSet = testClass.get().getAnnotation(DataSet.class);
            if (dataSet != null) {
                writeDataSet(SpringExtension.getApplicationContext(extensionContext).getBean(DataSource.class), dataSet);
            }
        }
    }

    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        Optional<Method> testMethod = extensionContext.getTestMethod();
        if (testMethod.isPresent()) {
            DataSet dataSet = testMethod.get().getAnnotation(DataSet.class);
            if (dataSet != null) {
                writeDataSet(SpringExtension.getApplicationContext(extensionContext).getBean(DataSource.class), dataSet);
            }
        }
    }

    private void writeDataSet(DataSource ds, DataSet dataSet) throws Exception {
        IDatabaseConnection connection = new DatabaseConnection(ds.getConnection());
        String[] values = dataSet.value();
        if (values.length > 0) {
            for (String value : values) {
                FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
                FlatXmlDataSet xmlDataSet = builder.build(this.getClass().getResource(value));
                DatabaseOperation.CLEAN_INSERT.execute(connection, xmlDataSet);
            }
        }
    }
}
