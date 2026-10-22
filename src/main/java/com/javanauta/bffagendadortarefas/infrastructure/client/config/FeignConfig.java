package com.javanauta.bffagendadortarefas.infrastructure.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configura componentes personalizados utilizados pelo OpenFeign
 * para realizar chamadas HTTP entre os microsserviços.
 */
@Configuration
public class FeignConfig {

    /**
     * Registra o FeignError como um bean do Spring,
     * permitindo sua utilização na configuração do tratamento de erros do Feign.
     */
    @Bean
    public FeignError feignError() {
        return new FeignError();
    }
}
