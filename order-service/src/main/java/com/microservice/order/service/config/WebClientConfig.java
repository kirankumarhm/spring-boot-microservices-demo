package com.microservice.order.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;



@Configuration
public class WebClientConfig {

    //java.net.UnknownHostException: Failed to resolve 'inventory-service' [A(1), AAAA(28)] after 7 queries
    // Add  @LoadBalanced annotation - It will add the client-side load balancing capabilities to the WebClient.Builder
    // Instead of returning WebClient return WebClient.Builder -
    // Whenever we create instance of client using the WebClient.Builder it will automatically create the client side load balancer

    @Bean
    @LoadBalanced
    public WebClient.Builder webClient(){
        return WebClient.builder();
    }

    //    @Bean
//    public WebClient webClient(){
//        return WebClient.builder().build();
//    }
}
