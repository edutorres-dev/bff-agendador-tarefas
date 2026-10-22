package com.javanauta.bffagendadortarefas.infrastructure.client.config;

import com.javanauta.bffagendadortarefas.infrastructure.exception.BusinessException;
import com.javanauta.bffagendadortarefas.infrastructure.exception.ConflictException;
import com.javanauta.bffagendadortarefas.infrastructure.exception.ResourceNotFoundException;
import com.javanauta.bffagendadortarefas.infrastructure.exception.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Interpreta respostas HTTP de erro recebidas pelos clientes Feign
 * ao realizar chamadas para outros microsserviços.
 *
 * Converte os status HTTP em exceções personalizadas do BFF,
 * permitindo que a aplicação trate cada tipo de erro adequadamente.
 */
public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        // Identifica o erro pelo status HTTP retornado pelo microsserviço.
        switch (response.status()) {
            case 409:
                // Indica um conflito, como a tentativa de cadastrar um email existente.
                return new ConflictException("Erro: atributo já existente");

            case 403:
                // Representa uma falha de autorização por falta de permissão.
                return new ResourceNotFoundException("Erro: acesso não permitido");

            case 401:
                // Indica que a autenticação falhou ou não foi aceita.
                return new UnauthorizedException("Erro: usuário não autenticado");

            default:
                // Trata outros status de erro como falhas gerais na comunicação.
                return new BusinessException("Erro ao comunicar com o microsserviço");
        }
    }
}
