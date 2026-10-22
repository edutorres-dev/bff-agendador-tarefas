package com.javanauta.bffagendadortarefas.controller;

import com.javanauta.bffagendadortarefas.business.TarefasService;
import com.javanauta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.javanauta.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Expõe os endpoints de gerenciamento de tarefas do BFF.
 * Recebe as requisições HTTP e delega as operações ao TarefasService.
 */

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {


    private final TarefasService tarefasService;

    // Endpoint responsável por cadastrar uma nova tarefa.
    @PostMapping
    @Operation(
            summary = "Salvar Tarefas do usuário",
            description = "Cria uma nova tarefa"
    )
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(
            @RequestBody TarefasDTORequest dto,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia o DTO e o token para o Service realizar o cadastro.
        return ResponseEntity.ok(
                tarefasService.gravarTarefa(token, dto)
        );
    }


    // Busca as tarefas agendadas dentro do período informado.
    @GetMapping("/eventos")
    @Operation(
            summary = "Busca tarefas por período ",
            description = " Busca tarefas"
    )
    @ApiResponse(responseCode = "200", description = " tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401" , description = "Usuário não autorizado" )

    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(

            // Recebe a data e hora inicial no formato ISO.
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataInicial,

            // Recebe a data e hora final no formato ISO.
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataFinal ,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia as datas para o Service e retorna as tarefas encontradas.
        return ResponseEntity.ok(
                tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal,token)
        );
    }


    // Busca todas as tarefas vinculadas ao usuário autenticado.
    @GetMapping
    @Operation(
            summary = "Busca lista de tarefas por email de usuário",
            description = "Busca tarefas cadastradas por usuário"
    )
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403" , description = "Email não encontrado" )
    @ApiResponse(responseCode = "401" , description = "Usuário não autorizado" )


    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia o token para o Service identificar o usuário e buscar suas tarefas.
        List<TarefasDTOResponse> tarefas = tarefasService.buscaTarefasPorEmail(token);

        return ResponseEntity.ok(tarefas);
    }



    // Endpoint responsável por excluir uma tarefa pelo ID.
    @DeleteMapping
    @Operation(
            summary = "Deleta tarefas por Id ",
            description = "Deleta tarefas cadastradas por ID"
    )
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403" , description = "tarefa id não encontrada" )
    @ApiResponse(responseCode = "401" , description = "Usuário não autorizado" )


    public ResponseEntity<Void> deletaTarefaPorId(
            @RequestParam("id") String id ,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia o ID para o Service realizar a exclusão.
        tarefasService.deleteTarefaPorId(id,token);

        return ResponseEntity.ok().build();
    }


    // Endpoint responsável por alterar o status da notificação.
    @PatchMapping
    @Operation(
            summary = "Altera status de tarefas",
            description = "Altera status das tarefas cadastradas"
    )
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403" , description = "tarefa id não encontrada" )
    @ApiResponse(responseCode = "401" , description = "Usuário não autorizado" )

    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestParam("id") String id,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia o status e o ID para o Service realizar a alteração.
        return ResponseEntity.ok(
                tarefasService.alteraStatus(status, id,token)
        );
    }


    // Endpoint responsável por atualizar os dados da tarefa.
    @PutMapping
    @Operation(
            summary = "Altera dados de tarefas",
            description = "Altera dados das tarefas cadastradas"
    )
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "403" , description = "tarefa id não encontrada" )
    @ApiResponse(responseCode = "401" , description = "Usuário não autorizado" )


    public ResponseEntity<TarefasDTOResponse> updateTarefas(
            @RequestBody TarefasDTORequest dto,
            @RequestParam("id") String id ,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia o DTO e o ID para o Service atualizar a tarefa.
        return ResponseEntity.ok(
                tarefasService.updateTarefas(dto, id,token)
        );
    }





}

