package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Ata;
import com.br.zup.gerenciadordeguildas.exceptions.ListaVaziaException;
import com.br.zup.gerenciadordeguildas.repositories.AtaRepository;
import org.springframework.stereotype.Service;

@Service
public class AtaService {

    private AtaRepository ataRepository;

    public AtaService(AtaRepository ataRepository) {
        this.ataRepository = ataRepository;
    }

    public Iterable<Ata> buscarAtas() {
        Iterable<Ata> listaComTodasAtas = ataRepository.findAll();
        if(listaComTodasAtas.iterator().hasNext()) {
            return listaComTodasAtas;
        }
        throw new ListaVaziaException("ata", 'a');
    }

    public Ata cadastrarAtas(Ata ata){
        return ataRepository.save(ata);
    }

}
