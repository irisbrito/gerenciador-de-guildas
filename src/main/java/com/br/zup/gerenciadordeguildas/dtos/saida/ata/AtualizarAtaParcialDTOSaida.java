package com.br.zup.gerenciadordeguildas.dtos.saida.ata;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarAtaParcialDTOSaida {

    private Integer id;
    private LocalDate data;
    private String pauta;
    private String assuntos;
    private Guilda guilda;

}
