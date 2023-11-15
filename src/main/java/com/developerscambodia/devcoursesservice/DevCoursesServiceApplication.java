package com.developerscambodia.devcoursesservice;

import com.developerscambodia.devcoursesservice.section.SectionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaServer
@RequiredArgsConstructor
public class DevCoursesServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(DevCoursesServiceApplication.class, args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }

    @Bean
    CommandLineRunner runner(SectionRepository sectionRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("TEST  dsdsfggggggggdsfsfg = " + sectionRepository.findByUuid("95a163c9-07b8-4a11-9783-ffc201410d9e"));
            }
        };
    }



}
