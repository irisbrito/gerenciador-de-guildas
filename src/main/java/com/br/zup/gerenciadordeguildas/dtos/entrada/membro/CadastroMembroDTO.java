package com.br.zup.gerenciadordeguildas.dtos.entrada.membro;

import com.br.zup.gerenciadordeguildas.dtos.saida.membro.MembroDTOSaida;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembroDTOEntrada {

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
    private List<Integer> guildas_id;

//    public MembroDTOSaida converterDTOparaEntity() {
//        Membro membro = new Membro();
//        membro.setNome(this.nome);
//        membro.setEmail(this.email);
//        membro.setZenity(this.zenity);
//        membro.setRepresentante(this.representante);
//
//        return MembroDTOSaida.builder()
//                .nome(this.nome)
//                .build();
//    }
}
