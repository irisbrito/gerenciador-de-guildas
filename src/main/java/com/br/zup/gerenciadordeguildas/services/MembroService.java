package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Membro;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.RepresentanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepresentanteService {

    @Autowired
    private RepresentanteRepository representanteRepository;

    public Membro cadastrarRepresentante(Membro membro) {
        return representanteRepository.save(membro);
    }

    public Iterable<Membro> retornarTodosOsRepresentantes(){
       return representanteRepository.findAll();
    }

    public Iterable<Membro> buscarRepresentantesPeloNomeDaGuilda(String nome){
        return representanteRepository.findAllByGuildasNome(nome);
    }

    public Membro atualizarRepresentante(Membro membro){
        if(representanteRepository.existsById(membro.getId())){
            Membro objMembro = representanteRepository.save(membro);
            return membro;
        }

        throw new RecursoNaoEncontradoException("Representante", membro.getId());
    }

    public void deletarRepresentante(Integer id) {
        if(representanteRepository.existsById(id)){
            representanteRepository.deleteById(id);
        }

        throw new RecursoNaoEncontradoException("Representante", id);
    }

}
