package com.br.zup.gerenciadordeguildas.repositories;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface AtividadeRepository extends CrudRepository<Atividade, Integer> {
    Optional<Atividade> findByGuilda(String guilda);
    //@Query("select a from Atividade a join responsaveis r on a.id = am.atividade_id where am.membro_id = :responsavelId")

    List<Atividade> findByResponsaveis_id(Integer responsavelId);
}
