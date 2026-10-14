
package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.javanauta.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.javanauta.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    // Client responsável por comunicar com o serviço de usuários.
    private final UsuarioClient client;

    // Cadastra um novo usuário através do serviço de usuários.
    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {

        return client.salvaUsuario(usuarioDTO);
    }


    // Realiza o login do usuário através do serviço de usuários.
    public String loginUsuario(LoginRequestDTO dto) {

        return client.login(dto);
    }


    // Busca um usuário pelo e-mail utilizando o token JWT.
    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {

        return client.buscarUsuarioPorEmail(email, token);
    }


    // Exclui um usuário pelo e-mail utilizando o token JWT.
    public void deletaUsuarioPorEmail(String email, String token) {

        client.deletaUsuarioPorEmail(email, token);
    }


    // Atualiza os dados do usuário autenticado.
    public UsuarioDTOResponse atualizarDadosUsuario(String token, UsuarioDTORequest dto) {

        return client.atualizaDadoUsuario(dto, token);
    }


    // Atualiza um endereço existente pelo ID.
    public EnderecoDTOResponse atualizaEndereco(
            Long idEndereco,
            EnderecoDTORequest enderecoDTO,
            String token) {

        return client.atualizaEndereco(
                enderecoDTO,
                idEndereco,
                token
        );
    }


    // Atualiza um telefone existente pelo ID.
    public TelefoneDTOResponse atualizaTelefone(
            Long idTelefone,
            TelefoneDTORequest dto,
            String token) {

        return client.atualizaTelefone(
                dto,
                idTelefone,
                token
        );
    }


    // Cadastra um novo endereço para o usuário autenticado.
    public EnderecoDTOResponse cadastraEndereco(
            String token,
            EnderecoDTORequest dto) {

        return client.cadastraEndereco(dto, token);
    }


    // Cadastra um novo telefone para o usuário autenticado.
    public TelefoneDTOResponse cadastraTelefone(
            String token,
            TelefoneDTORequest dto) {

        return client.cadastraTelefone(dto, token);
    }
}

