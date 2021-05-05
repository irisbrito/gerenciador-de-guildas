package com.br.zup.gerenciadordeguildas.dtos.entrada.guilda;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarGuildaDTO {

    @NotNull
    private String nome;
    @NotNull
    @NotEmpty
    private String descricao;
    @NotNull
    private String objetivos;
    @NotNull
    private String linkDoChat;
    @NotNull
    private List<Membro> membros;
    private List<Atividade> atividades;
    private List<Ata> atas;

    public Guilda converterDTOParaModel(Integer id){
        Guilda guilda = new Guilda();
        guilda.setId(id);
        guilda.setNome(this.nome);
        guilda.setDescricao(this.descricao);
        guilda.setObjetivos(this.objetivos);
        guilda.setLinkDoChat(this.linkDoChat);
        guilda.setMembros(this.membros);
        guilda.setAtividades(this.atividades);
        guilda.setAtas(this.atas);

        return guilda;
    }
}
