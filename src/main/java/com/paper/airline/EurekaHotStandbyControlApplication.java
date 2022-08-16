package com.paper.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEurekaClient
@EnableScheduling
@EnableEurekaServer
@SpringBootApplication
public class EurekaHotStandbyControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaHotStandbyControlApplication.class, args);
    }

}
