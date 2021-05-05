package com.br.zup.gerenciadordeguildas.repositories;

import com.br.zup.gerenciadordeguildas.entities.Membro;
import org.springframework.data.repository.CrudRepository;

public interface RepresentanteRepository extends CrudRepository<Membro,Integer> {
    Iterable<Membro> findAllByGuildasNome(String nome);
}
