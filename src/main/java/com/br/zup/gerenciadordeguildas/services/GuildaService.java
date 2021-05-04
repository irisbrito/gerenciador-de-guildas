package com.br.zup.gerenciadordeguildas.services;

import com.br.zup.gerenciadordeguildas.entities.Guilda;
import com.br.zup.gerenciadordeguildas.exceptions.RecursoNaoEncontradoException;
import com.br.zup.gerenciadordeguildas.repositories.GuildaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuildaService {

    @Autowired
    private GuildaRepository guildaRepository;

    public Guilda cadastrarGuilda(Guilda guilda) {
        return guildaRepository.save(guilda);
    }

    public Iterable<Guilda> retornarTodasAsGuildas(){
        return guildaRepository.findAll();
    }

    public void deletarGuildas(Integer id) {
        if(guildaRepository.existsById(id)){
            guildaRepository.deleteById(id);
        }

        throw new RecursoNaoEncontradoException("Guilda", id);
    }
}
