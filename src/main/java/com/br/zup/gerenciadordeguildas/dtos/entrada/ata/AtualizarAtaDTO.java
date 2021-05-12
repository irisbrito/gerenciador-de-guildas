package com.br.zup.gerenciadordeguildas.dtos.entrada.ata;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarAtaDTO {

    private String pauta;
    private String assuntos;
    private String guilda;

    public Ata converterDTOParaEntity(Integer id, Guilda guilda) {
        Ata ata = new Ata();
        ata.setId(id);
        ata.setPauta(this.pauta);
        ata.setAssuntos(this.assuntos);
        ata.setGuilda(guilda);

        return ata;
    }
}
