package com.br.zup.gerenciadordeguildas.dtos.entrada;

import com.br.zup.gerenciadordeguildas.enums.Status;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeDTO {
    private Integer id;
    @NotBlank
    @Min(value = 2, message = "Por favor digite um nome válido")
    @Max(value = 100, message = "Por favor, digite no máximo 100 caracteres")
    private String nome;
    @NotBlank
    @Max(value = 500, message = "Por favor, digite no máximo 500 caracteres")
    private String descricao;
    @NotBlank
    @Min(value = 2, message = "Por favor digite um nome válido")
    @Max(value = 100, message = "Por favor, digite no máximo 100 caracteres")
    private String responsavel;
    @NotBlank
    @Max(value = 18, message = "Por favor, digite um Status existente")
    private Status status;
}
