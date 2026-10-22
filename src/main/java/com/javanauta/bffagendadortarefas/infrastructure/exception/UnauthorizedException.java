package com.javanauta.bffagendadortarefas.infrastructure.exception;

/**
 * Exceção para representar erros de acesso relacioandos a
 * à autenticação do usuário.
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String mensagem){
        super(mensagem);
    }

    public UnauthorizedException(String mensagem , Throwable trowable){
        super(mensagem,trowable);
    }


}
