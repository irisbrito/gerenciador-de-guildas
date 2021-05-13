package com.br.zup.gerenciadordeguildas.usuario.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
    private String email;
    private String senha;
}
