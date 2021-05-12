package com.br.zup.gerenciadordeguildas.config;

import com.br.zup.gerenciadordeguildas.jwt.ComponenteJWT;
import com.br.zup.gerenciadordeguildas.jwt.FiltroDeAutenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private ComponenteJWT componenteJWT;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();

        http.authorizeRequests().antMatchers(HttpMethod.GET, "/atas/**").permitAll()
                .antMatchers("/atividades/**").permitAll()
                .antMatchers("/guildas/").permitAll()
                .anyRequest()
                .authenticated();

        http.addFilter(new FiltroDeAutenticacao(componenteJWT, authenticationManager()));
    }

    @Bean
    CorsConfigurationSource configuracaoDeCors(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
