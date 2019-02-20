package by.departmentmanager.api.configuration;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages = "by.departmentmanager.api")
@Profile("live")
public class DatabaseConfigH2 {


    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("sql/schema.sql")
                .addScript("sql/data.sql")
                .build();
    }

}
