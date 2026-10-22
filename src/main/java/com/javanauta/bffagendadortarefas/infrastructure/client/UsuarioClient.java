package com.javanauta.bffagendadortarefas.infrastructure.client;
import com.javanauta.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.javanauta.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

// Cliente responsável pela comunicação com o serviço de usuários.
@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    // Busca os dados de um usuário através do e-mail informado.
    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail(
            @RequestParam("email") String email,
            @RequestHeader("Authorization") String token
    );

    // Cadastra um novo usuário no serviço de usuários.
    @PostMapping
    UsuarioDTOResponse salvaUsuario(
            @RequestBody UsuarioDTORequest usuarioDTOResponse
    );

    // Realiza o login do usuário e retorna o token JWT.
    @PostMapping("/login")
    String login(
            @RequestBody LoginRequestDTO usuarioDTOResponse
    );

    // Exclui um usuário pelo e-mail informado.
    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(
            @PathVariable String email,
            @RequestHeader("Authorization") String token
    );

    // Atualiza os dados do usuário autenticado.
    @PutMapping
    UsuarioDTOResponse atualizaDadoUsuario(
            @RequestBody UsuarioDTORequest dto,
            @RequestHeader("Authorization") String token
    );

    // Atualiza um endereço existente pelo ID.
    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(
            @RequestBody EnderecoDTORequest dto,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    );

    // Atualiza um telefone existente pelo ID.
    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(
            @RequestBody TelefoneDTORequest dto,
            @RequestParam("id") Long id,
            @RequestHeader("Authorization") String token
    );

    // Cadastra um novo endereço para o usuário.
    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(
            @RequestBody EnderecoDTORequest dto,
            @RequestHeader("Authorization") String token
    );

    // Cadastra um novo telefone para o usuário.
    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(
            @RequestBody TelefoneDTORequest dto,
            @RequestHeader("Authorization") String token
    );
}