package by.departmentmanager.api.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages = "by.departmentmanager.api")
@Profile("dev")
public class DatabaseConfig {

    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource(){

        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(environment.getRequiredProperty("jdbc.url"));
        ds.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        ds.setUsername(environment.getRequiredProperty("jdbc.username"));
        ds.setPassword(environment.getRequiredProperty("jdbc.password"));

        return ds;
    }
}
