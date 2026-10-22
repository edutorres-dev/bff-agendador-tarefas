package com.javanauta.bffagendadortarefas.controller;

import com.javanauta.bffagendadortarefas.business.UsuarioService;
import com.javanauta.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.javanauta.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.javanauta.bffagendadortarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro de login e usuários")
public class UsuarioController {

    // Service responsável pelas operações relacionadas aos usuários.
    private final UsuarioService usuarioService;

    // Endpoint responsável por cadastrar um novo usuário.
    @PostMapping
    @Operation(
            summary = "Salvar Usuários",
            description = "Cria um novo usuário"
    )
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(
            @RequestBody UsuarioDTORequest usuarioDTO) {

        // Envia os dados para o Service realizar o cadastro.
        UsuarioDTOResponse usuarioSalvo = usuarioService.salvaUsuario(usuarioDTO);

        return ResponseEntity.ok(usuarioSalvo);
    }

    // Endpoint responsável por realizar o login do usuário.
    @PostMapping("/login")
    @Operation(
            summary = "Login Usuários",
            description = "Login do usuário"
    )
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO) {

        // Envia as credenciais para o Service realizar o login.
        return usuarioService.loginUsuario(usuarioDTO);
    }

    // Endpoint responsável por buscar um usuário pelo e-mail.
    @GetMapping
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    @Operation(
            summary = "Buscar Dados de Usuários por Email",
            description = "Buscar dados do usuário"
    )
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "403", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")

    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPorEmail(
            @RequestParam("email") String email,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia o e-mail e o token para o Service realizar a busca.
        return ResponseEntity.ok(
                usuarioService.buscarUsuarioPorEmail(email, token)
        );
    }

    // Endpoint responsável por excluir um usuário pelo e-mail.
    @DeleteMapping("/{email}")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    @Operation(
            summary = "Deletar Usuários por Email",
            description = "Deleta usuário"
    )
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")

    public ResponseEntity<Void> deletaUsuarioPorEmail(
            @PathVariable String email,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Solicita ao Service a exclusão do usuário.
        usuarioService.deletaUsuarioPorEmail(email, token);

        // Retorna HTTP 200 sem conteúdo no corpo da resposta.
        return ResponseEntity.ok().build();
    }

    // Endpoint responsável por atualizar os dados do usuário autenticado.
    @PutMapping
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    @Operation(
            summary = "Atualizar dados de Usuários",
            description = "Atualizar dados de usuário"
    )
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")

    public ResponseEntity<UsuarioDTOResponse> atualizaDadoUsuario(
            @RequestBody UsuarioDTORequest dto,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia o DTO e o token para o Service realizar a atualização.
        return ResponseEntity.ok(
                usuarioService.atualizarDadosUsuario(token, dto)
        );
    }

    // Endpoint responsável por atualizar um endereço existente.
    @PutMapping("/endereco")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    @Operation(
            summary = "Atualiza Endereço de Usuários",
            description = "Atualiza endereço de usuário"
    )
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")

    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(
            @RequestBody EnderecoDTORequest dto,
            @RequestParam("id") Long id,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia o endereço, ID e token para o Service realizar a atualização.
        return ResponseEntity.ok(
                usuarioService.atualizaEndereco(id, dto, token)
        );
    }

    // Endpoint responsável por atualizar um telefone existente.
    @PutMapping("/telefone")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    @Operation(
            summary = "Atualiza Telefone de Usuários",
            description = "Atualiza telefone de usuário"
    )
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")

    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(
            @RequestBody TelefoneDTORequest dto,
            @RequestParam("id") Long id,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia o telefone, ID e token para o Service realizar a atualização.
        return ResponseEntity.ok(
                usuarioService.atualizaTelefone(id, dto, token)
        );
    }

    // Endpoint responsável por cadastrar um novo endereço.
    @PostMapping("/endereco")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    @Operation(
            summary = "Salva Endereço de Usuários",
            description = "Salva endereço de usuário"
    )
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "403", description = "Usuário não encntrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(
            @RequestBody EnderecoDTORequest dto,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia o endereço e o token para o Service realizar o cadastro.
        return ResponseEntity.ok(
                usuarioService.cadastraEndereco(token, dto)
        );
    }

    // Endpoint responsável por cadastrar um novo telefone.
    @PostMapping("/telefone")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    @Operation(
            summary = "Salva Telefone de Usuários",
            description = "Salva telefone de usuário"
    )
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(
            @RequestBody TelefoneDTORequest dto,
            @RequestHeader(name="Authorization",required=false) String token) {

        // Envia o telefone e o token para o Service realizar o cadastro.
        return ResponseEntity.ok(
                usuarioService.cadastraTelefone(token, dto)
        );
    }
}