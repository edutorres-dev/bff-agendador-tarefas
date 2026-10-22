package com.javanauta.bffagendadortarefas.infrastructure.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * Configura o esquema de autenticação JWT utilizado na documentação do Swagger.
 * Permite informar um token Bearer para testar endpoints protegidos pela API.
 */

@SecurityScheme(name = SecurityConfig.SECURITY_SCHEME, type = SecuritySchemeType.HTTP,
bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfig {

    // Nome que identifica o esquema de autenticação na documentação do Swagger.
    public static final String SECURITY_SCHEME="bearerAuth";
}
