package com.br.zup.gerenciadordeguildas.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class FiltroDeAutenticacao extends UsernamePasswordAuthenticationFilter {

    private ComponenteJWT componenteJWT;
    private AuthenticationManager authenticationManager;

    public FiltroDeAutenticacao(ComponenteJWT componenteJWT, AuthenticationManager authenticationManager) {
        this.componenteJWT = componenteJWT;
        this.authenticationManager = authenticationManager;
    }
}
