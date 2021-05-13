package com.br.zup.gerenciadordeguildas.usuario;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);

}
