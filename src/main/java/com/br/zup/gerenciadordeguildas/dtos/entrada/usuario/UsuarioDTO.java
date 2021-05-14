package com.br.zup.gerenciadordeguildas.dtos.entrada.usuario;

import com.br.zup.gerenciadordeguildas.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private String email;
    private String nomeCompleto;

    public static UsuarioDTO converterModelParaDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getEmail(), usuario.getNomeCompleto());
        return usuarioDTO;
    }

    public static List<UsuarioDTO> converterListaDeModelParaDTO(List<Usuario> usuarios){
        List<UsuarioDTO> usuarioDTOS = usuarios.stream()
                .map(objeto -> converterModelParaDTO(objeto)).collect(Collectors.toList());

        return usuarioDTOS;
    }
}
