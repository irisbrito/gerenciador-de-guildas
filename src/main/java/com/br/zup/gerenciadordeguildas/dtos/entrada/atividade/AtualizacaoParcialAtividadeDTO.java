package com.br.zup.gerenciadordeguildas.dtos.entrada.atividade;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.enums.Status;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizacaoParcialAtividadeDTO {

    @NotNull
    @Size(min = 2, max = 100, message = "Por favor digite um nome válido")
    private String nome;

    @NotNull
    @Size(max = 500, message = "Por favor, digite no máximo 500 caracteres")
    private String descricao;

    @NotNull
    @Size(min = 2, max = 100, message = "Por favor digite um nome válido")
    private List<String> responsaveis;

    @NotNull
    private Status status;

    public Atividade converterDTOParaModel(int id){
        Atividade atividade = new Atividade();
        atividade.setId(id);
        atividade.setNome(this.nome);
        atividade.setDescricao(this.descricao);
        atividade.setResponsaveis(this.responsaveis);
        atividade.setStatus(this.status);

        return atividade;
    }
}
