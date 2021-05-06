package com.br.zup.gerenciadordeguildas.dtos.entrada.membro;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarParcialMembroDTO {


    @Size(min = 2, max = 100, message = "Por favor digite um nome válido")
    private String nome;
    @Email(message = "Email inválido")
    private String email;

    private String zenity;

    private List<Guilda> guildas;

    public Membro converterDTOParaModel(Integer id){
        Membro membro = new Membro();
        membro.setId(id);
        membro.setNome(this.nome);
        membro.setEmail(this.email);
        membro.setZenity(this.zenity);
        membro.setGuildas(this.guildas);

        return membro;
    }
}
