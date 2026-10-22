package com.javanauta.bffagendadortarefas.infrastructure.exception;

/**
 * Exceção para representar erros relacionados
 * às regras de negócio da aplicação (tentar cancelar uma tarefa já concluída
 * por exemplo )
 *
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String mensagem){
        super(mensagem);
    }

    public BusinessException(String mensagem , Throwable trowable){
        super(mensagem,trowable);
    }


}
