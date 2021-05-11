package com.br.zup.gerenciadordeguildas.dtos.entrada.ata;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
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

    public Ata converterDTOParaModel(Integer id, Guilda guilda){
        Ata ata = new Ata();
        ata.setId(id);
        ata.setAssuntos(this.assuntos);
        ata.setGuilda(guilda);

        return ata;
    }
}
