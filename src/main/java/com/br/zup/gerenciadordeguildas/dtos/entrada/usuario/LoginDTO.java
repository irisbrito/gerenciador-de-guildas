package com.br.zup.gerenciadordeguildas.dtos.entrada.usuario;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
    private String email;
    private String senha;
}
