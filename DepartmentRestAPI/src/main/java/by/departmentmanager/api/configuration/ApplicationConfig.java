package by.departmentmanager.api.configuration;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "by.departmentmanager.api")
@EnableWebMvc
public class ApplicationConfig implements WebMvcConfigurer {
    final static Logger logger = Logger.getLogger(ApplicationConfig.class);

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        logger.info("Bean ViewResolver has been created");
        return resolver;
    }

    @Bean
    public RestTemplate template() {
        logger.info("Bean RestTemplate has been created");
        return new RestTemplate();
    }
}
