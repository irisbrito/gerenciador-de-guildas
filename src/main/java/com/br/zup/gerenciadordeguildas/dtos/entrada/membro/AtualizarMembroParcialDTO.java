package com.br.zup.gerenciadordeguildas.dtos.entrada.membro;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarMembroParcialDTO {

    private String nome;
    @Email(message = "Por favor digite um email válido.")
    private String email;
    private String zenity;
    private Boolean representante;
}