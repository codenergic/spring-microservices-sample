package org.codenergic.sample.forex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ForexServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForexServiceApplication.class, args);
    }
}
