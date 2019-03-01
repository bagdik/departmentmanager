package by.departmentmanager.api.configuration;

import org.apache.log4j.Logger;
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

    final static Logger logger = Logger.getLogger(DatabaseConfigH2.class);
    @Bean
    public DataSource dataSource() {
        logger.info("Bean DataSource has been created");
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setScriptEncoding("UTF-8")
                .addScript("sql/schema.sql")
                .addScript("sql/data.sql")
                .build();
    }

}
