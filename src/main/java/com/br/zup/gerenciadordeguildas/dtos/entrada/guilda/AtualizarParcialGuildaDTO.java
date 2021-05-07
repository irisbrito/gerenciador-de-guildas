package com.br.zup.gerenciadordeguildas.dtos.entrada.guilda;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarParcialGuildaDTO {

    private String nome;
    private String descricao;
    private String objetivos;
    private String linkDoChat;

    public Guilda converterDTOParaModel(Integer id){
        Guilda guilda = new Guilda();
        guilda.setId(id);
        guilda.setNome(this.nome);
        guilda.setDescricao(this.descricao);
        guilda.setObjetivos(this.objetivos);
        guilda.setLinkDoChat(this.linkDoChat);
        guilda.setMembros(guilda.getMembros());
        guilda.setAtividades(guilda.getAtividades());
        guilda.setAtas(guilda.getAtas());

        return guilda;
    }
}
