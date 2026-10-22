package com.javanauta.bffagendadortarefas.business;
import com.javanauta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

/**
 * Serviço responsável por buscar tarefas próximas do horário de execução
 * e enviar e-mails de notificação automaticamente.
 */
public class CronService {

    // Services responsáveis por tarefas, e-mails e usuários.
    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    // E-mail utilizado para realizar o login automático.
    @Value("${usuario.email}")
    private String email;

    // Senha utilizada para realizar o login automático.
    @Value("${usuario.senha}")
    private String senha;

    // Executa automaticamente conforme o horário definido no cron.
    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora() {

        // Realiza o login para obter o token de autenticação.
        String token = login(converterParaRequestDTO());

        log.info("Iniciada a busca de tarefas");

        // Define o início do período de busca como o horário atual.
        LocalDateTime horaAtual = LocalDateTime.now();

        // Define o final do período como uma hora após o horário atual.
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);

        // Busca as tarefas agendadas dentro do período informado.
        List<TarefasDTOResponse> listaTarefas =
                tarefasService.buscaTarefasAgendadasPorPeriodo(
                        horaAtual,
                        horaFutura,
                        token
                );

        log.info("Tarefas encontradas: {}", listaTarefas);

        // Percorre as tarefas encontradas para enviar as notificações.
        listaTarefas.forEach(tarefa -> {

            // Envia o e-mail de notificação da tarefa.
            emailService.enviaEmail(tarefa);

            log.info(
                    "Email enviado para o usuário: {}",
                    tarefa.getEmailUsuario()
            );

            // Atualiza o status da tarefa para NOTIFICADO.
            tarefasService.alteraStatus(
                    StatusNotificacaoEnum.NOTIFICADO,
                    tarefa.getId(),
                    token
            );
        });

        log.info("Finalizada a busca e notificação de tarefas");
    }

    // Realiza o login e retorna o token de autenticação.
    public String login(LoginRequestDTO dto) {
        return usuarioService.loginUsuario(dto);
    }

    // Cria o DTO com as credenciais configuradas na aplicação.
    public LoginRequestDTO converterParaRequestDTO() {

        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
