package com.org.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.org.products.entity")
@EnableJpaRepositories("com.org.products.repository")
@ComponentScan(basePackages = {"com.org.products"})
public class ProductsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }
}
