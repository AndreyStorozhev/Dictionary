package example.old;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "example")
public class SpringConfig {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("console");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    @Bean("map")
    public Map<String, String> map() {
        return new HashMap<>();
    }
}
