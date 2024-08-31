package com.eurosgateway.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Configuration
public class PaymentConfig {
    @Bean
    public SseEmitter getEmitter()
    {
        return new SseEmitter();
    }
}
