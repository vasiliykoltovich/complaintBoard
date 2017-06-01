package com.epam.bootapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.epam.web", "services", "epam.repository"})
@EnableJpaRepositories(basePackages = "epam.repository")
@EntityScan(basePackages = "epam.beans")

public class BoardApplication {
    private static final Logger logger = LoggerFactory.getLogger(BoardApplication.class);

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(BoardApplication.class);
//    }

    public static void main(String[] args) {
//        System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(BoardApplication.class, args);
        logger.debug("--Application Started--");
    }

}
