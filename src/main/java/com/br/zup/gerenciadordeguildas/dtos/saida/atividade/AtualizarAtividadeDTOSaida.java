package com.br.zup.gerenciadordeguildas.dtos.saida.atividade;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.enums.Status;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarAtividadeDTOSaida {
    private Integer id;
    private String nome;
    private String descricao;
    private List<String> responsaveis;
    private Status status;
    private Guilda guilda;
}
