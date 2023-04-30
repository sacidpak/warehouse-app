package com.sacidpak.depot;

import com.sacidpak.common.repository.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@EnableTransactionManagement
@EnableFeignClients(basePackages = "com.sacidpak.clients")
@SpringBootApplication(
        scanBasePackages = {
                "com.sacidpak.depot",
                "com.sacidpak.common.exception",
                "com.sacidpak.queueconfig",
        }
)
@EnableJpaRepositories(basePackages = {"com.sacidpak.depot.repository"}, repositoryBaseClass = BaseRepositoryImpl.class)
public class DepotApplication {
    public static void main(String[] args) {
        SpringApplication.run(DepotApplication.class,args);
    }
}