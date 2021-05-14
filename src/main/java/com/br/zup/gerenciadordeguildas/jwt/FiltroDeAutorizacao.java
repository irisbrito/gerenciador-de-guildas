package com.br.zup.gerenciadordeguildas.jwt;

import com.br.zup.gerenciadordeguildas.exceptions.TokenInvalidoException;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String autorizacacao = request.getHeader("Authorization");

        if(autorizacacao != null && autorizacacao.startsWith("Token ")){
            try {
                UsernamePasswordAuthenticationToken auth = getAutenticacao(request, autorizacacao.substring(6));  //substring: ignora os 6 primeiros caracteres ("Token ")
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (TokenInvalidoException error){
                System.out.println(error.getMessage());
            }
        }
        chain.doFilter(request, response);
    }
}
