package com.example.exportfile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ExportFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExportFileApplication.class, args);
    }

}
