package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Representante;
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

    public void deletarRepresentante(Integer id) {
        representanteRepository.deleteById(id);
    }

    public Representante salvarRepresentante(Representante representante) {
        try{
            Representante obj = representanteRepository.save(representante);
            return representante;
        }catch (Exception error){
            throw new RuntimeException("Representante já cadastrado!");
        }
    }
}
