package com.br.zup.gerenciadordeguildas.dtos.saida.ata;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroAtaDTOSaida {

    private Integer id;
    private LocalDate data;

    @NotNull
    @Size(max = 30, message = "Digitar no máximo 30 caracteres.")
    private String pauta;

    @NotNull
    @Size(max = 500, message = "Digitar no máximo 500 caracteres.")
    private String assuntos;

    private Guilda guilda;

    public static CadastroAtaDTOSaida converterEntityParaDTOSaida(Ata ata) {
        CadastroAtaDTOSaida cadastroAtaDTOSaida = new CadastroAtaDTOSaida();
        cadastroAtaDTOSaida.setId(ata.getId());
        cadastroAtaDTOSaida.setPauta(ata.getPauta());
        cadastroAtaDTOSaida.setData(ata.getData());
        cadastroAtaDTOSaida.setAssuntos(ata.getAssuntos());
        cadastroAtaDTOSaida.setGuilda(ata.getGuilda());

        return cadastroAtaDTOSaida;
    }
}
