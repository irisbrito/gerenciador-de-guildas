package com.br.zup.gerenciadordeguildas.repositories;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import org.springframework.data.repository.CrudRepository;

public interface AtaRepository extends CrudRepository<Ata, Integer> {
    Iterable<Ata> findAllByGuilda(String guilda);
}
