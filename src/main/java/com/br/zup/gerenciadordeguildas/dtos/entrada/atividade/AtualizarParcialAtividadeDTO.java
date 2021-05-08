package com.br.zup.gerenciadordeguildas.dtos.entrada.atividade;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.enums.Status;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarParcialAtividadeDTO {


    private String nome;
    private String descricao;
    private List<Membro> responsaveis;
    private Status status;

    private Guilda guilda;

    public Atividade converterDTOParaModel(int id){
        Atividade atividade = new Atividade();
        atividade.setId(id);
        atividade.setNome(this.nome);
        atividade.setDescricao(this.descricao);
        atividade.setResponsaveis(this.responsaveis);
        atividade.setStatus(this.status);
        atividade.setGuilda(this.guilda);

        return atividade;
    }
}
