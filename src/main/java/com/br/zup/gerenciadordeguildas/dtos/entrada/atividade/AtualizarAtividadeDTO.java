package com.br.zup.gerenciadordeguildas.dtos.entrada.atividade;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.enums.Status;
import lombok.*;

import java.util.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarAtividadeDTO {

    @NotNull
    @Size(min = 2, max = 100, message = "Por favor digite um nome válido")
    private String nome;

    @NotNull
    @Size(max = 500, message = "Por favor, digite no máximo 500 caracteres")
    private String descricao;

    @NotNull
    @Size(min = 2, max = 100, message = "Por favor digite um nome válido")
    private List<Membro> responsaveis;

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
