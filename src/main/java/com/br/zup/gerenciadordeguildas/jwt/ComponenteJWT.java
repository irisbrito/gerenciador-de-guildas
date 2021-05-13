package com.br.zup.gerenciadordeguildas.jwt;

import com.br.zup.gerenciadordeguildas.exceptions.TokenInvalidoException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ComponenteJWT {

    private int millissegundos = 300000;

    private String segredo = "Secret";

    public String gerarToken(String username){
        Date vencimento = new Date(System.currentTimeMillis()+millissegundos);

        String token = Jwts.builder().setSubject(username)
                .setExpiration(vencimento).signWith(SignatureAlgorithm.HS512, segredo.getBytes()).compact();

        return token;
    }

    public Claims getClaims(String token){
        try{
            Claims claims = Jwts.parser().setSigningKey(segredo.getBytes()).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception error ){
            throw new TokenInvalidoException();
        }
    }

    public String getUsername(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    public boolean isTokenValid(String token){
        try {
            Claims claims = getClaims(token);
            Date vencimento = claims.getExpiration();
            Date agora = new Date(System.currentTimeMillis());
            String username = getUsername(token);

            if(username != null && vencimento != null && agora.before(vencimento)){
                return true;
            }

            return false;

        } catch (TokenInvalidoException error){
            return false;
        }
    }
}
