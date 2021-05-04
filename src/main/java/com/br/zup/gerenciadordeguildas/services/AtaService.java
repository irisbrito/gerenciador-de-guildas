package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.repositories.AtaRepository;
import org.springframework.stereotype.Service;

@Service
public class AtaService {

    private AtaRepository ataRepository;

    public AtaService(AtaRepository ataRepository) {
        this.ataRepository = ataRepository;
    }

    
}
