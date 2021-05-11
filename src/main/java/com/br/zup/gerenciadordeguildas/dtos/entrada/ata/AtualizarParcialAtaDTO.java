package com.br.zup.gerenciadordeguildas.dtos.entrada.ata;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.entities.Guilda;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarParcialAtaDTO {

    private LocalDate data;
    private String pauta;
    private String assuntos;
    private String guilda;

    public Ata converterDTOParaModel(Integer id, Guilda guilda){
       Ata ata = new Ata();
        ata.setId(id);
        ata.setData(this.data);
        ata.setAssuntos(this.assuntos);
        ata.setGuilda(guilda);

        return ata;
    }
}
