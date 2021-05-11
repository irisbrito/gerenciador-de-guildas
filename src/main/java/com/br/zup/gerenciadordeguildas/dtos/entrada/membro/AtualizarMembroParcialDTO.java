package com.br.zup.gerenciadordeguildas.dtos.entrada.membro;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import lombok.*;

import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarParcialMembroDTO {

    private String nome;
    @Email(message = "Email inválido")
    private String email;

    private String zenity;

    private List<Guilda> guildas;
}