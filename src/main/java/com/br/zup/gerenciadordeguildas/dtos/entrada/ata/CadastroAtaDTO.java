package com.br.zup.gerenciadordeguildas.dtos.entrada.ata;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroAtaDTO {

    private Integer id;
    private Instant data;

    @NotNull
    @Size(max = 30, message = "Digitar no máximo 30 caracteres.")
    private String pauta;

    @NotNull
    @Size(max = 500, message = "Digitar no máximo 500 caracteres.")
    private String assuntos;

    private String guilda;

    public Ata converterDTOParaEntity(Guilda guilda){
        Ata ata = new Ata();
        ata.setId(this.id);
        ata.setData(Instant.now());
        ata.setAssuntos(this.assuntos);
        ata.setPauta(this.pauta);
        ata.setGuilda(guilda);

        return ata;
    }
}
