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

// Cliente responsável por realizar chamadas para o serviço de usuários.
@FeignClient(name = "usuario",url="${usuario.url}")
public interface UsuarioClient {

    // Busca os dados de um usuário através do e-mail informado.
    @GetMapping
    UsuarioDTOResponse buscarUsuarioPorEmail (@RequestParam("email") String email ,
                                              @RequestHeader("Authorization") String token);




    /*
     * ==========================================================
     * CADASTRO DE USUÁRIO
     * ==========================================================
     *
     *
     */
    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTOResponse);


    /*
     * ==================
     * LOGIN
     * ==================
     * */

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO usuarioDTOResponse);


    /*
     * =======================
     * DELETAR USUÁRIO
     * =======================
     *
     * */

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable String email , @RequestHeader("Authorization") String token);

    /*
     * =======================
     * ATUALIZAR DADOS
     * =======================
     */

    // Endpoint responsável por atualizar os dados do usuário autenticado.
    @PutMapping
    UsuarioDTOResponse atualizaDadoUsuario(@RequestBody UsuarioDTORequest dto,
                                           @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto ,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);


    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto , @RequestParam("id") Long id ,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto ,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto ,
                                         @RequestHeader("Authorization") String token);


}
