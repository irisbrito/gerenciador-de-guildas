package com.br.zup.gerenciadordeguildas.dtos.entrada.membro;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroMembroDTO {

    private Integer id;
    @NotNull
    private String nome;
    @Email(message = "Email inválido!")
    private String email;
    @NotNull
    private String zenity;
    @NotNull
    private boolean representante;
    @NotNull
    private String guilda;

    public Membro converterDTOparaEntity(Guilda guilda) {
        Membro membro = new Membro();
        membro.setNome(this.nome);
        membro.setEmail(this.email);
        membro.setZenity(this.zenity);
        membro.setRepresentante(this.representante);
        membro.setGuilda(guilda);

        return membro;
    }
}
