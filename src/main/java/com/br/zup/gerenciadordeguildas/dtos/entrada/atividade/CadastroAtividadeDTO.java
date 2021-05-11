package com.br.zup.gerenciadordeguildas.dtos.entrada.atividade;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.enums.Status;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroAtividadeDTO {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 100, message = "Por favor digite um nome válido")
    private String nome;

    @NotNull
    @Size(max = 500, message = "Por favor, digite no máximo 500 caracteres")
    private String descricao;

    @NotNull
    private List<String> responsaveis;

    @NotNull
    private Status status;

    @NotNull
    private String guilda;

    public Atividade converterDTOparaEntity(Guilda guilda, List<Membro> membros) {
        Atividade atividade = new Atividade();
        atividade.setNome(this.nome);
        atividade.setDescricao(this.descricao);
        atividade.setResponsaveis(membros);
        atividade.setStatus(this.status);
        atividade.setGuilda(guilda);

        return atividade;
    }
}
