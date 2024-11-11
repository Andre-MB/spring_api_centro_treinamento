package com.syntaxsquad.centro_treinamento.model.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync

public class AsyncConfig {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);  // Número de threads iniciais
        executor.setMaxPoolSize(20);   // Número máximo de threads
        executor.setQueueCapacity(100); // Capacidade da fila
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }
}
