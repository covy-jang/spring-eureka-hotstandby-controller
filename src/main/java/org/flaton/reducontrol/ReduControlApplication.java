package org.flaton.reducontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEurekaClient
@EnableScheduling
@EnableEurekaServer
@SpringBootApplication
public class ReduControlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReduControlApplication.class, args);
    }

}
