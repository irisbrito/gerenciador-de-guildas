package com.br.zup.gerenciadordeguildas.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class FiltroDeAutorizacao extends BasicAuthenticationFilter {

    private ComponenteJWT componenteJWT;
    private UserDetailsService userDetailsService;

    public FiltroDeAutorizacao(AuthenticationManager authenticationManager, ComponenteJWT componenteJWT, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.componenteJWT = componenteJWT;
        this.userDetailsService = userDetailsService;
    }
}
