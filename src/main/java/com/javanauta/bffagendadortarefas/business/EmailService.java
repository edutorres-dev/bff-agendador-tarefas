package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

/**
 * Serviço responsável por solicitar o envio de e-mails
 * através do serviço de notificações.
 */

public class EmailService {

    // Client responsável por comunicar com o serviço de notificação.
    private final EmailClient emailClient;

    // Solicita ao serviço de notificação o envio do e-mail.
    public void enviaEmail(TarefasDTOResponse dto) {

        // Envia os dados da tarefa através do Feign Client.
        emailClient.enviarEmail(dto);
    }
}
