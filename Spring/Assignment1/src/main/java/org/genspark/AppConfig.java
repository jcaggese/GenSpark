package org.genspark;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "org.genspark")
public class AppConfig {
    @Bean
    @Primary
    public Student getStudent() {
        return new Student();
    }

    @Bean
    public Address getAddress() {
        return new Address();
    }
}
