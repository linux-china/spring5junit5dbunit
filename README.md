DBUnit for Spring 5 and JUnit 5
===============================

### How to Use
Please add "@ExtendWith(DBUnitExtension.class)" on Class, and @DataSet for class or method :

```
@SpringJUnitConfig(classes = {DatabaseConfiguration.class})
@ExtendWith(DBUnitExtension.class)
@DataSet("/user-dataset.xml")
public class AppTest {
}

```
**The default database operation is CLEAN_INSERT**

### Export DTD
Please add maven-ant-run and execute "maven antrun:run@dbunit" to generate database dtd file.
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-antrun-plugin</artifactId>
    <version>1.8</version>
    <executions>
        <execution>
            <id>dbunit</id>
            <configuration>
                <target>
                    <taskdef name="dbunit" classname="org.dbunit.ant.DbUnitTask"/>
                    <dbunit driver="com.mysql.jdbc.Driver"
                            url="jdbc:mysql://localhost:3306/test"
                            userid="user"
                            password="password">
                        <export dest="test.dtd" format="dtd"/>
                    </dbunit>
                </target>
            </configuration>
            <goals>
                <goal>run</goal>
            </goals>
        </execution>
    </executions>
    <dependencies>
        <dependency>
            <groupId>org.dbunit</groupId>
            <artifactId>dbunit</artifactId>
            <version>2.5.4</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.44</version>
        </dependency>
    </dependencies>
</plugin>
```

Then you can use DTD in your xml file.
```xml
<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE dataset SYSTEM "test.dtd">
<dataset>
    <PEOPLE id="1" nick="linux_china"/>
</dataset>
```


### References

* DBUnit: http://dbunit.sourceforge.net/
* Spring DbUnit: https://github.com/springtestdbunit/spring-test-dbunit
* 
