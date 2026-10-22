package com.javanauta.bffagendadortarefas.infrastructure.client;

import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Client responsável pela comunicação com o serviço de notificação.
@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    // Envia os dados da tarefa para o serviço de notificação.
    @PostMapping
    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}
