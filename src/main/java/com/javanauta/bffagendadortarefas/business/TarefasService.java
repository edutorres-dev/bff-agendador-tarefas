package com.javanauta.bffagendadortarefas.business;


import com.javanauta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;


    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {
        return tarefasClient.gravarTarefas(dto,token);
    }


    // Busca as tarefas agendadas dentro do período informado.
    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(
            LocalDateTime dataInicial, LocalDateTime dataFinal , String token) {

        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial,dataFinal,token);
    }




    // Busca as tarefas pertencentes ao usuário autenticado.
    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {

        return tarefasClient.buscaTarefasPorEmail(token);
    }



    // Exclui uma tarefa pelo ID informado.
    public void deleteTarefaPorId(String id , String token) {
        tarefasClient.deletaTarefaPorId(id,token);
    }


    // Atualiza o status de notificação de uma tarefa.
    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id , String token) {
        return tarefasClient.alteraStatusNotificacao(status,id,token);
    }


    // Atualiza os dados de uma tarefa pelo ID.
    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id , String token) {
        return tarefasClient.updateTarefas(dto,id,token);
    }







}

