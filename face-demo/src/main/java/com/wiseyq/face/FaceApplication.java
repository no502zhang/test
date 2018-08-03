package com.wiseyq.face;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.wiseyq.face.service.impl.VisitorConutConsumer;

@SpringBootApplication
public class FaceApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public VisitorConutConsumer visitorConutConsumer() {
        return new VisitorConutConsumer();
    }

    public static void main(String[] args) {
        SpringApplication.run(FaceApplication.class, args);
    }
}
