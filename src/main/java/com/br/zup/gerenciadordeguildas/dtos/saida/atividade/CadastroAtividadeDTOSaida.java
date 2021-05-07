package com.br.zup.gerenciadordeguildas.dtos.saida.atividade;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
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
public class CadastroAtividadeDTOSaida {

    private Integer id;

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

    @NotNull
    private Guilda guilda;

    public static CadastroAtividadeDTOSaida converterEntityParaDTOSaida(Atividade atividade) {
        CadastroAtividadeDTOSaida cadastroAtividadeDTOSaida = new CadastroAtividadeDTOSaida();
        cadastroAtividadeDTOSaida.setId(atividade.getId());
        cadastroAtividadeDTOSaida.setNome(atividade.getNome());
        cadastroAtividadeDTOSaida.setDescricao(atividade.getDescricao());
        cadastroAtividadeDTOSaida.setResponsaveis(atividade.getResponsaveis());
        cadastroAtividadeDTOSaida.setStatus(atividade.getStatus());
        cadastroAtividadeDTOSaida.setGuilda(atividade.getGuilda());


        return cadastroAtividadeDTOSaida;
    }
}
