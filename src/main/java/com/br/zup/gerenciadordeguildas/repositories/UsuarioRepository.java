package com.br.zup.gerenciadordeguildas.repositories;

import com.br.zup.gerenciadordeguildas.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);

}
