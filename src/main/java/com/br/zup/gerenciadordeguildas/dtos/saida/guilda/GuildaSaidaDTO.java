package com.br.zup.gerenciadordeguildas.dtos.saida.guilda;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuildaSaidaDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private String objetivos;
    private String linkDoChat;

    public static GuildaSaidaDTO converterGuildaParaDTO(Guilda guilda){
        return new GuildaSaidaDTO(guilda.getId(), guilda.getNome(), guilda.getDescricao(),
                guilda.getObjetivos(), guilda.getLinkDoChat());
    }
}
