package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Atividade;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
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

    public Iterable<Atividade> buscarAtividades(){
        return atividadeRepository.findAll();
    }

    public Atividade buscarAtividadePelaGuilda(String guilda) {
        return atividadeRepository.findByGuilda(guilda).get();
    }

    public Atividade atualizarAtividade(Atividade atividade){
        if(atividadeRepository.existsById(atividade.getId())){
            Atividade objAtividade = cadastrarAtividade(atividade);
            return atividade;
        }

        throw new RecursoNaoEncontradoException("Atividade", atividade.getId());
    }

    public void deletarAtividade(Integer id){
        if(atividadeRepository.existsById(id)){
            atividadeRepository.deleteById(id);
        }

        throw new RecursoNaoEncontradoException("Atividade", id);
    }
}
