package com.br.zup.gerenciadordeguildas.jwt;

import com.br.zup.gerenciadordeguildas.usuario.Usuario;
import com.br.zup.gerenciadordeguildas.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioLoginService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if(usuarioOptional.isEmpty()){
            throw new UsernameNotFoundException("Email não cadastrado");
        }

        Usuario usuario = usuarioOptional.get();

        return new UsuarioLogin(usuario.getEmail(), usuario.getSenha());
    }
}
