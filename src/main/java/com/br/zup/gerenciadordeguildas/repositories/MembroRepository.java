package com.br.zup.gerenciadordeguildas.repositories;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.entities.Membro;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MembroRepository extends CrudRepository<Membro,Integer> {
    Optional<Membro> findByNome(String nome);
    Optional<Membro> findByEmail(String email);
    boolean existsByEmail(String email);
    Iterable<Membro> findAllByRepresentanteIsAndGuilda(boolean booleano, Guilda guilda);
}
