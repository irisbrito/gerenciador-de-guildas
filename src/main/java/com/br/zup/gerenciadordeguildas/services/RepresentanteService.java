package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Representante;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.RepresentanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepresentanteService {

    @Autowired
    private RepresentanteRepository representanteRepository;

    public Representante cadastrarRepresentante(Representante representante) {
        return representanteRepository.save(representante);
    }

    public Iterable<Representante> retornarTodosOsRepresentantes(){
       return representanteRepository.findAll();
    }

    public Iterable<Representante> buscarRepresentantesPeloNomeDaGuilda(String nome){
        return representanteRepository.findAllByGuildasNome(nome);
    }

    public Representante atualizarRepresentante(Representante representante){
        if(representanteRepository.existsById(representante.getId())){
            Representante objRepresentante = representanteRepository.save(representante);
            return representante;
        }

        throw new RecursoNaoEncontradoException("Representante", representante.getId());
    }

    public void deletarRepresentante(Integer id) {
        if(representanteRepository.existsById(id)){
            representanteRepository.deleteById(id);
        }

        throw new RecursoNaoEncontradoException("Representante", id);
    }

}
