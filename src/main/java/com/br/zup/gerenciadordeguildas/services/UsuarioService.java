package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Usuario;
import com.br.zup.gerenciadordeguildas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Usuario salvarUsuario(Usuario usuario){
        Usuario objUsuario = usuarioRepository.save(usuario);
        System.out.println(objUsuario.getId());
        return objUsuario;
    }

    public Usuario cadastrarNovoUsuario(Usuario usuario){
        String senhaEncoder = encoder.encode(usuario.getSenha());

        usuario.setSenha(senhaEncoder);

        return salvarUsuario(usuario);
    }

    public List<Usuario> buscarTodosOsUsuarios(){
        return (List<Usuario>) usuarioRepository.findAll();
    }

}
