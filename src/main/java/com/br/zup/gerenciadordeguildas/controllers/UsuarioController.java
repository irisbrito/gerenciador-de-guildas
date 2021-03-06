package com.br.zup.gerenciadordeguildas.controllers;

import com.br.zup.gerenciadordeguildas.entities.Usuario;
import com.br.zup.gerenciadordeguildas.services.UsuarioService;
import com.br.zup.gerenciadordeguildas.dtos.entrada.usuario.CadastroUsuarioDTO;
import com.br.zup.gerenciadordeguildas.dtos.entrada.usuario.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios/")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuario(@RequestBody @Valid CadastroUsuarioDTO usuario){
        System.out.println(usuario.getEmail());
        usuarioService.cadastrarNovoUsuario(usuario.converterDTOParaModel());
    }

    @GetMapping
    public List<UsuarioDTO> retornarTodosOsUsuarios(){
        List<Usuario> usuarios = usuarioService.buscarTodosOsUsuarios();
        return UsuarioDTO.converterListaDeModelParaDTO(usuarios);
    }

}
