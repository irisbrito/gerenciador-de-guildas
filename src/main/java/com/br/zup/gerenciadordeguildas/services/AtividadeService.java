package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.repositories.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public Atividade cadastrarAtividade(Atividade atividade){
       return atividadeRepository.save(atividade);
    }

    public void deletarAtividade(Integer id){
        if(atividadeRepository.existsById(id)){
            atividadeRepository.deleteById(id);
        }

        throw new RuntimeException("Atividade não encontrada");
    }
}
