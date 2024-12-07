package edu.miu.cs544.moe.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "edu.miu.cs544.moe")
public class JavaConfigs {
    @Bean
    public EntityManagerWrapper entityManagerWrapper() {
        return new EntityManagerWrapper("my-pu");
    }
}
