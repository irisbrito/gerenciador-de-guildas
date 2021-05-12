package com.br.zup.gerenciadordeguildas.dtos.entrada.ata;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AtualizarAtaParcialDTO {

    private String pauta;
    private String assuntos;
    private String guilda;

    public Ata converterDTOParaEntity(Integer id) {
        Ata ata = new Ata();
        ata.setId(id);
        ata.setPauta(this.pauta);
        ata.setAssuntos(this.assuntos);

        return ata;
    }
}

