package com.br.zup.gerenciadordeguildas.repositories;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GuildaRepository extends CrudRepository<Guilda, Integer> {
    Optional<Guilda> findByNome(String nome);
}
