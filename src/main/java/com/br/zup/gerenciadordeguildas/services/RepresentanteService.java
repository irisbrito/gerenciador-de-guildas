package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Representante;
import com.br.zup.gerenciadordeguildas.repositories.RepresentanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Representante atualizarRepresentante(Representante representante){
        if(representanteRepository.existsById(representante.getId())){
            Representante objRepresentante = representanteRepository.save(representante);
            return representante;
        }

        throw new RuntimeException("Representante não encontrado");
    }

    public void deletarRepresentante(Integer id) {
        if(representanteRepository.existsById(id)){
            representanteRepository.deleteById(id);
        }

        throw new RuntimeException("Representante não encontrado");
    }

}
