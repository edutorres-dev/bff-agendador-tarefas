package com.javanauta.bffagendadortarefas.infrastructure.client;

import com.javanauta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

// Cliente responsável por realizar chamadas para o serviço de usuários.
@FeignClient(name = "agendador-tarefas",url="${agendador-tarefas.url}")
public interface TarefasClient {

    // Endpoint responsável por cadastrar uma nova tarefa.
    @PostMapping
    TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest dto,
                                     @RequestHeader("Authorization") String token);



    // Busca as tarefas agendadas dentro do período informado.
    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo(

            // Recebe a data e hora inicial no formato ISO.
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataInicial,

            // Recebe a data e hora final no formato ISO.
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataFinal ,

            @RequestHeader("Authorization") String token);





    // Busca todas as tarefas vinculadas ao usuário autenticado.
    @GetMapping
   List<TarefasDTOResponse> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);



    // Endpoint responsável por excluir uma tarefa pelo ID.
    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id ,
                           @RequestHeader("Authorization") String token);



    // Endpoint responsável por alterar o status da notificação.
    @PatchMapping
    TarefasDTOResponse alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                               @RequestParam("id") String id , @RequestHeader("Authorization") String token);


    // Endpoint responsável por atualizar os dados da tarefa.
    @PutMapping
    TarefasDTOResponse updateTarefas(@RequestBody TarefasDTORequest dto,
                                     @RequestParam("id") String id ,
                                     @RequestHeader("Authorization") String token);



}
