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
}