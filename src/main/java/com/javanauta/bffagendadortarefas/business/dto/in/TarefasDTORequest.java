package com.javanauta.bffagendadortarefas.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javanauta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTORequest {


    // Nome da tarefa cadastrada.
    private String nomeTarefa;

    // Descrição detalhada da tarefa.
    private String descricao;


    // Define o formato da data ao enviar ou receber o JSON.
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;


}

