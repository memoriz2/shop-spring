package com.shop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    // 14분마다 실행 (15분 전에 ping)
    @Scheduled(fixedRate = 14 * 60 * 1000)
    public void pingServer() {
        try {
            // 자신의 서버 URL로 ping
            String serverUrl = "https://shop-spring.onrender.com/api/products";
            restTemplate.getForObject(serverUrl, String.class);
            System.out.println("Ping sent successfully at: " + java.time.LocalDateTime.now());
        } catch (Exception e) {
            System.out.println("Ping failed: " + e.getMessage());
        }
    }
} 