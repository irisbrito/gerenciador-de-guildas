package com.br.zup.gerenciadordeguildas.dtos.entrada.usuario;

import com.br.zup.gerenciadordeguildas.entities.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CadastroUsuarioDTO {

    @Size(min = 10, max = 100, message = "")
    private String nomeCompleto;
    @Email(message = "E-mail inválido!")
    private String email;
    @Size(min = 6, max = 10, message = "Senha inválida, deve conter no mín 6 caracteres e no máx 10")
    private String senha;

    public Usuario converterDTOParaModel(){
        Usuario usuario = new Usuario();
        usuario.setEmail(this.email);
        usuario.setNomeCompleto(this.nomeCompleto);
        usuario.setSenha(this.senha);

        return usuario;
    }
}
