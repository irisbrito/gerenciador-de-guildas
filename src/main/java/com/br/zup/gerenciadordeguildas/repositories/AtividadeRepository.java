package com.br.zup.gerenciadordeguildas.repositories;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AtividadeRepository extends CrudRepository<Atividade, Integer> {

    Optional<Atividade> findByGuilda(String guilda);
}
