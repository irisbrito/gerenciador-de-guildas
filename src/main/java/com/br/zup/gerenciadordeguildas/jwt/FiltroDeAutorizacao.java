package com.br.zup.gerenciadordeguildas.jwt;

import com.br.zup.gerenciadordeguildas.exceptions.TokenInvalidoException;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

public class FiltroDeAutorizacao extends BasicAuthenticationFilter {

    private ComponenteJWT componenteJWT;
    private UserDetailsService userDetailsService;

    public FiltroDeAutorizacao(AuthenticationManager authenticationManager, ComponenteJWT componenteJWT, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.componenteJWT = componenteJWT;
        this.userDetailsService = userDetailsService;
    }

    private UsernamePasswordAuthenticationToken getAutenticacao(HttpServletRequest request, String token){

        if(!componenteJWT.isTokenValid(token)){
            throw new TokenInvalidoException();
        }

        Claims claims = componenteJWT.getClaims(token);
        UserDetails user = userDetailsService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

    }

}
